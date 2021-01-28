package com.cs.springboot.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: cs
 * @date: 2019/08/15 9:18
 * @desc: Cglib代理 是字节码底层继承要代理的类实现的 在运行期动态扩展Java类，Spring在运行期通过继承要被代理的类，重写父类方法完成
 *        代理的类和对象不能为final否则报错，目标对象如果为final/static，就不会被拦截，无法执行代理类的业务方法
 */
public class CglibProxy implements MethodInterceptor {
    //维护目标对象
    private Object target;

    public CglibProxy(Object target){
        this.target = target;
    }

    //给目标对象创建代理对象
    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类（代理对象）
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务...");
        //执行目标对象的方法
        Object returnValue = method.invoke(target,objects);
        System.out.println("提交事务...");
        return returnValue;
    }
}
