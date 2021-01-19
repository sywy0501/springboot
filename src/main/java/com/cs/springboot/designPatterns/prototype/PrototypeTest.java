package com.cs.springboot.designPatterns.prototype;
/**
* @description:
*
* @author: chushi
*
* @create: 2021-01-19 16:38
**/
public class PrototypeTest {

    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape1 = ShapeCache.getShape("1");
        System.out.println("Shape1 : "+clonedShape1.getType());

        Shape cloneShape2 = ShapeCache.getShape("2");
        System.out.println("Shape1 : "+cloneShape2.getType());

        Shape cloneShape3 = ShapeCache.getShape("3");
        System.out.println("Shape2 : "+cloneShape3.getType());
    }
}
