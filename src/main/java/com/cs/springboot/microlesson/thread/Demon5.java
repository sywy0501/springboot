package com.cs.springboot.microlesson.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: cs
 * @date: 2020/05/21 22:35
 * @desc: 线程通信-park unpark
 */
public class Demon5 {
    private Object o = null;
    public void parkUnparkTest(){

        Thread consumer = new Thread(()->{
            if (o==null){
                LockSupport.park();
            }
        });
        consumer.start();
        o = new Object();
        LockSupport.unpark(consumer);
    }
}
