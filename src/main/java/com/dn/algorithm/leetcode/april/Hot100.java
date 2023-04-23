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

    //有效的括号
    public static boolean isValid(String s) {
        if (s == null || s.length() == 0 || s.length() % 2 == 1) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (!map.containsKey(ch)) {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (!pop.equals(map.get(ch))) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    //合并两个有序链表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int carry = 0;
        while (list1 != null || list2 != null) {
            int sum = carry;
            if (list1 != null) {
                sum += list1.val;
            }
            if (list2 != null) {
                sum += list2.val;
            }
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            carry = sum / 10;
            if (list1 != null) {
                list1 = list1.next;
            }
            if (list2 != null) {
                list2 = list2.next;
            }
        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("()"));
        System.out.println(isValid("([{}])[{()}]"));
        System.out.println(isValid("}]"));
    }

}
