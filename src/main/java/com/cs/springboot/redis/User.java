package com.cs.springboot.redis;

/**
 * @author: ChuShi
 * @date: 2019/11/25 8:59 下午
 * @desc:
 */
public class User {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
