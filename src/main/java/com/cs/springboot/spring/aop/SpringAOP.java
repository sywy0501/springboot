package com.cs.springboot.spring.aop;

import java.lang.reflect.Proxy;

/**
 * @author: cs
 * @date: 2019/09/05 3:41 下午
 * @desc:
 */
public class SpringAOP {
    public static Object getProxy(Object bean,Advice advice) {
        return Proxy.newProxyInstance(SpringAOP.class.getClassLoader(),bean.getClass().getInterfaces(),advice);
    }

}
