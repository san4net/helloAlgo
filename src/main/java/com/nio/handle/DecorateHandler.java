package com.nio.handle;

import java.io.IOException;

public abstract class DecorateHandler<S> implements Handler<S> {

    protected Handler<S> handler;

    public DecorateHandler(Handler<S> handler) {
        this.handler = handler;
    }

    @Override
    public void handle(S s) throws IOException {
        handler.handle( s );
    }
}
