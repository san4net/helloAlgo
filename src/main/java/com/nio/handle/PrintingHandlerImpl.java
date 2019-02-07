package com.nio.handle;

import java.io.IOException;

public class PrintingHandlerImpl<S> extends DecorateHandler<S> {


    public PrintingHandlerImpl(Handler<S> handler) {
        super(handler);
        }

    @Override
    public void handle(S s) throws IOException {
         System.out.println("connected to" + s);
         super.handle(s);
         System.out.println("disconnected from" + s);
    }
}
