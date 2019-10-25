package com.cs.springboot.thread.account;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: cs
 * @date: 2019/08/23 16:39
 * @desc: lock
 */
public class AccountLock {
    //显示定义Lock对象
    private final Lock lock = new ReentrantLock();
    //获得指定lock对象对应的条件变量
    private final Condition con = lock.newCondition();

    private String accountNo;
    private double balance;
    //标识账户中是否有存款的标识
    private boolean flag = false;

    public AccountLock(){
        super();
    }

    public AccountLock(String accountNo,double balance){
        super();
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public void draw(double drawAmount){
        lock.lock();
        try {
            if (!flag){
                con.await();
            }else {
                System.out.println(Thread.currentThread().getName()+" 取钱："+drawAmount);
                balance = balance-drawAmount;
                System.out.println("余额： "+balance);
                flag = false;
                con.signalAll();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void deposit(double depositAmount){
        lock.lock();
        try {
            if (flag){
                con.await();
            }else {
                System.out.println(Thread.currentThread().getName()+"存钱 "+depositAmount);
                balance = balance+depositAmount;
                System.out.println("账户余额： "+balance);
                flag = true;
                con.notifyAll();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
