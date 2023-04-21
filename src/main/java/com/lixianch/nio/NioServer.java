package com.lixianch.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * created by lixianch on 2023/4/17
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        int port = 9090;
        initServer(port);
    }

    private static void initServer(int port) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(9090);
        // server.bind与server.socket().bind逻辑相同，都是调用的ServerSocketChannel的bind
        server.configureBlocking(false);
        server.bind(inetSocketAddress);
//        server.socket().bind(inetSocketAddress);
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        while(true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(key.isAcceptable()){
                    SocketChannel socketChannel = server.accept();
                    System.out.println(String.format("client:%s 已上线", socketChannel.getRemoteAddress()));
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }else {
                    SocketChannel channel = (SocketChannel) key.channel();
                    if(key.isReadable()){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        System.out.println(String.format("client:%s, send msg:%s", channel.getRemoteAddress(), new String(byteBuffer.array())));
                    } else if(key.isConnectable()){
                        System.out.println(String.format("client:%s online", channel.getRemoteAddress()));
                    }
                }
                iterator.remove();
            }
        }
    }

}
