package com.nio.handle;

public class ThreadedHandlerImpl<S> extends UncheckedHandlerHandlerImpl<S> {


    public ThreadedHandlerImpl(Handler<S> handler) {
        super( handler );
    }

    @Override
    public void handle(S s) {
       new Thread(()->{
         super.handle( s );
       }) .start(); ;
    }
}
