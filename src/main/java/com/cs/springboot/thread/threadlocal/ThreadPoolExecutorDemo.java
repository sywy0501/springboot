package com.cs.springboot.thread.threadlocal;

import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.*;

/**
 * @description:
 * @author: chushi
 * @create: 2021-01-26 17:27
 **/
public class ThreadPoolExecutorDemo {

    /**
     * @author: ChuShi
     * @date: 2021/1/27 1:57 下午
     * @param
     * @return: void
     * @desc: 核心线程数 5 最大线程数 10 无界队列 超出核心线程数量的线程存活时间 5s
     */
    private void threadPoolExecutorTest1() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,5 , TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        testCommon(threadPoolExecutor);
    }

    /**
     * @author: ChuShi
     * @date: 2021/1/27 1:57 下午
     * @param
     * @return: void
     * @desc: 核心线程数 5 最大线程数 10 队列大小 3 超出核心线程数量的线程存活时间 5s
     */
    private void threadPoolExecutorTest2() throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("任务被拒绝执行了");
            }
        });
        testCommon(threadPoolExecutor);
    }

    /**
     * @author: ChuShi
     * @date: 2021/1/27 5:58 下午
     * @param
     * @return: void
     * @desc: 核心线程数 5 最大数 5 无界队列 超出核心线程数量的线程存活时间 0s
     */
    private void threadPoolExecutorTest3() throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,5 ,0L ,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
        testCommon(threadPoolExecutor);
    }

    private void threadPoolExecutorTest4() throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0,Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
        testCommon(threadPoolExecutor);
    }

    private void threadPoolExecutorTest5(){
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        threadPoolExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务被执行，当前时间："+System.currentTimeMillis());
            }
        },3000,TimeUnit.MILLISECONDS);
        System.out.println("定时任务，提交成功，时间是:"+System.currentTimeMillis()+",当前线程池中线程数量："+threadPoolExecutor.getPoolSize());
    }

    private void threadPoolExecutorTest6(){
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        threadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("任务-1 被执行，现在时间："+System.currentTimeMillis());
            }
        },2000,1000,TimeUnit.MILLISECONDS);

        threadPoolExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("任务-2 被执行，现在时间："+System.currentTimeMillis());
            }
        },2000,1000,TimeUnit.MILLISECONDS);
    }

    private void threadPoolExecutorTest7() throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("有任务被拒绝执行了");
            }
        });

        for (int i = 0;i<15;i++){
            int n  =i;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("开始执行："+n);
                        Thread.sleep(3000L);
                        System.out.println("执行结束："+n);
                    }catch (InterruptedException e){
                        System.out.println("异常："+e.getMessage());
                    }
                }
            });
            System.out.println("任务提交成功："+i);
        }

        Thread.sleep(1000);
        threadPoolExecutor.shutdown();
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("追加一个任务");
            }
        });
    }

    private void threadPoolExecutorTest8() throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(3), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("有任务被拒绝执行了");
            }
        });

        for (int i = 0;i<15;i++){
            int n  =i;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("开始执行："+n);
                        Thread.sleep(3000L);
                        System.out.println("执行结束："+n);
                    }catch (InterruptedException e){
                        System.out.println("异常："+e.getMessage());
                    }
                }
            });
            System.out.println("任务提交成功："+i);
        }

        Thread.sleep(1000);
        List<Runnable> shutdown = threadPoolExecutor.shutdownNow();
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("追加一个任务");
            }
        });
    }

    private void testCommon(ThreadPoolExecutor threadPoolExecutor) throws InterruptedException {

        for (int i = 0;i<15;i++){
            int n = i;
            threadPoolExecutor.submit(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {

                    try {
                        System.out.println("开始执行:"+n);
                        Thread.sleep(3000);
                        System.out.println("执行结束:"+n);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
            });
            System.out.println("任务提交成功："+i);
        }
        Thread.sleep(500L);
        System.out.println("当前线程池线程数量为："+threadPoolExecutor.getPoolSize());
        System.out.println("当前线程池等待数量为:"+threadPoolExecutor.getQueue().size());
        Thread.sleep(15000L);
        System.out.println("当前线程池线程数量为:"+threadPoolExecutor.getPoolSize());
        System.out.println("当前线程池等待数量为:"+threadPoolExecutor.getQueue().size());
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutorDemo threadPoolExecutorDemo = new ThreadPoolExecutorDemo();
        threadPoolExecutorDemo.threadPoolExecutorTest1();
    }
}
