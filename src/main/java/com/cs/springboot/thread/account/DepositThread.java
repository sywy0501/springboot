package com.cs.springboot.thread.account;

/**
 * @author: cs
 * @date: 2019/08/23 16:01
 * @desc: 存钱
 */
public class DepositThread implements Runnable {

    private Account account;
    private double depositAmount;

    public DepositThread(Account account,double depositAmount){
        super();
        this.account = account;
        this.depositAmount = depositAmount;
    }

    @Override
    public void run() {
        for (int i = 0;i<100;i++){
            account.deposit(depositAmount);
        }
    }
}
