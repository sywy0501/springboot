package com.cs.springboot.algorithm.jianzhiOffer;

/**
 * @author: cs
 * @date: 2020/04/01 21:36
 * @desc:
 */
public class Questions3 {

    public int[] findRepeatedNum(int[] nums){

        int[] result = new int[nums.length];
        int tmp,j=0;

        for (int i=0;i<nums.length;i++){

            while (i!=nums[i]){
                if (nums[i]==nums[nums[i]]){
                    result[j]=nums[i];
                    j++;
                    break;
                }else {
                    tmp = nums[nums[i]];
                    nums[nums[i]] = nums[i];
                    nums[i] = tmp;
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        Questions3 questions3 = new Questions3();
        int[] a = {3,2,4,5,5,0,2};
        int[] b = questions3.findRepeatedNum(a);
        for (int i=0;i<b.length;i++){
            System.out.println(b[i]);
        }


    }
}
