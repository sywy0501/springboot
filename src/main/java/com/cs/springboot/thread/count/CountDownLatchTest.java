package com.cs.springboot.thread.count;

import java.util.concurrent.CountDownLatch;

/**
 * @author: cs
 * @date: 2019/08/26 22:05
 * @desc: CountDownLatch
 *        减计数方式，计数为0时释放所有等待线程，无法重置
 *        调用countDown()方法计数减一，调用await()方法只进行阻塞对计数没有影响
 */
public class CountDownLatchTest {

    static class readNum implements Runnable{
        private int id;
        private CountDownLatch latch;

        public readNum(int id , CountDownLatch latch){
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("id:"+id);
            latch.countDown();
            System.out.println("线程组任务"+id+"结束，其他任务继续");
        }
    }

    public static void main(String[] args)throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0;i<5;i++){
            new Thread(new readNum(i,countDownLatch)).start();
        }
        countDownLatch.await();
        System.out.println("线程执行结束。。。。");
    }
}
