package com.cs.springboot.arithmetic;

/**
 * @author: ChuShi
 * @date: 2020/03/11 3:32 下午
 * @desc: http://www.ruanyifeng.com/blog/2013/05/Knuth–Morris–Pratt_algorithm.html
 */
public class KMP {

    public static int kmp(String str,String dest){
        char[] t= str.toCharArray();
        char[] p = dest.toCharArray();

        int i = 0;
        int j = 0;
        int[] next = getNext(dest);

        while (i<t.length&&j<p.length){
            if (j==-1||t[i]==p[j]){
                i++;
                j++;
            }else {
                j=next[j];
            }
        }
        if (j==p.length){
            return i-j;
        }else {
            return -1;
        }

    }

    public static int[] getNext(String ps){
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j<p.length-1){
            if (k==-1||p[j]==p[k]){
                if (p[++j]==p[++k]){
                    next[j] = next[k];
                }else {
                    next[j]=k;
                }
            }else {
                k=next[k];
            }
        }
        return  next;
    }
}
