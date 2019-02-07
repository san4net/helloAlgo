package com.nio.handle;

import com.nio.ServerSupport;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TransmogrifyChannelHandlerImpl implements Handler<SocketChannel> {

    private ByteBuffer byteBuffer = ByteBuffer.allocate( 80 );

    @Override
    public void handle(SocketChannel socketChannel) throws IOException {

        int read = socketChannel.read( byteBuffer );

        if (read < 0) {
            socketChannel.close();
            return;
        }

        if(read>0) {
            ServerSupport.transmogrify( byteBuffer );
            while (byteBuffer.hasRemaining()) {
                socketChannel.write( byteBuffer );
            }
            byteBuffer.compact();
        }

    }
}
