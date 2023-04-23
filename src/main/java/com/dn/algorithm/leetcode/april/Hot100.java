package com.dn.algorithm.leetcode.april;

import java.util.*;

/**
 * @author skyline
 * @version 1.0
 * @description:
 * @date 2023/4/23 13:13
 */
public class Hot100 {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //三数之和
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            int first = nums[i];
            if (first > 0) {
                return res;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
//            Set<Integer> set = new HashSet<>();
//            for (int j = i + 1; j < len; j++) {
//                int third = nums[j];
//                int second = -(first + third);
//                if (set.contains(second)) {
//                    res.add(Arrays.asList(first, second, third));
//                    while (j < len - 1 && nums[j] == nums[j + 1]) {
//                        j++;
//                    }
//                }
//                set.add(third);
//            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = first + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(first, nums[left], nums[right]));
                    left++;
                    right--;
                }
                if (sum > 0) {
                    right--;
                }
                if (sum < 0) {
                    left++;
                }
            }

        }
        return res;
    }


    //删除链表的倒数第 N 个结点
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        int depth = depth(head);
        if (depth == 1 && n > 0) {
            return new ListNode();
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        for (int i = 0; i < depth - n; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    private static int depth(ListNode node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.next;
        }
        return depth;
    }

    public static void main(String[] args) {
        System.out.println(removeNthFromEnd(new ListNode(1), 1).val);
    }

}
