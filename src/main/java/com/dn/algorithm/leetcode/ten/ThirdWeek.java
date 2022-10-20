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
    private static ListNode reverse(ListNode head){
        if(head == null){
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next  = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
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
        ListNode listNode2 = reverse(listNode1);
        System.out.println(JSONObject.toJSONString(listNode2));
        ListNode listNode3 = reverse2(listNode2);
        System.out.println(JSONObject.toJSONString(listNode3));
    }

    private static ListNode reverse2(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode last = reverse2(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    private static boolean circle(ListNode node){
        if(node == null || node.next == null){
            return false;
        }
        ListNode slow = node.next;
        ListNode fast = node.next.next;
        while(fast != slow){
            if(fast == null ||fast.next == null){
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }


    private static ListNode findCircle(ListNode node){
        if(node == null || node.next == null){
            return null;
        }
        ListNode slow = node;
        ListNode fast = node;
        while(fast != null){
            slow = slow.next;
            if(fast.next != null){
                fast = fast.next.next;
            } else {
                return null;
            }
            if(fast == slow){
                ListNode start  = node;
                while(start != slow){
                    slow = slow.next;
                    start = start.next;
                }
                return start;
            }
        }
        return null;
    }


    private static int[] twoNumbers(int[] nums, int target){
        if(nums.length == 0){
            return new int[0];
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int tar = target - nums[i];
            if(map.containsKey(tar)){
                return new int[]{i, map.get(tar)};
            } else{
                map.put(nums[i], i);
            }
        }
        return new int[0];
    }

    //迭代
    private ListNode reverse1(ListNode node){
        if(node == null){
            return null;
        }
        ListNode pre = null;
        ListNode cur = node;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //递归
    private static ListNode reverse3(ListNode node){
        if(node == null || node.next == null){
            return node;
        }
        ListNode last = reverse3(node.next);
        node.next.next = node;
        node.next= null;
        return last;
    }

    private static boolean circle1(ListNode node){
        if(node == null || node.next == null){
            return false;
        }
        ListNode slow = node.next;
        ListNode fast = node.next.next;
        while(slow != fast){
            if(fast == null || fast.next == null){
                return  false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    private static ListNode findCircle1(ListNode node){
        if(node == null || node.next == null){
            return null;
        }
        ListNode slow = node;
        ListNode fast = node;
        while(fast != slow){
            slow = slow.next;
            if(fast == null || fast.next == null){
                return null;
            }
            fast = fast.next.next;
            if(fast == slow){
                ListNode start = node;
                while(start != slow){
                    slow = slow.next;
                    start = start.next;
                }
                return slow;
            }
        }
        return null;
    }

}
