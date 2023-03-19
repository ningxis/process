package com.dn.algorithm.leetcode.march;

/**
 * @author dingning
 * @date 2023/3/15 下午 07:11
 **/
public class LeetCode {


    //hello world
    public static void main(String[] args) {

    }

    private static int[] countBits(int n){
        int[] bits = new int[n + 1];
        for(int i = 0; i < bits.length; i++){
            bits[i] = countOneBit(i);
        }
        return bits;
    }

    private static int countOneBit(int x){
        int one = 0;
        while(x > 0){
            x = x & (x - 1);
            one++;
        }
        return one;
    }

}
