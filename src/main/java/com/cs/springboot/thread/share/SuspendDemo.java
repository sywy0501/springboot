package com.cs.springboot.thread.share;

/**
 * @description:
 * @author: chushi
 * @create: 2021-01-05 16:49
 **/
public class SuspendDemo {

    public static Object baozipu = null;

    public void suspendResumeTest() throws InterruptedException {

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                if (null==baozipu){
                    System.out.println("1.没包子，进入等待");
                    Thread.currentThread().suspend();
                }
                System.out.println("2.买到包子回家");
            }
        });

        consumer.start();
        Thread.sleep(3000);
        baozipu = new Object();
        consumer.resume();
        System.out.println("3.通知消费者");
    }

    public static void main(String[] args) throws InterruptedException {
        SuspendDemo suspendDemo = new SuspendDemo();
        suspendDemo.suspendResumeTest();
    }
}
