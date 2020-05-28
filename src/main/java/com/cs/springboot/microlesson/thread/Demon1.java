package com.cs.springboot.microlesson.thread;

/**
 * @author: cs
 * @date: 2020/05/19 20:41
 * @desc: 多线程运行态切换示例
 */
public class Demon1 {

    public static void main(String[] args) throws InterruptedException {
        //第一种状态切换 新建-> 运行 -> 终止
        System.out.println("###第一种形态切换 新建-> 运行 -> 终止 ##########");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread当前状态："+Thread.currentThread().getState().toString());
                System.out.println("thread执行了");
            }
        });
        System.out.println("没调用start方法，thread当前状态："+thread.getState().toString());
        thread.start();
        Thread.sleep(2000l);
        System.out.println("等待两秒，再看thread当前状态："+thread.getState().toString());
        System.out.println();
        System.out.println("###第二种：新建->运行->等待->运行->终止(sleep方式)###");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("4 thread1当前状态："+Thread.currentThread().getState().toString());
                System.out.println("thread1执行了");
            }
        });
        System.out.println("1 thread1当前状态："+thread1.getState().toString());
        thread1.start();
        System.out.println("2 thread1当前状态："+thread1.getState().toString());
        System.out.println();
        Thread.sleep(200l);
        System.out.println("3 thread1当前状态："+thread1.getState().toString());
        Thread.sleep(3000l);
        System.out.println("5 thread1当前状态："+thread1.getState().toString());

        System.out.println();
        System.out.println("####第三种：新建->运行->阻塞->运行->终止######");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Demon1.class) {
                    System.out.println("thread2当前状态：" + Thread.currentThread().getState().toString());
                    System.out.println();
                }
            }
        });

        synchronized (Demon1.class){

            System.out.println("thread2当前状态"+thread2.getState().toString());
            thread2.start();
            System.out.println("thread2当前状态"+thread2.getState().toString());
            Thread.sleep(200l);
            System.out.println("thread2当前状态"+thread2.getState().toString());
        }
        Thread.sleep(3000l);
        System.out.println("thread2当前状态"+thread2.getState().toString());

    }

}
