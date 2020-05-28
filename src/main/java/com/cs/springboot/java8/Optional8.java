package com.cs.springboot.java8;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author: ChuShi
 * @date: 2019/11/25 1:22 下午
 * @desc: java8的optional
 */
public class Optional8 {

    public static void main(String[] args) {

        String str ="null";

        //Optional.of(str);
        System.out.println(Optional.ofNullable(str).orElse("zero"));
        System.out.println(Optional.ofNullable(str).orElseGet(()->"stringSupplier"));
        Optional.ofNullable(str).ifPresent(System.out::println);

    }
}
