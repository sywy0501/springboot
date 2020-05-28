package com.cs.springboot.spring.aop;

/**
 * @author: cs
 * @date: 2019/09/05 3:51 下午
 * @desc:
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHelloWorld() {
        System.out.println("hello world");
    }
}
