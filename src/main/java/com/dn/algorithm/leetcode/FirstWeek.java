package com.dn.algorithm.leetcode;

import com.dn.bean.ListNode;

import java.util.*;

/**
 * @author dingning
 * @version 1.0
 * @description:
 * @date 2022/5/17 13:51
 */
public class FirstWeek {


    public static void postOrder2(ListNode root) {
//        Deque
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            root = s1.pop();
            s2.push(root);
            if (root.pre != null) {
                s1.push(root.pre);
            }
            if (root.next != null) {
                s1.push(root.next);
            }
        }
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().value + " ");
        }
    }

        public boolean isPalindrome(ListNode head) {
            ListNode pre = null;
            ListNode slow = head;
            ListNode fast = head;
            while(fast != null && fast.next != null){
                ListNode temp = slow.next;
                if(pre != null) {
                    slow.next = pre;
                }
                pre = slow;
                fast = fast.next.next;
                slow = temp;
            }
            if(fast != null) slow = slow.next;
            while(slow != null){
                if(slow.value != pre.value) return false;
                slow = slow.next;
                pre = pre.next;
            }
            return true;
        }

    public static int dp(ListNode root) {
        if (root == null) {
            return 0;
        }
//        int i = dp(root.pre);
//        int j = dp(root.next);
//
//        return (i > j) ? i + 1 : j + 1;
        if(root == null){
            return 0;
        }
//        int i = dp(root.pre);
//        int j = dp(root.next);
//        return (i<j)? j+1:i+1;
        if(root == null){
            return 0;
        }
        return Math.max(dp(root.pre),dp(root.next)) +1;
    }


    public static void main(String[] args) {
//        printNode(test01());
//        printNode(test02());
//        printNode(test03());
//        printNode(test04());
//        printNode(test05(test04()));
//        printNode(test04());
//        printNode(test07(init()));
//        test09();
//        ListNode root = new ListNode("A");
//        root.pre = new ListNode("B");
//        root.next = new ListNode("C");
//        root.pre.pre = new ListNode("D");
//        root.pre.next = new ListNode("E");
//        root.next.pre = new ListNode("F");
//        System.out.println(dp(root));
//        test21();
        int[] array = new int[]{2,7,9,3,1};
        System.out.println(test22(array));
    }

    public static int test22(int[] array){
            int fir = 0;
            int sec = 0;
            for(int i = 0; i + 2 <= array.length -1;){
                fir = fir + array[i];
                sec = sec + array[i + 1];
                i = i + 2;
            }
            return Math.max(fir,sec);
    }

    public static void test21() {
        //验证自定义的二分查找法
        int[] a = {};
        int[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] c = {1, 4, 6, 7, 8, 3, -2};

        //循环实现
        int circulate1 = test21(a, 0, 0, a.length - 1);
        int circulate2 = test21(b, 5, 0, b.length - 1);
        int circulate3 = test21(c, -2, 0, c.length - 1);
        Arrays.sort(c);
        int circulate4 = test21(c, -2, 0, c.length - 1);
        System.out.println("++++++++++++++++" + circulate1 + "");  //-1
        System.out.println("++++++++++++++++" + circulate2 + ""); //4
        System.out.println("++++++++++++++++" + circulate3 + ""); //-1
        System.out.println("++++++++++++++++" + circulate4 + ""); //0
    }

    public static int test21(int[] ints, int target, int start, int end) {
        if (start > end || start < 0 || end > ints.length - 1) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (ints[mid] > target) {
            return test21(ints, target, start, mid - 1);
        } else if (ints[mid] < target) {
            return test21(ints, target, mid + 1, end);
        } else {
            return mid;
        }
    }

    public static void test20() {
        //验证自定义的二分查找法
        int[] a = {};
        int[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] c = {1, 4, 6, 7, 8, 3, -2};

        //循环实现
        int circulate1 = test20(a, 0);
        int circulate2 = test20(b, 5);
        int circulate3 = test20(c, -2);
        Arrays.sort(c);
        int circulate4 = test20(c, -2);
        System.out.println("++++++++++++++++" + circulate1 + "");  //-1
        System.out.println("++++++++++++++++" + circulate2 + ""); //4
        System.out.println("++++++++++++++++" + circulate3 + ""); //-1
        System.out.println("++++++++++++++++" + circulate4 + ""); //0
    }

    //二分查找法,for循环迭代法实现
    public static int test20(int[] ints, int val) {
        int start = 0;
        int end = ints.length - 1;

        while (end >= start) {
            int mid = (start + end) / 2;
            if (ints[mid] > val) {
                end = mid - 1;

            }
            if (ints[mid] == val) {
                return mid;
            }
            if (ints[mid] < val) {
                start = mid + 1;

            }
        }
        return -1;

    }

    //斐波那契数列，递推写法
    public static int test19(int n) {
        if (n <= 2) {
            return 1;
        }
        int tmp = 1, post = 1, last = 1;
        for (int i = 2; i < n; i++) {
            tmp = post + last;
            last = post;
            post = tmp;
        }
        return tmp;
    }

    //斐波那契数列，数组写法
    public static int test18(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] ints = new int[n];
        ints[0] = ints[1] = 1;
        for (int i = 2; i < n; i++) {
            ints[i] = ints[i - 1] + ints[i - 2];
        }
        System.out.println(Arrays.toString(ints));
        return ints[n - 1];
    }


    //斐波那契数列,递归写法
    public static int test17(int number) {
        if (number == 1 || number == 2) {
            return 1;
        }
        return test17(number - 1) + test17(number - 2);
    }


    public static void printByStack(ListNode root) {
        Stack<ListNode> stack = new Stack<>();
        ListNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.pre;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.getValue());
                node = node.next;
            }
        }
    }

    public static void test08() {
        int[] datas = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 16, 29};
//        BinaryTree biTree = BinaryTree.createBiTree(datas);
//        BinaryTree.print(biTree.getRoot());
    }

    public static void test09() {
        ListNode root = new ListNode("A");
        root.pre = new ListNode("B");
        root.next = new ListNode("C");
        root.pre.pre = new ListNode("D");
        root.pre.next = new ListNode("E");
        root.next.pre = new ListNode("F");
        printByStack(root);
        System.out.println();
        test10(root);
        System.out.println();
        test11(root);
        System.out.println();
        test12(root);
    }

    public static void test10(ListNode node) {
        if (node == null) return;
        System.out.print(node.getValue());
        test10(node.pre);
        test10(node.next);
    }

    public static void test11(ListNode node) {
        if (node == null) return;
        test11(node.pre);
        System.out.print(node.getValue());
        test11(node.next);
    }

    public static void test12(ListNode node) {
        if (node == null) return;
        test12(node.pre);
        test12(node.next);
        System.out.print(node.getValue());
    }


    public static ListNode init() {
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
    public static ListNode test07(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode last = test07(head.next);
        head.next.next = head;
        head.next = null;
        return last;//返回之后还是会执行递归方法
    }

    public static ListNode test04() {
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


    public static void test06() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        ListNode binaryTree = createBinaryTree(list);
        printTreeNode(binaryTree);

    }

    public static void printTreeNode(ListNode root) {
        if (root == null) return;
        System.out.println(root.getValue());
        if (root.getPre() != null) {
            printNode(root.getPre());
        }
        if (root.getNext() != null) {
            printNode(root.getNext());
        }
    }

    /**
     * 构建二叉树
     *
     * @param list 输入序列
     * @return
     */
    public static ListNode createBinaryTree(LinkedList<Integer> list) {
        ListNode listNode = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        Integer data = list.removeFirst();
        if (data != null) {
            listNode = new ListNode(data);
            listNode.pre = createBinaryTree(list);
            listNode.next = createBinaryTree(list);
        }
        return listNode;
    }


    public static ListNode test05(ListNode head) {
        if (head.next == null) return head;
        ListNode last = test05(head.next);
//            head.next.next = head;
//            head.next = null;
        return last;
    }

    public static void tansferNode(ListNode listNode1, ListNode listNode2) {
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
