package com.cs.springboot.proxy;

import com.cs.springboot.proxy.dao.IUserDao;
import com.cs.springboot.proxy.dao.MessageDao;
import com.cs.springboot.proxy.dao.UserDao;

/**
 * @author: cs
 * @date: 2019/08/14 22:12
 * @desc: 测试类
 */
public class Test {

    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDao();

        //静态代理 代理对象，把目标对象传给代理对象，建立代理关系
        StaticProxy staticProxy = new StaticProxy(target);
        staticProxy.save();

        //JDK动态代理
        System.out.println(target.getClass());
        IUserDao JDKPoxy = (IUserDao) new JDKProxy(target).getJDKProxy();
        System.out.println(JDKPoxy.getClass());
        JDKPoxy.save();

        //Cglib代理
        MessageDao messageDao = new MessageDao();
        MessageDao cglibProxy = (MessageDao) new CglibProxy(messageDao).getProxyInstance();
        cglibProxy.save();
    }
}
