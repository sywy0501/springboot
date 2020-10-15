package com.cs.springboot.microlesson.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: cs
 * @date: 2020/05/24 20:33
 * @desc: 线程池的使用
 */
public class Demon6 {

    public void testCommon(ThreadPoolExecutor threadPoolExecutor)throws Exception{
        for (int i=0;i<15;i++){
            int n = i;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("开始执行: "+n);
                        Thread.sleep(3000);
                        System.out.println("执行结束："+n);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("任务提交成功："+i);
        }
        Thread.sleep(500);
        System.out.println("当前线程池线程数量为："+threadPoolExecutor.getPoolSize());
        System.out.println("当前线程池等待的数量为："+threadPoolExecutor.getQueue().size());
        Thread.sleep(15000);
        System.out.println("当前线程池数量为："+threadPoolExecutor.getPoolSize());
        System.out.println("待其概念线程池等待的数量为："+threadPoolExecutor.getQueue().size());
    }

    private void threadPoolExecutorTest1()throws Exception{
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        testCommon(threadPoolExecutor);
    }
}
