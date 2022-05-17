package com.dn.algorithm.leetcode;

import com.dn.bean.Node;

import java.util.LinkedList;

/**
 * @author dingning
 * @version 1.0
 * @description:
 * @date 2022/5/17 13:51
 */
public class FirstWeek {

    public static void main(String[] args) {
        printNode(test01());
//        printNode(test02());
//        printNode(test03());
//        printNode(test04());
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
        while (pCur != null) {
            //首先断开节点引用
            prev.next = pCur.next;
            //反转节点引用
            pCur.next = prev;
            dummy.next = pCur;
            pCur = prev.next;
        }
        return dummy.next;

    }

    //采用新链表反转,整体逻辑和原来一致，只是用的新链表反转
    public static Node test02() {
        Node head = new Node("1");
        head.next = new Node("2");
        head.next.next = new Node("3");
        head.next.next.next = new Node("4");
        head.next.next.next.next = new Node("5");
        printNode(head);//原始链表
        Node dummy = new Node("-1");
        Node pCur = head;
        while (pCur != null) {
            Node pNex = pCur.next;
            pCur.next = dummy.next;
            dummy.next = pCur;
            pCur = pNex;
        }
        return dummy.next;

    }

    public static Node test03() {
        Node head = new Node("1");
        head.next = new Node("2");
        head.next.next = new Node("3");
        head.next.next.next = new Node("4");
        head.next.next.next.next = new Node("5");
        printNode(head);
        Node node = new Node("-1");//空节点
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
        Node current = head;
        while (current != null) {
            Node temp = current.next;
            current.next = node.next;
            node.next = current;
            current = temp;
        }
        return node.next;
    }

    public static Node test04(){
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("5");
        tansferNode(node1,node2);
        tansferNode(node2,node3);
        tansferNode(node3,node4);
        tansferNode(node4,node5);
        return node1;
    }


    public static void test05(){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        Node binaryTree = createBinaryTree(list);

    }

    /**
     * 构建二叉树
     * @param list   输入序列
     * @return
     */
    public static Node createBinaryTree(LinkedList<Integer> list){
        Node node = null;
        if(list == null || list.isEmpty()){
            return null;
        }
        Integer data = list.removeFirst();
        if(data!=null){
            node = new Node(data);
            node.pre = createBinaryTree(list);
            node.next = createBinaryTree(list);
        }
        return node;
    }

    public static void tansferNode(Node node1,Node node2){
        node1.next = node2;
        node2.pre = node1;
    }


    public static void printNode(Node node) {
        while (node != null) {
            System.out.print(node.getValue());
            node = node.getNext();
        }
        System.out.println();
    }

}
