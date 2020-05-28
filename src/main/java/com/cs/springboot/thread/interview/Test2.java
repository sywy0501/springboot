package com.cs.springboot.thread.interview;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: cs
 * @date: 2019/10/11 9:58 上午
 * @desc:
 */
public class Test2 {

    private static Lock lock = new ReentrantLock();
    private static int state = 0;
    private static final int NUMBER = 10;

    static class ThreadA extends Thread{
        @Override
        public void run() {
            for (int i=0;i<NUMBER;){
                lock.lock();
                if (state%3==0){
                    System.out.println("A");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

    static class ThreadB extends Thread{
        @Override
        public void run() {
            for (int i=0;i<NUMBER;){
                lock.lock();
                if (state%3==1){
                    System.out.println("B");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

    static class ThreadC extends Thread{
        @Override
        public void run() {
            for (int i=0;i<NUMBER;){
                lock.lock();
                if (state%3==2){
                    System.out.println("C");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }
}
