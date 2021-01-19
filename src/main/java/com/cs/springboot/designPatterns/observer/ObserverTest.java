package com.cs.springboot.designPatterns.observer;



/**
 * @description:
 * @author: chushi
 * @create: 2021-01-19 11:35
 **/
public class ObserverTest {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        subject.setState(15);

        subject.setState(10);
    }
}
