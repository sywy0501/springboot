package com.cs.springboot.designPatterns.singleton;

/**
 * @author: cs
 * @date: 2019/08/19 10:43
 * @desc:
 */
public class Test {
    public static void main(String[] args) {
        Singleton.INSTANCE.method();
        int[] nums=new int[10];
        if (nums.length%2==0){
            System.out.println(nums.length%2);
        }

    }
}
