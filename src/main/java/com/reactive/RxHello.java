package com.reactive;


import io.reactivex.*;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RxHello {
   private static Logger logger = LoggerFactory.getLogger(RxHello.class.getName());

    @Test
    public void testObservable(){
        AtomicReference<String> result = new AtomicReference<>("");
        io.reactivex.Observable
                .fromArray(Arrays.asList("a","b","c","d").toArray())
                .map(Observable::just)
                .flatMap((a)->{return  a;})
                .subscribe(
                        i -> result.set(i+result.get()),  //OnNext
                        Throwable::printStackTrace, //OnError
                        () -> {

                        }
                        //OnCompleted
                );
        System.out.println(result.get());
        logger.info("done");

//        assertTrue(result.get().equals("abcd"));
    }

    public static void zip(){
        Flowable<String> ticks =  Flowable.interval(1, TimeUnit.MILLISECONDS, Schedulers.computation())
                .limit(10)
                .map(i->"tick#"+i)
                .subscribeOn(Schedulers.computation());

        Flowable<String> random  = Flowable.just(
                "abc", "def", "ghi", "jkl")
                .subscribeOn(Schedulers.computation());

        Flowable.merge(ticks,random)
                .subscribe(obj->logger.info("received {}",obj));

    }

    public static void main(String[] args) {
        new RxHello().testObservable();

        Single.just(1)
                .map(i->i*10)
                .map(Object::toString)
                .subscribe(in->logger.info(in));


        List<String> data =
                Arrays.asList("foo", "bar", "baz");
        Random random = new Random();


        Observable<String> source =
                Observable.create(sub -> {
            for (String a : data) {
                sub.onNext(a);
            }
            sub.onComplete();
        });

        source.subscribe(
                next-> logger.info("next {}",next),
                error-> logger.error("whhps",error),
                ()->logger.info("done"));


        new Thread(()->{source.retry(2)
                .subscribe(next-> logger.info("next {}",next),
                        error-> logger.error("whhps",error),
                        ()->logger.info("done"));}).start();


        Flowable.range(1,5)
                .map(i->i*10)
                .map(i->{
                    logger.info("map {}", i);
                    return i.toString();
                })
                .subscribeOn(Schedulers.single())
                .observeOn(Schedulers.computation())
                .subscribe(i->{logger.info(i);});

        System.out.println("done");
        zip();
    }



}
