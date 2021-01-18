package com.cs.springboot.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description:
 * @author: chushi
 * @create: 2021-01-18 16:54
 **/
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread.currentThread().setName("主线程");
        System.out.println(Thread.currentThread().getName());
        FutureTask<String> task = new FutureTask<String>(new CallableInner());

        Thread thread = new Thread(task);
        thread.setName("线程三");
        thread.start();
        String result = task.get();
        System.out.println(result);
    }

    static class CallableInner implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName());
            return Thread.currentThread().getName();
        }
    }
}
