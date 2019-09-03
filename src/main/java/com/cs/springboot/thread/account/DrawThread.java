package com.cs.springboot.thread.account;

/**
 * @author: cs
 * @date: 2019/08/23 15:59
 * @desc: 存钱
 */
public class DrawThread implements Runnable {

    private Account account;
    private double drawAmount;

    public DrawThread(Account account,double drawAmount){
        super();
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
        for (int i = 0;i<100;i++){
            account.draw(drawAmount);
        }
    }
}
