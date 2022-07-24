package com.dn.algorithm.leetcode;

import com.alibaba.fastjson.JSONObject;
import com.dn.bean.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author dingning
 * @date 2022/7/17 上午 10:21
 * 算法第十周
 **/
public class TenWeek {


    //118. 杨辉三角 todo 优化
    private static List<List<Integer>> leetCode118(int numRows) {
        numRows = 10;
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    list.add(1);
                } else {
                    list.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(list);
        }
        System.out.println(JSONObject.toJSONString(ret));
        return ret;
    }

    //判断是否是回文串
    private static boolean leetCode125() {
        String s = "race a car";
        //首先先把s合并英文字符串
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                builder.append(s.charAt(i));
            }
        }
        s = builder.toString().toLowerCase();
        return s.equals(builder.reverse().toString().toLowerCase());

//        int left = 0;
//        int right = s.length() - 1;
//        //比对每一个字符
//        while(left < right){
//            if(s.charAt(left) != s.charAt(right)){
//                return false;
//            }
//            left++;
//            right--;
//        }
//        return true;
    }

    private static String leetCode168(int cn) {
        StringBuilder sb = new StringBuilder();
        while (cn > 0) {
            cn--;
            sb.append((char) (cn % 26 + 'A'));
            cn /= 26;
        }
        sb.reverse();
        return sb.toString();
    }

    private static int leetCode169() {
        int[] nums = new int[]{3, 2, 3};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.get(num) != null) {
                map.put(num, map.get(num) + 1);
                if (map.get(num) > nums.length / 2) {
                    return num;
                }
            } else {
                map.put(num, 1);
            }
        }
        return 0;
    }

    //171. Excel 表列序号
    private static int leetCode171() {
        String s = "ZY";
        int sum = 0;
        int multiple = (int) Math.pow(26, s.length() - 1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sum += (c - 'A' + 1) * multiple;
            multiple /= 26;
        }
        return sum;
    }

    //移动0
    private static int[] leetCode283() {
        int[] nums = new int[]{0, 1, 0, 3, 12};
//        int[] nums = new int[]{1,0,1};
        if (nums.length == 0) {
            return new int[0];
        }
        int tmp = 0; //上一个元素所在的位置
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                if (nums[i + 1] != 0) {
                    nums[tmp] = nums[i + 1];
                    nums[i + 1] = 0;
                    tmp += 1;
                }
            } else {
                tmp += 1;
            }
        }
        return nums;
    }

    public void moveZeroes(int[] nums) {
        //1.check
        if (nums == null || nums.length < 2) {
            return;
        }
        //2.init
        int first = 0;
        int second = 0;
        //3.遍历
        while (first < nums.length) {
            if (nums[first] != 0) {
                nums[second++] = nums[first];
            }
            ++first;
        }
        //3.对非零元素后面补零
        for (int i = second; i < nums.length; ++i) {
            nums[i] = 0;
        }
    }


    //169. 多数元素
    private static int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.get(num) != null) {
                map.put(num, map.get(num) + 1);
                if (map.get(num) > nums.length / 2) {
                    return num;
                }
            } else {
                map.put(num, 1);
            }
        }
        return nums[0];
    }

    //todo:dingning 2022/7/19 下午 11:42  相关算法的解法优化，以及上次提及的几种解题思路

    //输入：n = 00000010100101000001111010011100
    //输出：964176192 (00111001011110000010100101000000)
    private static int leetCode190() {
        String s = "00111001011110000010100101000000";
        System.out.println(Integer.parseInt(new StringBuilder().append(s).reverse().toString(), 2));
        return Integer.parseInt(s, 2);

    }

    private static boolean leetCode202(int n) {
        return checkHappy(n, new ArrayList<>());
    }

    private static boolean checkHappy(int n, ArrayList<Integer> list) {
        //要找到递归结束的条件，如果一直循环则返回false
        if (n == 1) {
            return true;
        }
        if (list.contains(n)) {
            return false;
        } else {
            list.add(n);
        }
        //求和
        int sum = (n % 10) * (n % 10);
        while (n / 10 > 0) {
            n = n / 10;
            sum = sum + (n % 10) * (n % 10);//往前递推的和平方
        }
        n = sum;
        return checkHappy(n, list);
    }

    private static ListNode<Integer> leetCode203(ListNode<Integer> head, int val) {
        head = new ListNode<>(7);
        head.next = new ListNode<>(7);
        head.next.next = new ListNode<>(7);
        head.next.next.next = new ListNode<>(7);
        head.next.next.next.next = new ListNode<>(4);
        head.next.next.next.next.next = new ListNode<>(5);
        head.next.next.next.next.next.next = new ListNode<>(6);
        if (head == null) {
            return null;
        }
        /** ListNode<Integer> dummyHead = new ListNode<>(0);
         dummyHead.next = head;
         ListNode<Integer> cur = dummyHead;
         while (cur.next != null) {
         if (cur.next.value == val) {
         cur.next = cur.next.next;
         } else {
         cur = cur.next;
         }
         }

         return dummyHead.next;*/

        //递归写法，很难想到
        head.next = leetCode203(head.next, val);
        return head.value == val ? head.next : head;

    }

    private static boolean leetCode205(String s, String t) {
        s = "bbbaaaba";
        t = "aaabbbba";
        if (s.length() == 0 || t.length() == 0) {
            return false;
        }
        //每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                if (map.containsValue(t.charAt(i))) {
                    return false;
                }
                map.put(c, t.charAt(i));
            }
            if (!(t.charAt(i) == c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean leetCode219(int[] nums, int k) {
        nums = new int[]{1, 0, 1, 1};
        k = 1;
        //判定重复元素出现的位置
        if (nums.length == 0) {
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (Math.abs(i - map.get(nums[i])) <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }

    //todo:dingning 2022/7/22 下午 11:29  需要优化,时间复杂度太高且适配不了unicode字符
    private static boolean leetCode242(String s, String t) {
        s = "rat";
        t = "car";
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (map.containsKey(c)) {
                if (map.get(c) == 1) {
                    map.remove(c);
                } else {
                    map.put(c, map.get(c) - 1);
                }
            } else {
                return false;
            }
        }
        return map.size() == 0;
    }

    //反转字符串
    private static void leetCode344(char[] s) {
        s = new char[]{'h', 'e', 'l', 'l', 'o'};
        int left = 0;
        int right = s.length - 1;
        char tmp;
        while (left < right) {
            tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }

    private static boolean leetCode290(String pattern, String s){
        pattern = "abba";
        s = "dog cat cat fish";
        if(pattern.length() == 0 || s.length() == 0){
            return false;
        }
        HashMap<Character, String> map = new HashMap<>();
        String[] chars = s.split(" ");
        if(pattern.length() != chars.length){
            return false;
        }
        for (int i = 0; i < pattern.length(); i++){
            if( !map.containsKey(pattern.charAt(i)) && !map.containsValue(chars[i])){
                map.put(pattern.charAt(i), chars[i]);
            }
            if(!chars[i].equals(map.get(pattern.charAt(i)))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(leetCode290("",""));
    }
}
