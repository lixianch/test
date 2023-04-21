package com.lixianch.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * created by lixianch on 2023/4/17
 */
public class NioClient {

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 9090;
        initClient(host, port);
    }

    private static void initClient(String host, int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.setOption(StandardSocketOptions.TCP_NODELAY, false);
        InetSocketAddress socketAddress = new InetSocketAddress(host, port);
        if(!socketChannel.connect(socketAddress)){
            System.out.print("客户端连接中");
            while (!socketChannel.finishConnect()) {
                System.out.println(".");
            }
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap("交谈中".getBytes());
        socketChannel.write(byteBuffer);
        socketChannel.close();
    }

}
