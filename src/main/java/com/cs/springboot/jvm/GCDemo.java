package com.cs.springboot.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: cs
 * @date: 2019/10/25 15:10
 * @desc: 写一段程序，运行时触发5次ygc，3次fgc，3次ygc，1次ygc
 *        -Xmx41m 最大堆
 *        -Xms41m 最小堆
 *        -Xmn10m 新生代
 *        -XX:+UseParallelGC
 */
public class GCDemo {

    private static final int UNIT_MB = 1024*1024;

    /*public static void getJvmInfo(){
        System.out.println("------------------JVM-Info-start-------------------------");
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage mu = memoryMXBean.getHeapMemoryUsage();

        System.out.println("heapInfo:"+mu);
        System.out.println("初始化堆:"+mu.getInit()/1024/1024+"Mb");
        System.out.println("最大堆值:"+mu.getMax()/1024/1024+"Mb");
        System.out.println("已用堆值:"+mu.getUsed()/1024/1024+"Mb");

        MemoryUsage none = memoryMXBean.getNonHeapMemoryUsage();
        System.out.println("non-heap Info(非堆内存):"+none);

        List<String> args = ManagementFactory.getRuntimeMXBean().getInputArguments();
        System.out.println("运行时VM参数:"+args);

        System.out.println("运行时总内存"+Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println("运行时空闲空间"+Runtime.getRuntime().freeMemory()/1024/1024);
        System.out.println("运行时最大内存"+Runtime.getRuntime().maxMemory()/1024/1024);

        System.out.println("-----------------JVM-Info-end-------------------");
        System.out.println("--");
        System.out.println("--");
        System.out.println("--");
    }

    public static void main(String[] args) {
        getJvmInfo();
        int count = 1;
        List caches = new ArrayList();
        System.out.println("--初始化时已用堆值"+ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
        for (int i = 1;i<=12;i++){
            if (i==11){
                System.out.println("--caches准备添加第11次，old区内存不够，开始full GC前先执行minor GC第"+5+"次,FGC 第1次(触发条件：【MinorGC后存活的对象超过了老年代剩余空间】)");
            }
            caches.add(new byte[3*UNIT_MB]);
            if (i%2==0&&i!=10){

                System.out.println("--caches添加第"+i+"次后，eden 的内存不够，开始minor GC第"+count+"次");
                getJvmInfo();
                count++;
            }else {
                System.out.println("--caches添加第"+i+"次");
                getJvmInfo();
            }
        }

        System.out.println("目前整个堆内存已经36m多，Young区6M多，Old区最大值为32M");
        //释放空间，重新添加，防止数组对象的大小超过堆的大小
        caches.remove(0);
        System.out.println("--Full GC开始 第2次（触发条件：晋升到老年代的代销超过了老年代的剩余大小）");
        caches.add(new byte[3*UNIT_MB]);
        System.out.println("本次Full GC移植了Young区的一部分到Old区，导致Young区还有3M左右");
        for (int i = 0;i<8;i++){
            //这是为了下次FGC之后，直接减少老年代的内存大小，从而正常YGC
            caches.remove(0);
        }
        System.out.println("--FGC开始 第3次");
        caches.add(new byte[3*UNIT_MB]);

        for (int i = 0;i<6;i++){
            caches.add(new byte[3*UNIT_MB]);
        }
    }*/

    public static void main(String[] args) {

        MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = mxBean.getHeapMemoryUsage();
        System.out.println("初始化堆：" + (heapMemoryUsage.getInit() >> 10) + "K");
        System.out.println("已用堆：" + (heapMemoryUsage.getUsed() >> 10) + "K");
        System.out.println("最大堆：" + (heapMemoryUsage.getMax() >> 10) + "K");

        List<byte[]> bytes = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            byte[] a = new byte[3 * UNIT_MB];
            bytes.add(a);
///            System.out.println("剩余：" + (Runtime.getRuntime().freeMemory() >> 10) + "K");
        }

        bytes.remove(0);
        bytes.add(new byte[3 * UNIT_MB]);

        bytes.subList(0, 8).clear();

        bytes.add(new byte[3 * UNIT_MB]);
        for (int i = 0; i < 6; i++) {
            bytes.add(new byte[3 * UNIT_MB]);
        }
    }

}
