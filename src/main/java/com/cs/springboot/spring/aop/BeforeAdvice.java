package com.cs.springboot.spring.aop;

import java.lang.reflect.Method;

/**
 * @author: cs
 * @date: 2019/09/05 3:39 下午
 * @desc:
 */
public class BeforeAdvice implements Advice {
    private Object bean;
    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean,MethodInvocation methodInvocation){
        this.bean=bean;
        this.methodInvocation=methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在目标方法前调用通知
        methodInvocation.invoke();
        return method.invoke(bean,args);
    }
}
