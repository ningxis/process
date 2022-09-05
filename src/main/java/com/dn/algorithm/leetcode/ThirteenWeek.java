package com.dn.algorithm.leetcode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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


    private static int leetCode191(int n){
        n = 00000000000000000000000000001011;
        int sum = 0;
        while(n != 0){
            n = n & (n - 1);
            sum++;
        }
        return sum;
    }


    //用位运算实现加法，
    /**
     * @description:
     * @author skyline
     * @date 2022/8/8 21:26
     * 我们的计算机其实就是通过上述的位运算实现加法运算的（通过加法器，加法器就是使用上述的方法实现加法的），
     * 而程序语言中的+ - * /运算符只不过是呈现给程序员的操作工具，计算机底层实际操作的永远是形如0101的位，
     * 所以说位运算真的很重要！
     */
    private static void sum(int num1, int num2){
        // 迭代写法
            int sum = num1 ^ num2;
            int carry = (num1 & num2) << 1;
            while(carry != 0){
                int a = sum;
                int b = carry;
                sum = a ^ b;
                carry = (a & b) << 1;
            }
    }
    private static int add(int num1, int num2){
        // 递归写法
            if(num2 == 0)
                return num1;
            int sum = num1 ^ num2;
            int carry = (num1 & num2) << 1;
            return add(sum, carry);
    }

    private static boolean leetCode383(String ransomNote,String magazine){
        for (int i = 0; i < magazine.length(); i++){
            ransomNote.replace(String.valueOf(magazine.charAt(i)),"");
        }
        System.out.println(ransomNote);
        return true;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String loginTime = "2022-8-5 10:13:55";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        if(calendar.getTime().getTime() > dft.parse(loginTime).getTime()){
            System.out.println("____________一个月之前登陆了__________");
        }
    }
}
