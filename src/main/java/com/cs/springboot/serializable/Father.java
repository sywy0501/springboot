package com.cs.springboot.serializable;

/**
 * @author: cs
 * @date: 2019/08/19 18:36
 * @desc:
 */
public class Father {
    public String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "Father{" +
                "a='" + a + '\'' +
                '}';
    }

    Father(){

    }

    Father(String a){
        this.a = a;
        System.out.println("father :"+a);
    }
}
