package com.cs.springboot.thread.stop;

/**
 * @description:
 * @author: chushi
 * @create: 2020-12-31 17:05
 **/
public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        StopThread thread = new StopThread();
        thread.start();
        Thread.sleep(1000);

        thread.interrupt();

        while (thread.isAlive()){

        }
        thread.print();
    }
}
