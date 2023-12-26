package com.dn.algorithm.leetcode.hot100;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author dingning
 * @date 2023/12/19 下午 03:38
 **/
public class first {


    public static void main(String[] args) {
        test01();
    }


    /*
        1. 两数之和
        给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[]{};
        if (nums == null || nums.length == 0) {
            return res;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(target - num)) {
                return new int[]{map.get(target - num), i};
            } else {
                map.put(num, i);
            }
        }
        return new int[]{};
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    /*
        2. 两数相加
        给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
        请你将两个数相加，并以相同形式返回一个表示和的链表。你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l1 == null ? 0 : l2.val;
            int sum = val1 + val2 + carry;
            carry = sum / 10;
            ListNode temp = new ListNode(sum % 10);
            cur.next = temp;
            cur = cur.next;
            if (l1 != null) {
               l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            cur.next = new ListNode(1);
        }
        return dummy.next;
    }

    /*
        3. 无重复字符的最长子串
     */

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int max = 0;
        for (int end = 0; end < s.length(); end++) {
            Character ch = s.charAt(end);
            if (map.containsKey(ch)) {
                start = Math.max(start, map.get(ch) + 1);
            }
            map.put(ch, end);
            max = Math.max(max, end - start + 1);
        }
        return max;
    }


    //Timer类可以用于执行重复性任务或者延迟执行任务。
    public static void test01(){
        Timer timer = new Timer();
        // 获取当前系统时间
        LocalDateTime now = LocalDateTime.now();

        // 创建一个日期时间格式化对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化当前系统时间
        String formattedDateTime = now.format(formatter);

        // 输出格式化后的时间
        System.out.println("当前系统格式化后的时间: " + formattedDateTime);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 在30秒后执行的逻辑处理
                System.out.println("---------------------------");
                System.out.println("当前系统格式化后的时间-------: " + LocalDateTime.now().format(formatter));
                //延迟执行任务,完成后释放资源，避免内存泄漏
                timer.cancel();
                timer.purge();
            }
        }, 3000); // 3秒后执行

    }
}
