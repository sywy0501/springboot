package com.cs.springboot.designPatterns.observer;

/**
 * @description:
 * @author: chushi
 * @create: 2021-01-18 18:28
 **/
public class OctalObserver extends Observer{

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal String: "+Integer.toOctalString(subject.getState()));
    }
}
