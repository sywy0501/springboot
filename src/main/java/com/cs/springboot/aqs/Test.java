package com.cs.springboot.aqs;

import java.util.concurrent.CyclicBarrier;

/**
 * @author: cs
 * @date: 2019/09/12 9:59 上午
 * @desc:
 */
public class Test {

    private static CyclicBarrier barrier = new CyclicBarrier(31);
    private static int a = 0;
    private static Mutex mutex = new Mutex();

    public static void main(String[] args) throws Exception{
        for (int i=0;i<30;i++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<10000;i++){
                        increment1();
                    }
                    try {
                        barrier.await();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        barrier.await();
        System.out.println("加锁前，a="+a);

        barrier.reset();
        a=0;

        for (int i=0;i<30;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0;i<10000;i++){
                        increment2();
                    }
                    try {
                        barrier.await();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        barrier.await();
        System.out.println("加锁后，a="+a);
    }

    public static void increment1(){
        a++;
    }

    public static void increment2(){
        mutex.lock();
        a++;
        mutex.unlock();
    }
}
