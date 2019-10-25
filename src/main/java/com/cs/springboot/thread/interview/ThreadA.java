package com.cs.springboot.thread.interview;

/**
 * @author: cs
 * @date: 2019/10/11 9:17 上午
 * @desc:
 */
public class ThreadA extends Thread {
    @Override
    public void run() {
        System.out.println("a");
    }

    public synchronized void await(){
        try {
            wait(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
