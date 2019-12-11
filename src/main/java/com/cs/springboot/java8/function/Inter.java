package com.cs.springboot.java8.function;

/**
 * @author: ChuShi
 * @date: 2019/11/25 1:59 下午
 * @desc:
 */
@FunctionalInterface
public interface Inter {

    static void print(){
        System.out.println("static");
    }

    default void ptintf(){
        System.out.println("default");
    }

    void println(String str);
}
