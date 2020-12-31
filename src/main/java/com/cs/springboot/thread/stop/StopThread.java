package com.cs.springboot.thread.stop;

/**
 * @description:
 * @author: chushi
 * @create: 2020-12-31 16:24
 **/
public class StopThread extends Thread{

    private int i=0,j=0;

    @Override
    public void run() {
        synchronized (this){
            ++i;
            try {
                Thread.sleep(10000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ++j;
        }
    }

    public void print(){
        System.out.println("i="+i+" j="+j);
    }
}
