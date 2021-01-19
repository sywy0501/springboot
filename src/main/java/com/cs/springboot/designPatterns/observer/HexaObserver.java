package com.cs.springboot.designPatterns.observer;

/**
 * @description:
 * @author: chushi
 * @create: 2021-01-18 18:30
 **/
public class HexaObserver extends Observer{

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String:"+Integer.toHexString(subject.getState()));
    }
}
