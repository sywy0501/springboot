package com.cs.springboot.serializable;

import sun.nio.ch.IOUtil;

import java.io.*;

/**
 * @author: cs
 * @date: 2019/08/19 18:39
 * @desc:
 */
public class Test {

    public static void main(String[] args) {
        Child child = new Child();
        child.setA("aa");
        child.setB("bb");

        System.out.println(child);

        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
            oos.writeObject(child);
        }catch (IOException e){
            e.printStackTrace();
        }

        File file = new File("tempFile");
        ObjectInputStream ois = null;
        try {

            ois = new ObjectInputStream(new FileInputStream(file));
            Child child1 = (Child) ois.readObject();
            System.out.println(child1);
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
