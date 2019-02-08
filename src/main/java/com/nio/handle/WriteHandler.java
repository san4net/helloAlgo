package com.nio.handle;

import com.nio.ServerSupport;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Queue;

public class WriteHandler implements Handler<SelectionKey> {

    private final Map<SocketChannel, Queue<ByteBuffer>> pendingData;

    public WriteHandler(Map<SocketChannel, Queue<ByteBuffer>> pendingData) {
        this.pendingData = pendingData;
    }

    @Override
    public void handle(SelectionKey selectionKey) throws IOException {

        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        Queue<ByteBuffer> queue = pendingData.get( socketChannel );

        while (!queue.isEmpty()){
            ByteBuffer buffer = queue.peek();
            int written = socketChannel.write( buffer );

            if(written==-1){
                socketChannel.close();
                pendingData.remove( socketChannel );
                System.out.println("disconnected from"+socketChannel+ " in write()");
                return;
            }

            if(buffer.hasRemaining()){
                return;
            }

            queue.remove();

            selectionKey.interestOps( SelectionKey.OP_READ );
        }

    }
}
