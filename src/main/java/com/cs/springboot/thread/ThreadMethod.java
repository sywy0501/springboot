package com.cs.springboot.thread;

/**
 * @author: cs
 * @date: 2019/08/16 12:46
 * @desc: wait/notify/notifyAll方法的使用
 */
public class ThreadMethod {
    public synchronized void testWait(){
        System.out.println(Thread.currentThread().getName()+" Start...");
        try {
            wait(0);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" End...");
    }

    public static void main(String[] args) throws InterruptedException{
        //wait方法是一个本地方法，底层由一个监视器锁对象完成，如果调用wait方法没有使用synchronize关键字，就无法获得监视器锁，就会抛出 java.lang.IllegalMonitorStateException
        //因此wait的使用必须必须要在同步的范围
        ThreadMethod threadMethod = new ThreadMethod();

        for (int i = 0;i<5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    threadMethod.testWait();
                }
            }).start();
        }
        synchronized (threadMethod){
            threadMethod.notify();
        }
        Thread.sleep(3000);
        System.out.println("-------------------");

        synchronized (threadMethod){
            threadMethod.notifyAll();
        }
    }
}
