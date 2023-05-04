package com.dn.algorithm.leetcode.may;

import com.dn.bean.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skyline
 * @version 1.0
 * @description:
 * @date 2023/5/4 9:17
 */
public class LeetCode {

    //括号生成
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n == 0) {
            return list;
        }
        backTrack( list, "",n, n);
        return list;
    }

    private void backTrack(List<String> list, String str, int left, int right) {
       if (left == 0 && right == 0) {
           list.add(str);
           return;
       }
       if (left >= right) {
           backTrack(list, str + "(" , left - 1, right);
       } else {
           backTrack(list, str + ")" , left, right - 1);
           if (left > 1) {
               backTrack(list, str + "(" , left - 1, right);
           }
       }
    }

    //两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head.next.next;
        head.next.next = head;
        head.next = temp;
        swapPairs(head.next.next);
        return head;
    }




    public static void main(String[] args) {

    }


}
