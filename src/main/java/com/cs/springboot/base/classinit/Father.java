package com.cs.springboot.base.classinit;

/**
 * @author: ChuShi
 * @date: 2019/12/25 6:03 下午
 * @desc:
 */
public class Father {

    private int i = test();
    private static int j = method();

    static {
        System.out.print(1+" ");
    }

    Father(){
        System.out.print(2+" ");
    }

    {
        System.out.print(3+" ");
    }

    public int test(){
        System.out.print(4+" ");
        return 1;
    }

    public static int method(){
        System.out.print(5+" ");
        return 1;
    }
}
