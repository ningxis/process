package com.dn.algorithm.leetcode;

import com.dn.bean.ListNode;

import java.util.LinkedList;

/**
 * @author dingning
 * @version 1.0
 * @description:
 * @date 2022/5/17 13:51
 */
public class FirstWeek {



    public static void main(String[] args) {
//        printNode(test01());
//        printNode(test02());
//        printNode(test03());
//        printNode(test04());
//        printNode(test05(test04()));
//        printNode(test04());
        printNode(test07(init()));
    }


    public static ListNode init(){
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
        listNode.next.next.next.next.next.next = new ListNode(7);
        System.out.print("链表初始化完成：");
        printNode(listNode);
        return listNode;
    }

    //反转链表
    public static ListNode test01() {
        ListNode head = new ListNode("1");
        head.next = new ListNode("2");
        head.next.next = new ListNode("3");
        head.next.next.next = new ListNode("4");
        head.next.next.next.next = new ListNode("5");
        printNode(head);//原始链表
        ListNode dummy = new ListNode("-1");
        dummy.next = head;
//        //反转
        ListNode prev = dummy.next;
        ListNode pCur = prev.next;
        while (pCur != null) {
            //首先断开节点引用
            prev.next = pCur.next;
            //反转节点引用
            pCur.next = dummy.next;//此处使用当前节点prev会导致数据错乱
            dummy.next = pCur;
            pCur = prev.next;
        }
        return dummy.next;

    }

    //采用新链表反转,整体逻辑和原来一致，只是用的新链表反转
    public static ListNode test02() {
        ListNode head = new ListNode("1");
        head.next = new ListNode("2");
        head.next.next = new ListNode("3");
        head.next.next.next = new ListNode("4");
        head.next.next.next.next = new ListNode("5");
        printNode(head);//原始链表
        ListNode dummy = new ListNode("-1");
        ListNode pCur = head;
        while (pCur != null) {
            ListNode pNex = pCur.next;
            pCur.next = dummy.next;
            dummy.next = pCur;
            pCur = pNex;
        }
        return dummy.next;

    }

    public static ListNode test03() {
        ListNode head = new ListNode("1");
        head.next = new ListNode("2");
        head.next.next = new ListNode("3");
        head.next.next.next = new ListNode("4");
        head.next.next.next.next = new ListNode("5");
        printNode(head);
        ListNode listNode = new ListNode("-1");//空节点
//        node.next = head;
//        Node current = node.next;//当前处理的节点
//        Node temp = current.next;//下一个需要处理的节点
        //开始反转,利用原来的链表
//        while(temp!=null){
//            current.next = temp.next;
//            temp.next = node.next;
//            node.next = temp;
//            temp = current.next;
//        }

        //利用新链表反转
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next;
            current.next = listNode.next;
            listNode.next = current;
            current = temp;
        }
        return listNode.next;
    }

    //递归实现反转链表
    public static ListNode test07(ListNode head){
            if(head.next == null){
                return head;
            }
            ListNode last = test07(head.next);
            head.next.next = head;
            head.next = null;
            return last;
    }

    public static ListNode test04(){
        ListNode listNode1 = new ListNode("1");
        ListNode listNode2 = new ListNode("2");
        ListNode listNode3 = new ListNode("3");
        ListNode listNode4 = new ListNode("4");
        ListNode listNode5 = new ListNode("5");
        tansferNode(listNode1, listNode2);
        tansferNode(listNode2, listNode3);
        tansferNode(listNode3, listNode4);
        tansferNode(listNode4, listNode5);
        return listNode1;
    }


    public static void test06(){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        ListNode binaryTree = createBinaryTree(list);

    }

    /**
     * 构建二叉树
     * @param list   输入序列
     * @return
     */
    public static ListNode createBinaryTree(LinkedList<Integer> list){
        ListNode listNode = null;
        if(list == null || list.isEmpty()){
            return null;
        }
        Integer data = list.removeFirst();
        if(data!=null){
            listNode = new ListNode(data);
            listNode.pre = createBinaryTree(list);
            listNode.next = createBinaryTree(list);
        }
        return listNode;
    }


    public static ListNode test05(ListNode head){
            if (head.next == null) return head;
            ListNode last = test05(head.next);
//            head.next.next = head;
//            head.next = null;
            return last;
    }

    public static void tansferNode(ListNode listNode1, ListNode listNode2){
        listNode1.next = listNode2;
        listNode2.pre = listNode1;
    }


    public static void printNode(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.getValue());
            listNode = listNode.getNext();
        }
        System.out.println();
    }

}
