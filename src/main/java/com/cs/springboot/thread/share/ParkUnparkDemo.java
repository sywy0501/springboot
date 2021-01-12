package com.cs.springboot.thread.share;

import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: chushi
 * @create: 2021-01-11 16:06
 **/
public class ParkUnparkDemo {

    private static Object baozi = null;

    public void parkUnparkTest() throws InterruptedException {
        Thread consumerThread = new Thread(()->{
            if (baozi==null){
                System.out.println("1.进入等待");
                LockSupport.park();
            }
            System.out.println("2.买到包子，回家");
        });
        consumerThread.start();

        Thread.sleep(3000);
        baozi = new Object();
        LockSupport.unpark(consumerThread);
        System.out.println("3.通知消费者");
    }

    public void parkUnparkDeadLockTest() throws InterruptedException {
        Thread consumerThread = new Thread(()->{
            if (baozi==null){
                System.out.println("1.进入等待");
                synchronized (this){
                    LockSupport.park();
                }
            }
            System.out.println("2.买到包子,回家");
        });
        consumerThread.start();
        Thread.sleep(3000);
        baozi = new Object();

        synchronized (this){
            LockSupport.unpark(consumerThread);
        }
        System.out.println("3.通知消费者");
    }

    public static void main(String[] args) throws InterruptedException {
        ParkUnparkDemo demo = new ParkUnparkDemo();
        //demo.parkUnparkTest();
        demo.parkUnparkDeadLockTest();
    }
}