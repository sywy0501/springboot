package com.cs.springboot.microlesson.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @description:
 * @author: chushi
 * @create: 2020-10-12 16:58
 **/
public class NIOClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        //默认阻塞 设置为非阻塞
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8080));

        while (!socketChannel.finishConnect()){
            // 没连接上，则一直等待
            Thread.yield();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        //发送内容
        String msg = scanner.nextLine();
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        while (buffer.hasRemaining()){
            socketChannel.write(buffer);
        }
        //读取响应
        System.out.println("收到服务端响应：");
        ByteBuffer requsetBuffer = ByteBuffer.allocate(1024);

        while (socketChannel.isOpen()&&socketChannel.read(requsetBuffer)!=-1){
            //长连接情况下，需要手动判断数据有没有读取结束(此处做一个简单的判断：超过 0 字节就认为请求结束了)
            if (requsetBuffer.position()>0){
                break;
            }
        }
        requsetBuffer.flip();
        byte[] content = new byte[requsetBuffer.limit()];
        requsetBuffer.get(content);
        System.out.println(new String(content));
        scanner.close();
        socketChannel.close();
    }
}
