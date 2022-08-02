package com.dn.algorithm.leetcode;

import com.dn.bean.ListNode;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author skyline
 * @version 1.0
 * @description: 2022年7月25日17:03:16  截止时间 2022年8月1日17:03:32
 * @date 2022/7/25 16:30
 */
public class ElevenWeek {

//智力题

    //文本转换，直接在最后一行追加
    private static void fileTransfer() {
        try {
            FileReader fileReader = new FileReader("D:/小远/pictures/company.txt");
            BufferedReader br = new BufferedReader(fileReader);
            String temp = "";
            Set<String> set = new HashSet<>();
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:/小远/pictures/companyTest.txt", true));
            //readLine()：表示读取一行文本。
            while ((temp = br.readLine()) != null) {
                if (set.add(temp)) {
                    bw.write(temp + "\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //原始流写法
    private static void convertFile(){
        String fileName = "D:/小远/pictures/companyTest2.txt";
        try(FileOutputStream fos = new FileOutputStream(fileName);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);){
            bw.write("Hello World -创建文件!!");
            bw.flush();
        } catch ( IOException e){
            System.out.println(e);
        }

    }

    //迭代法反转链表
    private static ListNode<Integer> convertListNode(ListNode<Integer> head){

        if(head == null){
            return head;
        }
        ListNode<Integer> pre = null;
        ListNode<Integer> cur = head;
        while(cur != null){
            ListNode<Integer> node = cur.next;
            cur.next = pre;
            pre = cur;
            cur = node;
        }
        return pre;
    }


    private static List<Integer> leetCode448(){
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
//        List<Integer> res = new ArrayList<>();
//        HashSet<Integer> set = new HashSet<>();
//        for (int i = 0; i <nums.length; i++) {
//            set.add(nums[i]);
//        }
//        for (int i = 1; i <= nums.length; i++) {
//            if(set.add(i)){
//                res.add(i);
//            }
//        }
//        return res;
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n; //得到num值对应的下标
            nums[x] += n;   //num-1下标位置的数+n放入nums数组中
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                //因为值是[1,n]而第一个for循环经过if判断后筛选得到
                //的是有问题的下标即从0开始但值从1，所以下面用i+1
                ret.add(i + 1);//i位置上的值i+1未曾出现过
            }
        }
        return ret;
    }

    //todo:dingning 2022/7/30 下午 05:43  out 指定英语计划每天todo、算法整理题目
    //https://www.bilibili.com/video/BV1Dk4y1q781 英语口语学习 当下的力量，做好当下，明天的事明天再说
    public static void main(String[] args) {
        leetCode448();
    }
}
