package com.cs.springboot.huawei;

import java.util.Scanner;

/**
 * @author: cs
 * @date: 2019/10/18 2:31 下午
 * @desc:
 */
public class Test {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        int[] a = new int[10];
        int result1 = 0;
        int result2 = 0;
        for (int i = 0;i<10;i++){
            int next = in.nextInt();
            a[i]=next;
        }

        for (int j = 0;j<10;j++){
            for (int k = 0;k<10;k++){
                if (k==j){
                    continue;
                }
                for (int l=0;l<10;l++){
                    if (l==j||l==k){
                        continue;
                    }
                    if (j==0){
                        result1 = a[j]*a[j]+a[j]*a[k]-a[k]*a[k]+a[l];
                    }else {
                        result2 = a[j]*a[j]+a[j]*a[k]-a[k]*a[k]+a[l];
                        if (result2<result1){
                            result1 = result2;
                        }
                    }
                }
            }
        }
        System.out.println(result1);
    }
}
