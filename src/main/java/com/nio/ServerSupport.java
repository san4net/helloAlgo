package com.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ServerSupport {

    public static int transmogrify(int data) {
        return  data;
//     return Character.isAlphabetic(data) ? data ^ ' ': data;
    }

    public static void transmogrify(ByteBuffer byteBuffer) {
        //pos=0, limit=80, capacity=8=
        //"hello\n" pos=6 limit=6, capacity=80
        byteBuffer.flip();
        //after flip
        // pos=0 limit=6, capacity=80

        for (int i = 0; i < byteBuffer.limit(); i++) {
            byteBuffer.put( i, (byte) transmogrify( byteBuffer.get( i ) ) );
        }

    }
}
