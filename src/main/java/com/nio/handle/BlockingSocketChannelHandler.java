package com.nio.handle;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class BlockingSocketChannelHandler extends DecorateHandler<SocketChannel> {

    public BlockingSocketChannelHandler(Handler<SocketChannel> handler) {
        super( handler );
    }

    @Override
    public void handle(SocketChannel socketChannel) throws IOException {
        while (socketChannel.isConnected()){
            super.handle( socketChannel );
        }
    }
}
