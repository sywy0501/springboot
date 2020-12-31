package com.cs.springboot.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Java 堆溢出异常测试
 * @author: chushi
 * @create: 2020-12-17 19:24
 **/
public class HeapOOMDemo {
    static class OOMObject{
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
