package com.cs.springboot.arithmetic.leetcode;

/**
 * @author: ChuShi
 * @date: 2020/04/09 9:36 上午
 * @desc: 最大回文字符串
 */
public class LeetCode5 {

    /**
     * 暴力破解法
     */
    public String longestPalindrome1(String s) {

        String tmp, result = null;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                tmp = s.substring(i, j);
                if (isPalindromic(tmp) && tmp.length() > max) {
                    result = tmp;
                    max = tmp.length();
                }
            }
        }
        return result;
    }

    private boolean isPalindromic(String s) {

        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /*
     * 最长公共子串
     */
    public String longestPalindrome2(String s) {
        if (s.equals("")) {
            return "";
        }
        String reserve = new StringBuffer(s).reverse().toString();
        int[][] a = new int[s.length()][s.length()];
        int maxLen = 0;
        int maxEnd = 0, beforeRev;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(i) == reserve.charAt(j)) {
                    if (i == 0 || j == 0) {
                        a[i][j] = 1;
                    } else {
                        a[i][j] = a[i - 1][j - 1] + 1;
                    }
                }
                if (a[i][j] > maxLen) {
                    beforeRev = s.length() - 1 - j;
                    if (beforeRev + a[i][j] - 1 == i) {
                        maxLen = a[i][j];
                        maxEnd = i;
                    }
                }

            }
        }
        return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }

    /*
     * 动态规划
     */
    public String longestPalindrome3(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        boolean[][] a = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            a[i][i] = true;
        }

        int maxLen = 1;
        int start = 0;

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        a[i][j] = true;
                    } else {
                        a[i][j] = a[i + 1][j - 1];
                    }
                } else {
                    a[i][j] = false;
                }

                if (a[i][j]) {
                    int curLen = j - i + 1;
                    if (curLen > maxLen) {
                        maxLen = curLen;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    public String longestPalindrome4(String s) {


        return null;
    }

    public static void main(String[] args) {
        LeetCode5 leetCode5 = new LeetCode5();
        String s = "aa";
        System.out.println(leetCode5.longestPalindrome3(s));
    }
}
