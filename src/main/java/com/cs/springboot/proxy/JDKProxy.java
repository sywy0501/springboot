package com.cs.springboot.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: cs
 * @date: 2019/08/14 22:33
 * @desc: JDK代理  利用JDK的API动态在内存中构建代理对象,是面向接口的代理模式
 *        代理对象不需要实现接口，但是目标对象一定要实现接口
 */
public class JDKProxy {

    //维护一个目标对象
    private Object target;
    public JDKProxy(Object target){
        this.target = target;
    }

    //给目标对象生成代理对象
    public Object getJDKProxy(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        System.out.println("开始事务2...");
                        Object returnValue = method.invoke(target,args);
                        System.out.println("提交事务2...");
                        return returnValue;
                    }
                }
        );
    }
}
