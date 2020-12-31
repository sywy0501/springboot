package com.cs.springboot.thread.stop;

/**
 * @description: stop方法在停止线程的时候并不能保证线程安全
 * @author: chushi
 * @create: 2020-12-31 16:24
 **/
public class StopDemo {
    public static void main(String[] args) throws InterruptedException {
        StopThread thread = new StopThread();
        thread.start();
        Thread.sleep(1000);

        thread.stop();

        while (thread.isAlive()){

        }
        thread.print();
    }
}
