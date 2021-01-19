package com.cs.springboot.designPatterns.observer;

/**
 * @description:
 * @author: chushi
 * @create: 2021-01-18 17:36
 **/
public abstract class Observer {

    protected  Subject subject;
    public abstract void update();
}
