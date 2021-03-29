package com.cs.springboot.thread.lock;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: chushi
 * @create: 2021-03-18 10:24
 **/
public class MyAQS {
    //acquire、acquireShared：定义了资源争用的逻辑，如果没拿到，就等待
    //tryAcquire、tryAcquiredShared：实际执行占用资源的操作，如何判定一个由使用者具体去实现
    //release、releaseShared：定义释放资源的逻辑，释放后，通知后续节点进行争抢
    //tyrRelease、tryReleaseShared：实际执行资源释放的操作，具体的 AQS 使用者去实现

    volatile AtomicReference<Thread> owner = new AtomicReference<>();

    volatile LinkedBlockingQueue<Thread> list = new LinkedBlockingQueue<>();

    volatile AtomicInteger status = new AtomicInteger(0);

    public int tryAcquireShared(){
        throw new UnsupportedOperationException();
    }

    public boolean tryReleaseShared(){
        throw new UnsupportedOperationException();
    }

    public void releaseShared(){
        if (tryReleaseShared()){
            Iterator<Thread> it = list.iterator();
            while (it.hasNext()){
                Thread thread = it.next();
                LockSupport.unpark(thread);
            }
        }
    }

    public void acquireShared(){
        boolean addQ = true;
        while (tryAcquireShared()<0){
            if (addQ){
                list.offer(Thread.currentThread());
                addQ = false;
            }else {
                LockSupport.park();
            }
        }
        list.remove(Thread.currentThread());
    }

    public boolean tryAcquire(){
        throw new UnsupportedOperationException();
    }

    public void acquire(){

        boolean flag = true;
        while (!tryAcquire()){
            if (flag){
                list.offer(Thread.currentThread()) ;
                flag = false;
            }else {
                LockSupport.park();
            }
        }
    }

    public void release(){
        if (tryRelease()){
            Iterator<Thread> it = list.iterator();
            if (it.hasNext()){
                Thread thread = it.next();
                LockSupport.unpark(thread);
            }
        }
    }

    public boolean tryRelease(){
        throw new UnsupportedOperationException();
    }

    public AtomicInteger getStatus() {
        return status;
    }

    public void setStatus(AtomicInteger status) {
        this.status = status;
    }
}
