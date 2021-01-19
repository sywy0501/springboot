package com.cs.springboot.designPatterns.prototype;

/**
 * @description:
 * @author: chushi
 * @create: 2021-01-19 15:28
 **/
public class Rectangle extends Shape{

    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Rectangle");
    }
}
