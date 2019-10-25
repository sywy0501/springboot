package com.cs.springboot.aqs;

import java.io.Serializable;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

/**
 * @author: cs
 * @date: 2019/09/12 9:39 上午
 * @desc:
 */
public class Mutex implements Serializable {

    //静态内部类，继承AQS
    private static class Sync extends AbstractQueuedLongSynchronizer {
        //是否处于占用状态
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        //当状态为0时获取锁，CAS操作成功，则state状态为1
        @Override
        protected boolean tryAcquire(long arg) {
            if (compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //释放锁，将同步状态置为0
        @Override
        protected boolean tryRelease(long arg) {
            if (getState()==0){
                throw  new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }

    private final Sync sync = new Sync();
    //加锁操作，代理到acquire上，acquire会调用我们重写的tryAcquire方法
    public void lock(){
        sync.acquire(1);
    }

    public boolean tryLock(){
        return sync.tryAcquire(1);
    }

    //释放锁，代理到release上，release会调用我们重写的tryRelease方法
    public void unlock(){
        sync.release(1);
    }

    public boolean isLocked(){
        return sync.isHeldExclusively();
    }
}
