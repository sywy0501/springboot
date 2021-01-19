package com.cs.springboot.designPatterns.prototype;

/**
 * @description:
 * @author: chushi
 * @create: 2021-01-19 15:40
 **/
public class Square extends Shape{

    public Square(){
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Square");
    }
}
