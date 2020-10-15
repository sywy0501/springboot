package com.cs.springboot.huawei;

import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: cs
 * @date: 2019/10/18 2:31 下午
 * @desc: 两个线程，一个线程+1，一个线程-1，共执行5次
 *//**/
public class Test {

    static class SharedData{
        private int num = 0;
        private Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        public void increment(){
            lock.lock();
            try {
                while (num!=0){
                    condition.await();
                }
                num++;
                System.out.println(Thread.currentThread().getName()+": "+num);
                condition.signalAll();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public void decrement(){
            lock.lock();
            try {
                while (num==0){
                    condition.await();
                }
                num--;
                System.out.println(Thread.currentThread().getName()+": "+num);
                condition.signalAll();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.lock();
            }
        }
    }

    public static void main(String[] args) {
        SharedData sharedData = new SharedData();

        new Thread(()->{
           for (int i=0;i<5;i++){
               try {
                   sharedData.increment();
               }catch (Exception e){
                   e.printStackTrace();
               }
           }
        },"aa").start();

        new Thread(()->{

            try {
                for (int i = 0; i < 5; i++) {
                    sharedData.decrement();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        },"bb").start();
    }
}
