package com.cs.springboot.thread.lock;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @description:
 * @author: chushi
 * @create: 2021-03-18 09:45
 **/
public class MyLock implements Lock {

    MyAQS myAQS = new MyAQS(){

        @Override
        public boolean tryAcquire() {
            return owner.compareAndSet(null,Thread.currentThread());
        }

        @Override
        public boolean tryRelease() {
            return owner.compareAndSet(Thread.currentThread(),null);
        }
    };

    @Override
    public void lock() {
        myAQS.acquire();
    }


    @Override
    public boolean tryLock() {
        return myAQS.tryAcquire();
    }

    @Override
    public void unlock() {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
