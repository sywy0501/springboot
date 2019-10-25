package com.cs.springboot.inter;

/**
 * @author: cs
 * @date: 2019/08/14 16:17
 * @desc: 接口特性测试
 */
public interface InterfaceTest {
    int i = 1;

    int insert();

    default void fun1(){
        System.out.println("default method");
    }

    static void fun2(){
        System.out.println("static method");
    }

    public static void main(String[] args) {
        InterfaceTest test ;
    }
}
