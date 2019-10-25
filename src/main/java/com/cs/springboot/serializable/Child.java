package com.cs.springboot.serializable;

import java.io.Serializable;

/**
 * @author: cs
 * @date: 2019/08/19 18:37
 * @desc:
 */
public class Child extends Father implements Serializable {


    private static final long serialVersionUID = -8276816422754732043L;
    private String b;

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Child{" +
                "b='" + b + '\'' +
                "a='" + a + '\'' +
                '}';
    }
}
