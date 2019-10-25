package com.cs.springboot.thread;

/**
 * @author: cs
 * @date: 2019/08/22 9:44
 * @desc: 多线程通信----共享变量
 */
public class SharedVariable {

    /*int index = 0;

    private class InnerThread extends Thread{
        public synchronized void run(){
            System.out.println(Thread.currentThread().getName()+" is running and index is "+index++);
        }
    }

    public Thread getThread(){
        return new InnerThread();
    }

    public static void main(String[] args) {
        SharedVariable sharedVariable = new SharedVariable();
        sharedVariable.getThread().start();
        sharedVariable.getThread().start();
    }*/

    protected boolean hasDataToProcess = false;

    public synchronized boolean hasDataToProcess(){
        return this.hasDataToProcess;
    }

    public synchronized void setHasDataToProcess(boolean hasData){
        this.hasDataToProcess = hasData;
    }

    public static void main(String[] args) {
        SharedVariable sharedVariable = new SharedVariable();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" "+sharedVariable.hasDataToProcess);
                sharedVariable.setHasDataToProcess(true);
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" "+sharedVariable.hasDataToProcess);
            }
        });

        thread.start();
        thread1.start();
    }
}
