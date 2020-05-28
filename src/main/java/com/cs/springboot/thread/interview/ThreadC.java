package com.cs.springboot.thread.interview;

/**
 * @author: cs
 * @date: 2019/10/11 9:18 上午
 * @desc:
 */
public class ThreadC implements Runnable {
    @Override
    public void run() {
        System.out.println("c");
    }

    public synchronized void cwait(){
        try {
            wait(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
