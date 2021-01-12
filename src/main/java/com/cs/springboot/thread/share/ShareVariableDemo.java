package com.cs.springboot.thread.share;

/**
 * @description: 共享变量
 * @author: chushi
 * @create: 2021-01-05 16:40
 **/
public class ShareVariableDemo {
    public static String content = "空";

    public static void main(String[] args) {
        new Thread(()->{
            try {
                while (true){
                    content = "当前时间"+String.valueOf(System.currentTimeMillis());
                    Thread.sleep(1000);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                while (true){
                    Thread.sleep(1000);
                    System.out.println(content);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
}
