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

    public void nextPermutation(int[] nums) {
        //从后向前找第一次出现邻近升序的对儿 A[i] < A[j]
        int i = nums.length - 2, j = nums.length - 1;
        while(i >= 0){
            if(nums[i] < nums[j]){
                break;
            }
            i--; j--;
        }

        //本身就是最后一个排列（全部降序）， 把整体整个翻转变升序进行返回
        if(i < 0) {
            reverse(nums, 0, nums.length-1);
            return;
        }

        //从[j, end]从后向前找第一个令A[i] < A[k]的 k值  （不邻近升序对儿 ，也有可能近邻）
        int k;
        for(k = nums.length-1; k >= j; k--){
            if(nums[i] < nums[k]) break;
        }
        //得到k
        //交换i, k
        swap(nums, i, k);
        //nums[j,end]是降序 改为升序
        reverse(nums, j, nums.length-1);
    }
    public void reverse(int[] nums, int l, int r){
        //双指针升序
        while(l < r){
            swap(nums, l, r);
            l++; r--;
        }
    }
    public void swap(int[] nums, int i, int k){
        int tmp = nums[i];
        nums[i] = nums[k];
        nums[k] = tmp;
    }




    public static void main(String[] args) {

    }


}
