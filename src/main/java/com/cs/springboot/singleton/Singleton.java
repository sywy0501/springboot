package com.cs.springboot.singleton;

/**
 * @author: cs
 * @date: 2019/08/19 10:42
 * @desc:
 */
public enum Singleton {
    INSTANCE;

    public void method(){
        System.out.println("enum singleton...");
    }
}
