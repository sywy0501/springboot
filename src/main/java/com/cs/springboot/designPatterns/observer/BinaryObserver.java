package com.cs.springboot.designPatterns.observer;

/**
 * @description:
 * @author: chushi
 * @create: 2021-01-18 17:37
 **/
public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String:"+Integer.toBinaryString(subject.getState()));
    }
}
