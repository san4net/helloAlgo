package com.nio.handle;

import java.io.IOException;
import java.io.UncheckedIOException;

public class UncheckedHandlerHandlerImpl<S> extends DecorateHandler<S> {


    public UncheckedHandlerHandlerImpl(Handler<S> handler) {
        super(handler);
    }

    @Override
    public void handle(S s)  {
        try{
            super.handle( s );
        } catch (IOException i){
            throw new UncheckedIOException( i );
        }

    }
}
