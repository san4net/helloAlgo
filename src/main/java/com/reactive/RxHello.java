package com.reactive;


import com.google.common.collect.Lists;
import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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

    }

    public static void testBackPressure(){
       /* Observable.range(1, 100_00_00)
                .map(Item::new)
                .observeOn(Schedulers.io())
                .subscribe(item->{
                    nap();
                    logger.info("received {}", item.i);
                }, err->{

                });*/

        Flowable.range(1, 100_00_00)
                .map(Item::new)
                .observeOn(Schedulers.io())
                .subscribe(item ->
                        {
                            nap(50);
                            logger.info("received {}", item.i);
                        },
                        err -> {
                            logger.error("got error", err);
                        });

    }

    public static class Item{
        int i;

        public Item(int i) {
            this.i = i;
            logger.info("creating {}", i);
        }
    }
    private static List<String> SUPER_HEROES = Arrays.asList(
            "Superman",
            "Batman",
            "Aquaman",
            "Asterix",
            "Captain America"
    );

    public static void practiceFunction(){
       /* Observable.range(1, 5)
                .first(5)
                .doOnSuccess(System.out::print)
                .subscribe();*/
//        create().skip(2).take(2).subscribe(System.out::println);

        Flowable.range(1,5)
                .scan(0, (res, va)-> res+va)
                .subscribe(System.out::println);

        Flowable.range(1,5).
                reduce(0, (a,b)->a+b)


        ;
               /* .doOnSuccess(System.out::println)
                .subscribe();*/

    }

    /**
     * Given two Observable o1 and 02 , we need to find element present in both
     *
     */
    public static void findCommonItemBetweenObservable(Observable<Integer> o1, Observable<Integer> o2) {
        // steps to follow
        o1.concatMap((Function<Integer, ObservableSource<Boolean>>) integer -> o2.contains(integer).toObservable())
        .zipWith(o1, (Boolean b, Integer i)->{ return b ? i:-1;})
        .filter((Integer k)->k>0).doOnNext(System.out::println)
        .subscribe();
    }

    private static void doColletion(){
        Observable.range(2,4).toList().subscribe(List::size);
    }


    public static void doFlat(){
        List<String> listA = Lists.newArrayList();
        listA.add("A1");listA.add("B1");

        Observable.fromIterable(listA)
                .flatMap(a->
                        Observable.create(sub->{
                    sub.onNext(Lists.newArrayList(a));
                    sub.onComplete();

                }))
                .subscribe(a->System.out.println(a));

        /*List<String> listB = Lists.newArrayList();
        listB.add("A2");listB.add("B2");
        List<List<String>> listList =  Lists.newArrayList();
        listList.add(listA);
        listList.add(listB);

        List<List<List<String>>> listlistList = Lists.newArrayList();
        listlistList.add(listList);

        Observable.fromIterable(listList)
                .flatMap( s-> Observable.fromIterable(s))
                .subscribe( a->{System.out.println(a);});

        Observable.fromIterable(listList)
                .subscribe(System.out::println);

        //chaining two request using flatmap

        Observable.fromIterable(listList)
                .flatMap(s-> Observable.fromIterable(s))
                .take(1)
                .flatMapSingle(a->Single.just(listB))
                .doOnNext(a->System.out.println(a))
                .subscribe();

        // u can also use flatMap on a Single to execute a second request once the Single has completed as illustrated in
        Single.just(1)
                .flatMap(a->Single.just(a))
                .doOnSuccess(System.out::println)
                .flatMap(a-> Single.just(2))
                .doOnSuccess(System.out::println)
                .subscribe();*/



    }

    public static void mergeMultipleStream(){
        Flowable<String> stringFlowable1 = Flowable.fromArray("A","B","C");
        Flowable<String> stringFlowable2 = Flowable.fromArray("D","E","F");

        stringFlowable1.mergeWith(stringFlowable2)
                .distinct()
                .count()
                .subscribe(c->System.out.println("count"+c));

        stringFlowable1.concatWith(stringFlowable2).doOnNext(System.out::println).subscribe();

      stringFlowable1.zipWith(stringFlowable2,
             String::concat).doOnNext(System.out::println)
             .subscribe();

    }

    static Observable<Integer> create() {
        AtomicInteger subscribers = new AtomicInteger();
        AtomicInteger counter = new AtomicInteger();

        return Observable.<Integer>create(sub -> {
                    new Thread(() -> {
                        while (subscribers.get() > 0) {
                           sub.onNext(counter.getAndIncrement());
                           nap(50);
                        }
                    }).start();

                }
        ).publish().autoConnect()
                .doOnSubscribe(s->{
                    System.out.println("subscribed");
                     subscribers.getAndIncrement();
                })
                .doOnDispose(()->{
                    System.out.println("subscriber leaving");
                    subscribers.getAndDecrement();
                });

    }
    private static final Path DIRECTORY = new File("src/main/resources/super/heroes").toPath();

    public static void checkCleanUp() {
            new Thread(()->{
                try {
//                   Observable<String> hero =  getHeroes();
                     Observable<String> hero =  getHeroesIssueInMultipleRun();
                    getHeroesIssueInMultipleRun().observeOn(Schedulers.io()).subscribe((String h)->{logger.info("subscriber 1  {}" , h);});
                    getHeroesIssueInMultipleRun().observeOn(Schedulers.io()).subscribe((String h)->{logger.info("subscriber 2 {} " , h);});

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
    }


    public static Observable<String> getHeroes() throws IOException {
        return Observable.<Path>create(emitter->{

         new Thread(()-> {

             DirectoryStream<Path> stream = null;
             try {
                 stream = Files.newDirectoryStream(DIRECTORY);

                 for (Path path : stream) {
                     emitter.onNext(path);
                    logger.info("emitting {}", path.toFile().getName());
                 }

                 stream.close();
                 emitter.onComplete();
             } catch (IOException e) {
                 e.printStackTrace();
             }

         }).start();

        }).map(path -> path.toFile().getName());
    }

    public static Observable<String> getHeroesIssueInMultipleRun() throws IOException {
        /*DirectoryStream<Path> stream;
        try {
            stream = Files.newDirectoryStream(DIRECTORY);
        } catch (IOException e) {
            return Observable.error(e);
        }*/

        return Observable.fromIterable(Files.newDirectoryStream(DIRECTORY))
                .map(path -> path.toFile().getName());
//                .doFinally(stream::close);

    }



    public static void nap(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            // Ignore me.
        }
    }


    public static void rxOfSteps(){
        Disposable d =  create()
                  .doOnNext(
                    System.out::println
                    ).subscribe();

        nap(50);

        d.dispose();

        /*Observable.fromIterable(SUPER_HEROES)
                .doOnNext(System.out:: println)
                .doOnComplete(()->System.out.print("complete"))
                .subscribe();

        Observable<String> stream = Observable.create(subscriber ->
           {
            subscriber.onNext("Black Canary");
            subscriber.onNext("Catwoman");
            // Inject an error
            subscriber.onError(new Exception("What a terrible failure"));
            subscriber.onNext("Elektra");
            subscriber.onComplete();
        });

        stream.doOnNext(System.out::println)
                .subscribe();

        Completable.fromAction(()-> System.out.println("action done"))
                .subscribe(()-> System.out.printf("complete"), err-> System.out.println(err));*/
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

    public static  <E> Single<E> single(E e){
        return Single.just(e);
    }

    public static void hotAndCold() throws InterruptedException {
       Observable<Long> observable =  Observable.interval(1000, TimeUnit.MILLISECONDS).doOnSubscribe(d->{
            System.out.println("subscribed");
        });


       Observable<Long> hotObservable=  observable.publish();

        hotObservable.subscribe((i) -> {

                    logger.info("onnext {}", i);
                },
                (err) -> {
                    err.printStackTrace();
                },
                () -> {
                    logger.info("complete");
                });


       observable.subscribe((number)->{logger.info("sub 1 got {}", number);},(err)->{
            err.printStackTrace();
       });

       Thread.sleep(2000);

        observable.subscribe((number)->{logger.info("sub 2 got {}", number);},(err)->{
            err.printStackTrace();
        });

        Thread.sleep(10000);



    }

    public static void main(String[] args) throws InterruptedException {
//        hotAndCold();

        /*Observable.fromIterable(Arrays.asList("A","B")).
                flatMap(e->{
                    return Observable.<String>create(obser->{
                        obser.onNext(e);
                    });
                }
        ).doOnSubscribe(System.out::println).subscribe(a->System.out.println(a), b->b.printStackTrace());*/

//        checkCleanUp();
        doFlat();
//        mergeMultipleStream();
//        practiceFunction();
//        findCommonItemBetweenObservable(Observable.range(1,5), Observable.range(3,8));
//        new RxHello().testObservable();
//        rxOfSteps();
//        testBackPressure();
//        nap(5000);


       /* Observable.fromIterable(Lists.newArrayList("san", "wan")).map(RxHello::single).subscribe((Single<String> next) -> {
//            System.out.println(next.cache());
            System.out.println(next.blockingGet());
        });

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
        zip();*/
    }



}
