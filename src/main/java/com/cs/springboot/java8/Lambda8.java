package com.cs.springboot.java8;


import com.cs.springboot.java8.function.Inter;
import com.cs.springboot.java8.function.InterImpl;

import java.util.function.Supplier;

/**
 * @author: ChuShi
 * @date: 2019/11/25 1:23 下午
 * @desc: java8的lambda表达式
 */
public class Lambda8 {
    public static void main(String[] args) {
        new Thread(()-> System.out.println("hello")).start();

        Supplier<InterImpl> supplier = InterImpl::new;
    }
}
