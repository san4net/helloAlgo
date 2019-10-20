package com.reactive;
import io.reactivex.*;

public class HelloReactive {


    public static void helloWorld(){
        Flowable.just("Hello world").subscribe(System.out::println);
    }
}
