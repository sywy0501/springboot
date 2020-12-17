/*
package com.cs.springboot.microlesson.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

*
 * @description: 多路复用 reactor 模型
 * @author: chushi
 * @create: 2020-10-20 14:54
 *

public class NIOServerV3 {
    //处理业务操作的线程
    private static ExecutorService workPool = Executors.newCachedThreadPool();

*
     * 封装了 selector.select()等事件轮询的代码


    abstract class ReactorThread extends Thread {
        Selector selector;
        LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

*
         * Selector 监听到有事件后，调用这个方法


        public abstract void handler(SelectableChannel channel) throws Exception;

        private ReactorThread() throws IOException {
            selector = Selector.open();
        }

        volatile boolean running = false;

        @Override
        public void run() {
            //轮询 Selector 事件
            while (running) {
                try {
                    //执行队列中的任务
                    Runnable task;
                    while ((task = taskQueue.poll()) != null) {
                        task.run();
                    }
                    selector.select(1000);

                    //获取查询结果
                    Set<SelectionKey> selected = selector.selectedKeys();
                    //遍历查询结果
                    Iterator<SelectionKey> iter = selected.iterator();

                    while (iter.hasNext()) {
                        //被封装的查询结果
                        SelectionKey key = iter.next();
                        iter.remove();
                        int readyOps = key.readyOps();
                        //关注 Read 和 Accept 两个事件
                        if ((readyOps & (SelectionKey.OP_READ | SelectionKey.OP_ACCEPT)) != 0 || readyOps == 0) {
                            try {
                                SelectableChannel channel = (SelectableChannel) key.attachment();
                                channel.configureBlocking(false);
                                handler(channel);
                                if (!channel.isOpen()) {
                                    //如果关闭了，就取消这个 key 的订阅
                                    key.cancel();
                                }
                            } catch (Exception e) {
                                //如果有异常就取消这个 key 的订阅
                                key.cancel();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ServerSocketChannel serverSocketChannel;
    //1.创建多个线程-accept 处理 reactor 线程(accept 线程)
    private ReactorThread[] mainReactorThreads = new ReactorThread[1];
    //2.创建多个线程-io 处理 reactor 线程(I/O 线程)
    private ReactorThread[] subReactorThreads = new ReactorThread[8];

*
     * 初始化线程组


    private void newGroup() throws IOException {
        //创建 IO 线程，负责处理客户端连接以后 socketChannel 的 IO 读写
        for (int i = 0; i < subReactorThreads.length; i++) {
            subReactorThreads[i] = new ReactorThread() {
                @Override
                public void handler(SelectableChannel channel) throws Exception {
                    //work 线程只负责处理 IO，不处理 accept 事件
                    SocketChannel ch = (SocketChannel) channel;
                    ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                    while (ch.isOpen() && ch.read(requestBuffer) != -1) {
                        //长连接情况下，需要手动判断数据有没有读取结束()此处做一个简单的判断，超过 0 字节就认为请求结束了
                        if (requestBuffer.position() > 0) {
                            break;
                        }
                    }
                    if (requestBuffer.position() == 0) {
                        return;
                    }
                    requestBuffer.flip();
                    byte[] content = new byte[requestBuffer.limit()];
                    requestBuffer.get(content);
                    System.out.println(new String(content));
                    System.out.println(Thread.currentThread().getName() + "收到数据，来自：" + ch.getRemoteAddress());

                    workPool.submit(()->{});

                    //响应结果 200
                    String response = "HTTP/1.1 200 OK\r\n" + "Content-Length: 11\r\n\r\n" + "Hello World";
                    ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                }
            };
        }

        //创建 mainReactor 线程，只负责处理 serverSocketChannel
        for (int i = 0;i<mainReactorThreads.length;i++){
            mainReactorThreads[i] = new ReactorThread() {
                AtomicInteger incr = new AtomicInteger(0);
                @Override
                public void handler(SelectableChannel channel) throws Exception {
                    //只做请求分发，不做具体的数据读取
                    ServerSocketChannel ch = (ServerSocketChannel) channel;
                    SocketChannel socketChannel = ch.accept();
                    socketChannel.configureBlocking(false);
                    //收到连接建立的通知之后，分发给 I/O 线程继续去读取数据
                    int index = incr.getAndIncrement()%subReactorThreads.length;
                    ReactorThread workEventLoop = subReactorThreads[index];
                    workEventLoop.doStart();
                    SelectionKey selectionKey = workEventLoop.register(socketChannel);
                    selectionKey.interestOps(SelectionKey.OP_READ);
                    System.out.println(Thread.currentThread().getName()+"收到新连接："+socketChannel.getRemoteAddress());
                }
            };
        }
    }

    private void initAndRegister()throws Exception{
        //1. 创建 ServerSocketChannel
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        //2.将 serverSocketChannel 注册到 selector
        int index = new Random().nextInt(mainReactorThreads.length);
        mainReactorThreads[index].doStart();
        SelectionKey selectionKey = mainReactorThreads[index].register(serverSocketChannel);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);
    }

    private void bind()throws IOException{
        //1. 正式绑定端口，对外服务
        serverSocketChannel.bind(new InetSocketAddress(8080));
        System.out.println("启动完成，端口 8080");
    }

    public static void main(String[] args) throws Exception {
        NIOServerV3 nioServerV3 = new NIOServerV3();
        nioServerV3.newGroup();//1. 创建 main 和 sub 两组线程
        nioServerV3.initAndRegister();//2. 创建 ServerSocketChannel，注册到 mainReactor 线程上的 selector 上
        nioServerV3.bind();
    }
}
*/
