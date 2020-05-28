package com.cs.springboot.microlesson.thread;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author: cs
 * @date: 2020/05/19 21:52
 * @desc: 线程通信方式-文件共享
 */
public class Demon2 {
    public static void main(String[] args) {
        //线程1写入数据
        new Thread(()->{
            try {
                while (true){
                    Files.write(Paths.get("Demon.log"),
                            ("当前时间"+String.valueOf(System.currentTimeMillis())).getBytes());
                    Thread.sleep(1000L);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        //线程2读数据
        new Thread(()->{
            try {
                while (true){
                    Thread.sleep(1000L);
                    byte[] allBytes = Files.readAllBytes(Paths.get("Demon.log"));
                    System.out.println(new String(allBytes));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }

}
