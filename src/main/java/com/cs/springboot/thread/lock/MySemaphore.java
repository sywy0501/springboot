package com.cs.springboot.thread.lock;

/**
 * @description:
 * @author: chushi
 * @create: 2021-03-19 10:02
 **/
public class MySemaphore {

    MyAQS aqs = new MyAQS(){

        @Override
        public int tryAcquireShared() {
            for (;;){
                int count = getStatus().get();
                int cur = count - 1;
                if (cur < 0||count <=0){
                    return -1;
                }

                if (getStatus().compareAndSet(count,cur)){
                    return 1;
                }
            }
        }

        @Override
        public boolean tryReleaseShared() {
            return getStatus().incrementAndGet()>=0;
        }
    };

    public MySemaphore(int count){
        aqs.getStatus().set(count);
    }

    public void acquire(){
        aqs.tryAcquireShared();
    }

    public void release(){
        aqs.tryReleaseShared();
    }
}
