package com.cs.springboot.designPatterns.proxy.dao;

/**
 * @author: cs
 * @date: 2019/08/15 9:23
 * @desc: Cglib代理的目标对象，没有实现任何接口
 */
public class MessageDao {

    public void save(){
        System.out.println("---data has saved---");
    }
}
