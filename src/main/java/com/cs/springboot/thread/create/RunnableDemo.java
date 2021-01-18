package com.cs.springboot.thread.create;

/**
 * @description:
 * @author: chushi
 * @create: 2021-01-18 16:52
 **/
public class RunnableDemo implements Runnable{
    @Override
    public void run() {
        System.out.println("启动线程");
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new RunnableDemo());
        thread.start();
    }
}
