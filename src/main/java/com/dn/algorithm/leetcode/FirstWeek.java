package com.dn.algorithm.leetcode;

import com.dn.bean.Node;

/**
 * @author dingning
 * @version 1.0
 * @description:
 * @date 2022/5/17 13:51
 */
public class FirstWeek {

    public static void main(String[] args) {
        printNode(test01());
    }

    //反转链表
    public static Node test01() {
        Node head = new Node("1");
        head.next = new Node("2");
        head.next.next = new Node("3");
        head.next.next.next = new Node("4");
        head.next.next.next.next = new Node("5");
        printNode(head);//原始链表
        Node dummy = new Node("-1");
        dummy.next = head;
//        //反转
        Node prev = dummy.next;
        Node pCur = prev.next;
        while(pCur!=null){
            //首先断开节点引用
            prev.next = pCur.next;
            //反转节点引用
            pCur.next = dummy.next;
            dummy.next = pCur;
            pCur = prev.next;
        }
        return dummy.next;

    }

    public static void printNode(Node node) {
        while (node != null) {
            System.out.print(node.getValue());
            node = node.getNext();
        }
        System.out.println();
    }

}
