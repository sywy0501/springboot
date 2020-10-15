package com.cs.springboot.microlesson.thread;

/**
 * @author: cs
 * @date: 2020/05/20 21:42
 * @desc: 线程通信-JDK API
 */
public class Demon4 {
    private Object o = null;
    public void waitNotifyTest() throws Exception {

        new Thread(()-> {
                if (o == null) {
                    synchronized (this) {
                        try {
                            System.out.println("无货阻塞");
                            this.wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("有货通行");
        }).start();

        Thread.sleep(3000);
        System.out.println("进货");
        o = new Object();
        synchronized (this) {
            System.out.println("唤醒");
            this.notifyAll();
        }
    }

    public void waitNotifyDeadLockTest() throws Exception {
        new Thread(()-> {
                if (o==null){
                    try {

                        Thread.sleep(5000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                        synchronized (this){
                            try {

                            this.wait();
                        }catch (Exception e){
                                e.printStackTrace();
                            }

                    }
                }
        }).start();
        o = new Object();
        Thread.sleep(1000);
        synchronized (this){

            this.notifyAll();
        }
    }

    public static void main(String[] args) throws Exception{
        Demon4 demon4 = new Demon4();
        //demon4.waitNotifyTest();

        demon4.waitNotifyDeadLockTest();
    }
}
