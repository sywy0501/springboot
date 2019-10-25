package com.cs.springboot.lock;

/**
 * @author: cs
 * @date: 2019/08/19 10:55
 * @desc: 死锁demo
 */
public class DeadLockDemo {
    public void method1(){
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
    }
}
