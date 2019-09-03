package com.cs.springboot.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author: cs
 * @date: 2019/08/27 9:33
 * @desc: Semaphore模拟买票流程
 */
public class Ticket {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 8; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + ": 开始买票");
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + ": 购票成功");
                        semaphore.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
