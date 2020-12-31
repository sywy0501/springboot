package com.cs.springboot.thread.stop;

/**
 * @description:
 * @author: chushi
 * @create: 2020-12-31 17:11
 **/
public class FlagDemo {
    public volatile  static boolean flag = true;
    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (flag){
                        System.out.println("运行中");
                        Thread.sleep(1000);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(3000);
        flag = false;
        System.out.println("程序运行结束");
    }
}
