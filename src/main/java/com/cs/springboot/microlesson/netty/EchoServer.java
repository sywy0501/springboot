package com.cs.springboot.microlesson.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @description:
 * @author: chushi
 * @create: 2020-10-20 18:10
 **/
public class EchoServer {
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));

    public static void main(String[] args) throws Exception {
        //Configure the server
        //创建 EventLoopGroup I/O 线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //创建 EventLoopGroup I/O 线程组
        EventLoopGroup workerGroup2 = new NioEventLoopGroup();

        try {
            //服务端启动引到工具类
            ServerBootstrap b = new ServerBootstrap();
            //配置服务端处理的 reactor 线程组以及服务端的其他配置
            b.group(bossGroup, workerGroup2).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.DEBUG)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline p = ch.pipeline();
                    p.addLast(new ServerHandler());
                }
            });
            //通过 bind 启动服务
            ChannelFuture f = b.bind(PORT).sync();
            //阻塞主线程，知道网络服务被关闭
            f.channel().closeFuture().sync();
        }finally {
            //关闭线程数组
        }
    }
}
