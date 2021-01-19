package com.cs.springboot.designPatterns.prototype;

/**
 * @description:
 * @author: chushi
 * @create: 2021-01-19 15:41
 **/
public class Circle extends Shape{

    public Circle(){
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Circle");
    }
}
