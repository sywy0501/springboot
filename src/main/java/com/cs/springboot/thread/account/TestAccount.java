package com.cs.springboot.thread.account;

/**
 * @author: cs
 * @date: 2019/08/23 16:05
 * @desc:
 */
public class TestAccount {

    public static void main(String[] args) {
        Account account = new Account();
        new Thread(new DrawThread(account,800),"取钱者").start();
        new Thread(new DepositThread(account,800),"存款者甲").start();
        new Thread(new DepositThread(account,800),"存款者乙").start();
        new Thread(new DepositThread(account,800),"存款者丙").start();
    }
}
