package com.dn.algorithm.leetcode.ten;

import com.alibaba.fastjson.JSONObject;
import com.dn.bean.ListNode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author dingning
 * @date 2022/10/17 下午 07:53
 **/
public class ThirdWeek {

    private static int[] leetCode1(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{};
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    //迭代法反转链表
    private static ListNode reverseListNode(ListNode node) {
        if (node == null) {
            return null;
        }
        ListNode prev = null;
        ListNode curr = node;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    //递归反转链表
    private static ListNode reverseByRecursive(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode last = reverseListNode(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    private static ListNode intArrayToListNode(int[] ints) {
        ListNode<Integer> node = new ListNode<>(ints[0]);
        ListNode<Integer> temp = node;
        for (int i = 1; i < ints.length; i++) {
            ListNode<Integer> cur = new ListNode<>(ints[i]);
            temp.next = cur;
            temp = temp.next;
        }
        return node;
    }

    //快慢指针
    private static boolean isLoop(ListNode node) {
        if(node == null || node.next == null){
            return false;
        }
        ListNode fast = node.next.next;
        ListNode slow = node.next;
        while(fast != slow){
            if(fast == null || fast.next == null){
                return  false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }
    //找出环形链表的环
    private static ListNode getCircle(ListNode node){
        if(node == null || node.next == null){
            return null;
        }
        ListNode fast = node;
        ListNode slow = node;
        while(fast != null){
            slow = slow.next;
            if(fast.next != null){
                fast = fast.next.next;
            } else{
                return null;
            }
            if(fast == slow){
                ListNode curr = node;
                while(curr != slow){
                    curr = curr.next;
                    slow = slow.next;
                }
                return curr;
            }
        }
        return null;
    }

    //坚持才是胜利 2022年10月19日23:50:43
    public static void main(String[] args) {
        int[] ints = new int[]{2, 7, 11, 15};
        int[] ints1 = leetCode1(ints, 14);
        System.out.println(Arrays.toString(ints1));
        int[] nums = new int[]{1, 2, 3, 4, 5};
        ListNode listNode = intArrayToListNode(nums);
        System.out.println(JSONObject.toJSONString(listNode));
        ListNode listNode1 = reverseByRecursive(listNode);
        System.out.println(JSONObject.toJSONString(listNode1));
    }



}
