package com.nio.handle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class ExecutorServiceHandlerImpl<S> extends DecorateHandler<S> {

    private final ExecutorService service;
    private final Thread.UncaughtExceptionHandler exceptionHandler;

    public ExecutorServiceHandlerImpl(Handler<S> handler , ExecutorService service , Thread.UncaughtExceptionHandler exceptionHandler) {
        super( handler );
        this.service = service;
        this.exceptionHandler = exceptionHandler;
    }

    public ExecutorServiceHandlerImpl(Handler<S> handler , ExecutorService service ) {
        this(handler, service, (s,t) ->{
            System.out.println(t);
        });
    }

    @Override
    public void handle(S s) {

        service.submit(
                new FutureTask<>(
                        () -> {
                            super.handle( s );
                            return null;
                        } ) {
                    
                    @Override
                    protected void setException(Throwable t) {
                        exceptionHandler.uncaughtException( Thread.currentThread() , t );
                    }
                }

        );
    }
}
