package com.reactive.rxsystem;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class HttpVertical extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        this.vertx.createHttpServer().requestHandler(httpServerRequest -> {
            httpServerRequest.response().setChunked(true).write("hello from server").end();
        }).listen(9090);

        startFuture.succeeded();
    }
}
