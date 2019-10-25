package com.cs.springboot.spring.ioc;

/**
 * @author: cs
 * @date: 2019/09/05 2:38 下午
 * @desc:
 */
public class Test {
    public static void main(String[] args)throws Exception {
        String location = SpringIOC.class.getClassLoader().getResource("ioc.xml").getFile();
        SpringIOC bf = new SpringIOC(location);
        Wheel wheel = (Wheel) bf.getBean("wheel");
        System.out.println(wheel);
        Car car = (Car) bf.getBean("car");
        System.out.println(car);
    }
}
