package com.cs.springboot.proxy.dao;

/**
 * @author: cs
 * @date: 2019/08/14 21:58
 * @desc: 目标对象
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("data has saved");
    }
}
