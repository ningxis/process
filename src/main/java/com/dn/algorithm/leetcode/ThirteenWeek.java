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
//        System.out.println(leetCode367(2147483647));//不用long的话会死循环
    }

    // 反转字符串中的元音字母,双指针
    private static String leetCode345(String s){
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        //这里的双指针边界问题要判清楚
        while(left < right){
            while(left < right && !isVowel(chars[left])){
                left++;
            }
            while(left > right && !isVowel(chars[right])){
                right--;
            }
            char a = chars[left];
            chars[left] = chars[right];
            chars[right] = a;
            left++;
            right--;
        }
        return new String(chars);
    }


    private static boolean isVowel(char c){
       return "aeiouAEIOU".indexOf(c) >= 0; //判断是否是元音字符
    }


    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            System.out.println( i+ "" + leetCode367(i));
//        }
        System.out.println(leetCode345("hello"));
    }
}
