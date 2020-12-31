package com.cs.springboot.jvm.oom;

/**
 * @description:
 * @author: chushi
 * @create: 2020-12-18 13:58
 **/
public class StackSOFDemo {

    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackSOFDemo stackSOFDemo = new StackSOFDemo();
        try {
            stackSOFDemo.stackLeak();
        }catch (Exception e){
            System.out.println("stack length:"+stackSOFDemo.stackLength);
            e.printStackTrace();
        }
    }
}
