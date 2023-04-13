package com.dn.algorithm.leetcode.april;

import java.util.Stack;

/**
 * @author dingning
 * @date 2023/4/12 下午 10:01
 **/
public class LeetCode {

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

    public ListNode reverseList(ListNode head) {
        if(head == null){
            return head;
        }
        if(head.next == null){
            return head;
        }
        //迭代从前到后
        // ListNode prev = null;
        // ListNode cur = head;
        // while(cur != null){
        //     ListNode temp = cur.next;
        //     cur.next = prev;
        //     prev = cur;
        //     cur = temp;
        // }
        // return prev;
        //递归从后到前
        // ListNode last = reverseList(head.next);
        // head.next.next = head;
        // head.next = null;
        // return last;
        //递归从前到后
        // return reverse(null, head);
        //栈
        if(head == null || head.next == null){
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        while(head != null){
            stack.push(head);
            head = head.next;
        }
        ListNode node = new ListNode(-1);
        ListNode cur = node;
        while(stack.size() > 0){
            ListNode temp = stack.pop();
            cur.next = temp;
            cur = temp;
        }
        cur.next = null;
        return node.next;
    }

    private ListNode reverse(ListNode prev, ListNode cur){
        if(cur == null){
            return prev;
        }
        ListNode node = cur.next;
        cur.next = prev;
        return reverse(cur, node);
    }

    //二分查找 左闭右闭
    private int search(int[] nums, int target){
        if(nums == null || nums.length == 0){
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            } else if(nums[mid] == target){
                return mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
    /*左闭右开区间查找
        while(left < right)，这里使用 < ,因为left == right在区间[left, right)是没有意义的
        if (nums[middle] > target) right 更新为 middle，因为当前nums[middle]不等于target，去左区间继续寻找，而寻找区间是左闭右开区间，所以right更新为middle，即：下一个查询区间不会去比较nums[middle]
    */
    private int search2(int[] nums, int target){
        if(nums == null || nums.length == 0){
            return -1;
        }
        int left = 0;
        int right = nums.length;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){
                right = mid;
            } else if(nums[mid] == target){
                return mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
