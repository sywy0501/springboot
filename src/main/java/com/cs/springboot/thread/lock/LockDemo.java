package com.cs.springboot.thread.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: unsafe方法保证某个变量的线程安全，本质是 CAS+while 循环不断重试
 * @author: chushi
 * @create: 2021-03-16 16:10
 **/
public class LockDemo {

    int i = 0;
    private static  long valueOffset;

    static Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            valueOffset = unsafe.objectFieldOffset(LockDemo.class.getDeclaredField("i"));
        }catch (Exception exception){

        }
    }

    Lock lock = new ReentrantLock();
    public void add(){
        int current;
        do {
            current = unsafe.getIntVolatile(this,valueOffset);
        }while (!unsafe.compareAndSwapInt(this,valueOffset,current,current+1));

    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo lockDemo = new LockDemo();

        for (int i = 0;i<2;i++){
            new Thread(()->{
               for (int j = 0;j<10000;j++){
                   lockDemo.add();
               }
            }).start();
        }
        Thread.sleep(2000L);
        System.out.println(lockDemo.i);
    }
}
