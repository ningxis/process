package com.dn.algorithm.leetcode;

import com.dn.bean.ListNode;

/**
* @description: 
* @author dingning
* @date 2022/5/23 9:46
* @version 1.0
*/
public class SecondWeek {
    public static void main(String[] args) {

    }

    //快慢指针链表找环
    public static boolean test01(ListNode root){
        if(root == null || root.next == null){
            return false;
        }
        ListNode slow = root.next;
        ListNode fast = root.next.next;
        while(slow != fast){
            if(slow == null || fast == null){
                return false;
            }
            if(slow.next == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
