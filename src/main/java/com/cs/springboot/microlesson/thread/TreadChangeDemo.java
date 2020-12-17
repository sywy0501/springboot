package com.cs.springboot.microlesson.thread;

import cn.hutool.core.thread.NamedThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: chushi
 * @create: 2020-10-22 17:37
 **/
public class TreadChangeDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.get();
        dynamicModifyExecutor();
    }

    /**
     * @param
     * @author: ChuShi
     * @date: 2020/10/22 5:48 下午
     * @return: java.util.concurrent.ThreadPoolExecutor
     * @desc: 自定义线程池
     */
    private static ThreadPoolExecutor builderThreadPoolExecutor() {
        return new ThreadPoolExecutor(2,
                5,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                new NamedThreadFactory("cs", true));
    }

    /**
     * @param
     * @author: ChuShi
     * @date: 2020/10/22 5:54 下午
     * @return: void
     * @desc: 先提交任务给线程池，并修改线程池参数
     */
    private static void dynamicModifyExecutor() throws InterruptedException {
        ThreadPoolExecutor executor = builderThreadPoolExecutor();
        for (int i = 0; i < 15; i++) {
            executor.submit(() -> {
                threadPoolStatus(executor, "创建任务");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolStatus(executor,"改变之前");
        executor.setCorePoolSize(10);
        executor.setMaximumPoolSize(10);
        threadPoolStatus(executor,"改变之后");
        Thread.currentThread().join();
    }

    private static void threadPoolStatus(ThreadPoolExecutor executor, String name) {
        LinkedBlockingQueue queue = (LinkedBlockingQueue) executor.getQueue();
        System.out.println(Thread.currentThread().getName() + "_" + name + "-:" +
                "核心线程数：" + executor.getCorePoolSize() +
                " 活动线程数:" + executor.getActiveCount() +
                " 最大线程数:" + executor.getMaximumPoolSize() +
                " 线程池活跃度:" + divide(executor.getActiveCount(), executor.getMaximumPoolSize()) +
                " 任务完成数:" + executor.getCompletedTaskCount() +
                " 队列大小:" + (queue.size() + queue.remainingCapacity()) +
                " 当前排队线程数:" + queue.size() +
                " 队列剩余大小:" + queue.remainingCapacity() +
                " 队列使用:" + divide(queue.size(), queue.size() + queue.remainingCapacity()));
    }


    private static String divide(int num1, int nums2) {
        return String.format("%1.2f%%", Double.parseDouble(num1 + "") / Double.parseDouble(nums2 + "") * 100);
    }
}
