package com.cs.springboot.thread.future;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author: cs
 * @date: 2019/08/23 21:40
 * @desc: 有返回值的线程创建
 */
public class FutureThread implements Callable {

    private String taskNum;

    public FutureThread(String taskNum){
        this.taskNum = taskNum;
    }

    @Override
    public Object call() throws Exception {

        System.out.println(">>>"+taskNum+" 任务启动");
        Date date = new Date();
        Thread.sleep(1000);
        Date date1 = new Date();
        long time = date.getTime()-date1.getTime();
        System.out.println(">>>"+taskNum+" 任务终止");
        return taskNum+"任务返回运行结果,当前任务时间【" + time + "毫秒】";
    }
}
