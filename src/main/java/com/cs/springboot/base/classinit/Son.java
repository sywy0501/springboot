package com.cs.springboot.base.classinit;

/**
 * @author: ChuShi
 * @date: 2019/12/25 6:03 下午
 * @desc:
 */
public class Son extends Father {
    private int i=test();
    private static int j = method() ;

    static {
        System.out.print(6+" ");
    }
    Son(){
        System.out.print(7+" ");
    }
    {
        System.out.print(8+" ");
    }
    public int test(){
        System.out.print(9+" ");
        return 1;
    }

    public static int method(){
        System.out.print(10+" ");
        return 1;
    }

    public static void main(String[] args) {
        Son son1 = new Son();
        System.out.println();
        Son son2 = new Son();
    }
}
