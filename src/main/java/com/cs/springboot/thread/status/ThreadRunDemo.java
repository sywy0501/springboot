package com.cs.springboot.thread.status;

/**
 * @description:
 * @author: chushi
 * @create: 2020-12-29 18:04
 **/
public class ThreadRunDemo {
    public static Thread thread;
    public static ThreadRunDemo threadRunDemo;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("##### 第一种状态切换-新建->运行->终止 #########");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1 当前状态:"+Thread.currentThread().getState().toString());
                System.out.println("thread1 执行了");
            }
        });
        System.out.println("没调用 start 方法，thread1 当前状态:"+thread1.getState().toString());
        thread1.start();
        Thread.sleep(1000L);
        System.out.println("等待一秒，再看 thread1 当前状态:"+thread1.getState().toString());
        System.out.println();

        System.out.println("######## 第二种：新建->运行->等待->运行->终止(sleep 方式) ########");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("thread2 当前状态:"+Thread.currentThread().getState().toString());
                System.out.println("thread2 执行了");
            }
        });
        System.out.println("没调用 start 方法,thread2 当前状态:"+thread2.getState().toString());
        thread2.start();
        System.out.println("调用 start 方法,thread2 当前状态:"+thread2.getState().toString());
        Thread.sleep(200);
        System.out.println("等待 200 毫秒，再看 thread2 当前状态:"+thread2.getState().toString());
        Thread.sleep(3000);
        System.out.println("等待 3 秒，再看 thread2 当前状态："+thread2.getState().toString());
        System.out.println();

        System.out.println("######### 第三种：运行->阻塞->运行->终止 ########");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ThreadRunDemo.class){
                    System.out.println("thread3 当前状态："+Thread.currentThread().getState().toString());
                    System.out.println("thread3 执行了");
                }
            }
        });
        synchronized (ThreadRunDemo.class){
            System.out.println("没调用 start 方法，thread3 当前状态："+thread3.getState().toString());
            thread3.start();
            System.out.println("调用 start 方法，再看 thread3 当前状态："+thread3.getState().toString());
            Thread.sleep(200);
            System.out.println("等待 200 毫秒，再看 thread3 当前状态："+thread3.getState().toString());
        }
        Thread.sleep(3000);
        System.out.println("等待 3 秒，让 thread3 抢到锁，再看 thread3 当前状态："+thread3.getState().toString());
    }
}
