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
        String test = "";
        //
        long start = System.currentTimeMillis();
            //语音文案命中对应业务类型
            System.out.println(test.contains("在哪里、怎么走"));//问路
            System.out.println(test.contains("地铁"));//地铁指南
            System.out.println(test.contains("身份证、户籍"));//身份证及户籍
            System.out.println(test.contains("居住证、市民卡"));//居住证及市民卡
            System.out.println(test.contains("派出所、警务室"));//派出所咨询
            System.out.println(test.contains("保险"));////社会保险
            System.out.println(test.contains("注意、事项"));//注意事项
        System.out.println(System.currentTimeMillis() - start + "ms");
        String a = "{\"content\":\"门诊办在哪里\",\"actionType\":\"1\"}";
    }

}
