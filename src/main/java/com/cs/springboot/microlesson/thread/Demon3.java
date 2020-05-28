package com.cs.springboot.microlesson.thread;

/**
 * @author: cs
 * @date: 2020/05/20 21:26
 * @desc: 线程通信-变量共享
 */
public class Demon3 {
    public static String content = "阿达";

    public static void main(String[] args) {
        //线程1-写入数据

        new Thread(() -> {
            try {
                while (true){
                    content = "当前时间" + String.valueOf(System.currentTimeMillis());
                    Thread.sleep(1000l);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        //线程2 读取数据
        new Thread(()->{
            try {
                while (true){
                    Thread.sleep(1000l);
                    System.out.println(content);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
}
