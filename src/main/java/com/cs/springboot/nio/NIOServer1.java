package com.cs.springboot.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @description: 基于非阻塞的写法，一个线程处理轮询所有请求
 * @author: chushi
 * @create: 2020-10-12 18:18
 **/
public class NIOServer1 {
    /**
     * 已经建立连接的集合
     */
    private static ArrayList<SocketChannel> channels = new ArrayList<>();

    public static void main(String[] args)throws Exception {
        //创建网络服务端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("启动成功");

        while (true){
            //获取新 tcp 连接通道
            SocketChannel socketChannel = serverSocketChannel.accept();
            //tcp 请求 读取/响应
            if (socketChannel!=null){
                System.out.println("收到新连接："+socketChannel.getRemoteAddress());
                socketChannel.configureBlocking(false);
                channels.add(socketChannel);
            }else {
                //没有新连接的情况下，就去处理现有连接的数据，处理完的就删除掉
                Iterator<SocketChannel> iterator = channels.iterator();
                while (iterator.hasNext()){
                    SocketChannel ch = iterator.next();
                    try {
                        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                        if (ch.read(requestBuffer)==0){
                            //等于 0，代表这个通道没有数据需要处理，那就待会再处理
                            continue;
                        }
                        while (ch.isOpen()&&ch.read(requestBuffer)!=-1){
                            //长连接情况下，需要手动判断数据有没有读取结束（此处做一个简单的判断：超过 0 字节就认为请求结束了）
                            if (requestBuffer.position()>0){
                                break;
                            }
                        }
                        if (requestBuffer.position()==0){
                            continue;
                        }
                    }
                }
            }
        }
    }
}
