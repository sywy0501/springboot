package com.cs.springboot.microlesson.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:
 * @author: chushi
 * @create: 2020-10-20 11:12
 **/
public class NIOServerV2 {

    public static void main(String[] args)throws Exception {
        //1. 创建网络服务端 ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        //2. 构建一个 Selector 选择器，并且将 channel 注册上去
        Selector selector = Selector.open();
        //将 serverSocketChannel 注册到 selector
        SelectionKey selectionKey = serverSocketChannel.register(selector,0,serverSocketChannel);
        //对 serverSocketChannel 上面的 accept 事件感兴趣(serverSocketChannel 只能支持 accept 操作)
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);

        //3. 绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        System.out.println("启动成功");

        while (true){
            //不再轮询通道，改用下面轮询事件的方式，select 方法有阻塞效果，直到有事件通知才会有返回
            selector.select();
            //获取事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历查询结果
            Iterator<SelectionKey> iter = selectionKeys.iterator();
            while (iter.hasNext()){
                //被封装的查询结果
                SelectionKey key = iter.next();
                iter.remove();
                //关注 Read 和 Accept 两个事件
                if (key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel) key.attachment();
                    //将拿到的客户端连接通道，注册到 selector 上面
                    SocketChannel clientSocketChannel = server.accept();
                    clientSocketChannel.configureBlocking(false);
                    clientSocketChannel.register(selector,SelectionKey.OP_READ,clientSocketChannel);
                    System.out.println("收到新连接："+clientSocketChannel.getRemoteAddress());
                }

                if (key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.attachment();
                    try {
                        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                        while (socketChannel.isOpen()&&socketChannel.read(requestBuffer)!=-1){
                            //长连接情况下，需要手动判断数据有没有读取结束(此处做一个简单的判断，超过 0 字节就认为请求结束了)
                            if (requestBuffer.position()>0){
                                break;
                            }
                        }
                        if (requestBuffer.position()==0){
                            continue;
                        }
                        requestBuffer.flip();
                        byte[] content = new byte[requestBuffer.limit()];
                        requestBuffer.get(content);
                        System.out.println(new String(content));
                        System.out.println("收到数据，来自："+socketChannel.getRemoteAddress());

                        //响应结果 200
                        String response = "HTTP/1.1 200 OK\r\n" + "Content-Length: 11\r\n\r\n" + "Hello World";
                        ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                        while (buffer.hasRemaining()){
                            socketChannel.write(buffer);
                        }
                    }catch (IOException e){
                        //取消订阅
                        key.cancel();
                    }
                }

            }
            selector.selectNow();
        }

    }
}
