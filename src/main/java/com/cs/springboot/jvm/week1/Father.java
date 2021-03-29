package com.cs.springboot.jvm.week1;

/**
 * @description:
 * @author: chushi
 * @create: 2021-02-01 16:21
 **/
public class Father {

    public static void main(String[] args) {

        isAct();
    }

    private static void isAct(){
        Son son = new Son();
        son.load();
    }
}
