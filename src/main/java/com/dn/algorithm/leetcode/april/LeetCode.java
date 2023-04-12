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
}
