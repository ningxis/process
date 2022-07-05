package com.dn.algorithm.leetcode;

import com.dn.bean.ListNode;

/**
* @description: 
* @author dingning
* @date 2022/5/23 9:46
* @version 1.0
*/
public class SecondWeek {

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

    public static void main(String[] args) {
        test02();
    }

    //给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
    public static ListNode test03(ListNode root){
        if(root == null){
            return null;
        }
        ListNode tmp = root.pre;
        root.pre = root.next;
        root.next = tmp;
        test03(root.pre);
        test03(root.next);
        return  root;
    }

    //回溯法走迷宫
    public static  void test01(){

    }


    public static void test02(){
        /*时间辗转来到快七月,今年也是毕业三年了，我想说一些话，告诉自己
        没有什么是一成不变的，感情、亲情、友情都需要时间和空间来维护
         */

    }

}
