package com.cs.springboot.thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author: cs
 * @date: 2019/08/19 10:55
 * @desc: 死锁demo
 */
public class DeadLockDemo implements Runnable{
    /*public void method1(){
        synchronized (Integer.class){
            System.out.println("---method1 Integer---");
        }

        synchronized (String.class){
            System.out.println("---method1 String---");
        }
    }

    public void method2(){
        synchronized (String.class){
            System.out.println("---method2 String---");
        }
        synchronized (Integer.class){
            System.out.println("---method2 Integer---");
        }
    }*/
    private final String lockA;
    private final String lockB;

    public DeadLockDemo(String lockA,String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"持有"+lockA+"，尝试获取"+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"持有"+lockB+",尝试获取"+lockA);
            }
        }
    }

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new DeadLockDemo(lockA,lockB),"ThreadAAA").start();
        new Thread(new DeadLockDemo(lockB,lockA),"ThreadBBB").start();

    }
}
