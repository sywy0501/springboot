package com.cs.springboot.thread.share;

/**
 * @description:
 * @author: chushi
 * @create: 2021-01-07 17:15
 **/
public class WaitNotifyDemo {

    public static Object baozi = null;

    public void waitNotifyTest() throws InterruptedException {
        new Thread(()->{
            if (baozi==null){
                synchronized (this){
                    try {
                        System.out.println("1.进入等待");
                        this.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("2.买到包子，回家");
        }).start();

        Thread.sleep(3000);
        baozi = new Object();
        synchronized (this){
            this.notify();
            System.out.println("3.通知消费者");
        }
    }

    public void waitNotifyDeadLockTest() throws InterruptedException {
        new Thread(()->{
            if (baozi==null){
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (this){
                    try {
                        System.out.println("1.进入等待");
                        this.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("2.买到包子，回家");
        }).start();

        Thread.sleep(3000);
        baozi = new Object();
        synchronized (this){
            this.notifyAll();
            System.out.println("3.通知消费者");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyDemo waitNotifyDemo = new WaitNotifyDemo();
        //waitNotifyDemo.waitNotifyTest();
        waitNotifyDemo.waitNotifyDeadLockTest();
    }
}
