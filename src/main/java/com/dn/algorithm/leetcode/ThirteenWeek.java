package com.dn.algorithm.leetcode;

/**
 * @author skyline
 * @version 1.0
 * @description:
 * @date 2022/8/8 19:24 - 2022年8月15日19:25:28
 */
public class ThirteenWeek {

    //判断是否完全平方数
    private static boolean leetCode367(int num){
            int left = 0;
            int right = num;
            int mid;
            while(left <= right){
                mid = left  + (right - left) / 2;
                long square = (long) mid * mid;  //需要用long类型解决int溢出问题
                if(square > num){
                    right = mid - 1;
                } else if (square < num) {
                    left = mid + 1;
                } else {
                    return true;
                }
            }
            return false;
    }


    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            System.out.println( i+ "" + leetCode367(i));
//        }
        System.out.println(leetCode367(2147483647));
    }
}
