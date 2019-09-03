package com.cs.springboot.thread.count;

import java.util.concurrent.CyclicBarrier;

/**
 * @author: cs
 * @date: 2019/08/26 22:05
 * @desc: CyclicBarrier
 *        加计数方式，达到指定值时释放所有等待线程，计数置为0重新开始
 *        调用await方法计数加1，若加1后的值不等于构造方法的值则线程阻塞，可重复利用
 */
public class CyclicBarrierTest {

    static class readNum implements Runnable{

        private int id;
        private CyclicBarrier cyclicBarrier;

        public readNum(int id ,CyclicBarrier cyclicBarrier){
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("id:"+id);
            try {
                cyclicBarrier.await();
                System.out.println("线程组任务" + id + "结束，其他任务继续");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier c = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("线程组执行结束");
            }
        });
        for (int i = 0;i<5;i++){
            new Thread(new readNum(i,c)).start();
        }

        //cyclicBarrier可以重复利用
        for (int i = 11;i<16;i++){
            new Thread(new readNum(i,c)).start();
        }
    }
}
