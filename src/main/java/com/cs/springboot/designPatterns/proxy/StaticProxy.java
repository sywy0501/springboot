package com.cs.springboot.proxy;

import com.cs.springboot.proxy.dao.IUserDao;

/**
 * @author: cs
 * @date: 2019/08/14 18:07
 * @desc: 代理模式 通过代理对象来访问或者操作目标对象
 *       （不要随意修改别人已经写好的代码或方法，通过代理的方式扩展）
 *        静态代理 代理对象接收传入的对象并处理，处理代理对象逻辑的同时要执行原本目标对象的逻辑
 *        优点：不修改目标对象的前提下扩展功能
 *        缺点：代理对象需要与目标对象实现同样的接口，因此会有很多代理类。一旦接口增加方法，目标对象和代理对象均要维护
 */
public class StaticProxy implements IUserDao {

    //接收保存目标对象
    private IUserDao target;
    public StaticProxy(IUserDao target){
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开始事务...");
        //执行目标对象的方法
        target.save();
        System.out.println("提交事务...");
    }
}
