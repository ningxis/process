package com.dn.algorithm.leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author skyline
 * @version 1.0
 * @description:
 * @date 2022/7/5 16:17
 */
public class EightWeek {

    public static void main(String[] args) {
//        System.out.println(leetCode20ByHashMap("()"));
//        System.out.println(leetCode20("]"));
//        System.out.println(Arrays.toString(leetCode283(new int[]{0, 1, 0, 3, 12})));
    }

    public static void testLeetCode69() {
        for (int i = 0; i < 101; i++) {
            System.out.println(i + ":" + leetCode69(i));
        }
    }

    private static int leetCode69(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        int left = 1;
        int right = x;

        while (right >= left) {
            int mid = left + ((right - left) >> 1);
            if (x / mid == mid) {
                return mid;
            }
            if (x / mid > mid) {
                left = mid + 1;
            }
            if (x / mid < mid) {
                right = mid - 1;
            }

        }
        return right;
    }

    //给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效
    private static boolean leetCode20(String s) {
        //先判断边界
        if(s.length() == 0 || s.length() % 2 == 1 ){
            return false;
        }
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            switch (a) {
                case '(':
                    stack.push("(");
                    break;
                case '{':
                    stack.push("{");
                    break;
                case '[':
                    stack.push("[");
                    break;
                case ']':
                    if (!stack.isEmpty() && stack.peek().equals("[")) {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case '}':
                    if (!stack.isEmpty() && stack.peek().equals("{")) {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case ')':
                    if (!stack.isEmpty() && stack.peek().equals("(")) {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }

    private static boolean leetCode20ByHashMap(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        HashMap<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if(map.containsKey(c)){
                stack.push(c);
            }else{
                if(stack.isEmpty() ||  c != map.get(stack.peek()) ){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private static int[] leetCode283(int[] nums){
        if(nums.length == 0){
            return new int[]{};
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if(nums[i + 1] != 0 && nums[i] ==0){
                nums[i] = nums[i + 1];
                nums[i + 1] = 0;
            }
        }
        return nums;
    }
}
