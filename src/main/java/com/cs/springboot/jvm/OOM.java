package com.cs.springboot.jvm;

/**
 * @author: cs
 * @date: 2019/10/25 11:13
 * @desc:
 */
public class OOM {
    private static int i=0;
    private static void test(){
        test();
        i++;
        System.out.println(i);
    }

    public static void main(String[] args) {
        //-Xss1m 设置每个线程的堆栈大小为1M
        //循环递归调用，JVM不得不在虚拟机栈上分配栈帧空间，导致StackOverflowError
        //test();

        //-Xms8m 设置JVM的初始内存为8M
        //-Xmx8m 设置JVM的最大内存为8M 初始内存和最大内存设置相同的值可以避免每次JVM垃圾回收之后重新分配内存
        //直接新建一个JVM初始值大小的数组就会导致 java.lang.OutOfMemoryError: Java heap space
        /*StringBuffer buffer = new StringBuffer("a");
        while (true){
            try {
                buffer.append("b");
            }catch (OutOfMemoryError e){
                e.printStackTrace();
                break;
            }
        }*/
        String a = "hello";
        System.out.println(a.length());
        System.out.println(a.codePointCount(0,a.length()));
    }
}
