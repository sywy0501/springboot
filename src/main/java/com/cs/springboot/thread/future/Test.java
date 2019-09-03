package com.cs.springboot.thread.future;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: cs
 * @date: 2019/08/23 21:47
 * @desc:
 */
public class Test {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Date date = new Date();

        int taskSize = 5;

        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        //创建多个有返回值的任务
        List<Future> list = new ArrayList<>();

        for (int i = 0;i<taskSize;i++){
            Callable c = new FutureThread(i+" ");
            //执行任务并创建Future对象
            Future future = pool.submit(c);
            list.add(future);
        }
        //关闭线程池
        pool.shutdown();
        //获取所有并发任务结果
        for (Future f:list){
            //从Future对象上获取任务的返回值
            System.out.println(">>>"+f.get().toString());
        }

        Date date1 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【" + (date1.getTime() - date.getTime()) + "毫秒】");

    }
}
