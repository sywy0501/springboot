package com.cs.springboot.huawei;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author: cs
 * @date: 2019/10/23 7:15 下午
 * @desc: j初始值为0，两个线程+1，两个线程-1，循环100次
 */
public class Test2 {

    private int j=0;

    private synchronized void  inc(){
        j++;
        System.out.println(Thread.currentThread().getName()+": "+j);
    }

    private synchronized void dec(){
        j--;
        System.out.println(Thread.currentThread().getName()+": "+j);
    }

    class Inc implements Runnable{

        @Override
        public void run() {
            for (int i=0;i<100;i++){
                inc();
            }
        }
    }

    class Dec extends Thread{
        @Override
        public void run() {
            for (int i=0;i<100;i++){
                dec();
            }
        }
    }

    public static void main(String[] args) {

        Test2 test2 = new Test2();
        Inc inc = test2.new Inc();

        Thread thread = null;
        for (int i=0;i<2;i++){
            thread = new Thread(inc);
            thread.start();
        }

        for (int i=0;i<2;i++){
            thread = test2.new Dec();
            thread.start();
        }
    }
}
