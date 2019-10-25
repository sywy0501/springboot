package com.cs.springboot.huawei;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author: cs
 * @date: 2019/10/23 7:15 下午
 * @desc:
 */
public class Test2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        byte[] s = a.getBytes();
        int res = 0;
        int start = 0;
        Stack<Integer> m = new Stack<>();
        for (int i=0;i<a.length();i++){
            if (s[i]==40){
                m.push(i);
            }else {
                m.pop();
                if (m.empty()){
                    m.push(i);
                }else {
                    res = Math.max(res,i-m.firstElement());
                }
            }
        }
        System.out.println(res);
    }
}
