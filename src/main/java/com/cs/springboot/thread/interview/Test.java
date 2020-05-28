package com.cs.springboot.thread.interview;

/**
 * @author: cs
 * @date: 2019/10/11 9:19 上午
 * @desc:
 */
public class Test {
    public static void main(String[] args) {
        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB();
        ThreadC c = new ThreadC();
        for (int i=0;i<5;i++){
            System.out.println("第"+i+"次");
            new Thread(a).start();
            a.await();
            new Thread(b).start();
            b.bwait();
            new Thread(c).start();
            c.cwait();
        }

        /*Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("a");
            }

            public synchronized void aWait () throws InterruptedException {
                wait(1);
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    a.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("b");
            }

            public synchronized void aWait () throws InterruptedException {
                wait(1);
            }
        });

        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    b.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("c");
            }

            public synchronized void aWait () throws InterruptedException {
                wait(1);
            }
        });

        a.start();
        b.start();
        c.start();*/
    }
}
