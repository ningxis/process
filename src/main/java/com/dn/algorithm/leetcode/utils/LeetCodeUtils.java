package com.dn.algorithm.leetcode.utils;

import com.dn.bean.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author dingning
 * @date 2023/3/26 下午 12:32
 **/
public class LeetCodeUtils {

    public static TreeNode processTreeNode(Integer[] array) {
        if (array.length == 0) {
            return null;
        }
        TreeNode treeNode = new TreeNode(array[0]);
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(treeNode);
        // 先左后右
        boolean isleft = true;
        for (int i = 1; i < array.length; i++) {
            TreeNode peek = deque.getFirst();
            if (isleft) {
                if (array[i] != null) {
                    peek.left = new TreeNode(array[i]);
                    //将左节点添加至队尾
                    deque.offer(peek.left);
                }
                isleft = false;

            } else {
                if (array[i] != null) {
                    peek.right = new TreeNode(array[i]);
                    deque.offer(peek.right);
                }
                // 删除队头第一个元素
                deque.pollFirst();
                isleft = true;
            }
        }
        return treeNode;
    }
}
