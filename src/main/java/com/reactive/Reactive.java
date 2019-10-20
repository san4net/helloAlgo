package com.reactive;


import io.reactivex.Observable;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static io.reactivex.Observable.*;

public class Reactive {

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
//        assertTrue(result.get().equals("abcd"));
    }

}
