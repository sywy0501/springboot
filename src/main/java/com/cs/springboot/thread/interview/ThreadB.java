package com.cs.springboot.thread.interview;

/**
 * @author: cs
 * @date: 2019/10/11 9:18 上午
 * @desc:
 */
public class ThreadB extends Thread {
    @Override
    public void run() {
        System.out.println("b");
    }

    public synchronized void bwait(){
        try {
            wait(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
