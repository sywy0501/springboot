package com.cs.springboot.thread.account;

/**
 * @author: cs
 * @date: 2019/08/23 11:29
 * @desc:
 */
public class Account {
    private String accountNo;
    private double balance;
    //是否有存款的标识
    private boolean flag = false;

    public Account(){
        super();
    }

    public Account(String accountNo,double balance){
        super();
        this.accountNo = accountNo;
        this.balance = balance;
    }

    /**
     * @author: cs
     * @date: 2019/8/23 15:45
     * @param: [drawAmount]
     * @return: void
     * @desc: 取钱
     */
    public synchronized void draw(double drawAmount){
        try {
            if (!flag){
                this.wait();
            }else {
                //取钱
                System.out.println(Thread.currentThread().getName()+" 取钱:"+drawAmount);
                balance = balance-drawAmount;
                System.out.println("余额: "+balance);
                //将标识账户是否已有存款的标志设为false
                flag = false;
                this.notifyAll();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @author: cs
     * @date: 2019/8/23 16:04
     * @param: [depositAmount]
     * @return: void
     * @desc: 存钱
     */
    public synchronized void deposit(double depositAmount){
        try {
            if (flag){
                this.wait();
            }else {
                System.out.println(Thread.currentThread().getName()+" 存钱: "+depositAmount);
                balance = balance+depositAmount;
                System.out.println("账户余额为： "+balance);
                flag = true;
                this.notifyAll();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
