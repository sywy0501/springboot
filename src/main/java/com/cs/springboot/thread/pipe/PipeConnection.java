package com.cs.springboot.thread.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author: cs
 * @date: 2019/08/23 17:48
 * @desc: 管道流通信
 *        管道流只能在两个线程之前传递数据
 *        管道流只能实现单向发送，两个线程互相通信需要两个管道
 */
public class PipeConnection {

    public static void main(String[] args) {
        PipedOutputStream pos = new PipedOutputStream();

        PipedInputStream pis = new PipedInputStream();

        try {
            //输入流和输出流连接
            pos.connect(pis);
        }catch (Exception e){
            e.printStackTrace();
        }

        Producer producer = new Producer(pos);

        Consumer1 consumer1 = new Consumer1(pis);

        producer.start();
        consumer1.start();
    }
}
