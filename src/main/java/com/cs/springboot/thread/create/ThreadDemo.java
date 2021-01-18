package com.cs.springboot.thread.create;

/**
 * @description:
 * @author: chushi
 * @create: 2021-01-18 16:51
 **/
public class ThreadDemo extends Thread{

    @Override
    public void run() {
        System.out.println("启动线程");
    }

    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        demo.start();
    }
}
