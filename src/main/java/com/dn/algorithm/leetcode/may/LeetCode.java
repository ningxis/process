package com.dn.algorithm.leetcode.may;

import com.dn.bean.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
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
        backTrack(list, "", n, n);
        return list;
    }

    private void backTrack(List<String> list, String str, int left, int right) {
        if (left == 0 && right == 0) {
            list.add(str);
            return;
        }
        if (left >= right) {
            backTrack(list, str + "(", left - 1, right);
        } else {
            backTrack(list, str + ")", left, right - 1);
            if (left > 1) {
                backTrack(list, str + "(", left - 1, right);
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

    public static void nextPermutation(int[] nums) {
        //从后向前找第一次出现邻近升序的对儿 A[i] < A[j]
        int i = nums.length - 2, j = nums.length - 1;
        while (i >= 0) {
            if (nums[i] < nums[j]) {
                break;
            }
            i--;
            j--;
        }

        //本身就是最后一个排列（全部降序）， 把整体整个翻转变升序进行返回
        if (i < 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        //从[j, end]从后向前找第一个令A[i] < A[k]的 k值  （不邻近升序对儿 ，也有可能近邻）
        int k;
        for (k = nums.length - 1; k >= j; k--) {
            if (nums[i] < nums[k]) break;
        }
        //得到k
        //交换i, k
        swap(nums, i, k);
        //nums[j,end]是降序 改为升序
        reverse(nums, j, nums.length - 1);
    }

    public static void reverse(int[] nums, int l, int r) {
        //双指针升序
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    public static void swap(int[] nums, int i, int k) {
        int tmp = nums[i];
        nums[i] = nums[k];
        nums[k] = tmp;
    }


    //下一个排列
    public static void nextPermutation1(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int i = nums.length - 1;
        int j = 0;
        while (i > 0) {
            if (nums[i - 1] < nums[i]) {
                j = i;
                break;
            }
            i--;
        }
        if (j - 1 < 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        i = nums.length - 1;
        while (i > 0) {
            if (nums[i] > nums[j - 1]) {
                break;
            }
            i--;
        }
        swap(nums, i, j - 1);
        reverse(nums, j, nums.length - 1);
    }


    //搜索旋转数组
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //左边有序
            if (nums[mid] >= nums[left]) {
                if (nums[mid] > target && nums[left] <= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
                //右边有序
            } else {
                if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    //搜索大于target的数组下标
    public static int searchNum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target <= nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    //在排序数组中查找元素的第一个和最后一个位置  二分查找法
    public static int[] searchRange(int[] nums, int target) {
        int left = searchNum(nums, target);
        int right = searchNum(nums, target + 1) - 1;
        if (left <= right && nums[left] == target) {
            return new int[]{left, right};
        }
        return new int[]{-1, -1};

    }

    /**
     * 范围查询规律
     * 初始化:
     * int left = 0;
     * int right = nums.length - 1;
     * 循环条件
     * left <= right
     * 右边取值
     * right = mid - 1
     * 左边取值
     * left = mid + 1
     * 查询条件
     * >= target值, 则 nums[mid] >= target时, 都减right = mid - 1
     * >  target值, 则 nums[mid] >  target时, 都减right = mid - 1
     * <= target值, 则 nums[mid] <= target时, 都加left = mid + 1
     * <  target值, 则 nums[mid] <  target时, 都加left = mid + 1
     * 结果
     * 求大于(含等于), 返回left
     * 求小于(含等于), 返回right
     * 核心思想: 要找某个值, 则查找时遇到该值时, 当前指针(例如right指针)要错过它, 让另外一个指针(left指针)跨过他(体现在left <= right中的=号), 则找到了
     * ----我的理解是  当前存在的mid指针,left超过这个指针就是大于等于了,right超过这个指针就是小于等于了
     */

//组合总和
    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        // 排序是剪枝的前提
        Arrays.sort(candidates);
        backTrack(candidates, target, 0, new ArrayList<>(), 0);
        return res;
    }

    // https://leetcode.cn/problems/combination-sum/solutions/14697/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
    private static void backTrack(int[] candidates, int target, int sum, List<Integer> path, int start) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 重点理解这里从 begin 开始搜索的语意
        for (int i = start; i < candidates.length; i++) {
            int num = candidates[i];
            int temp = sum + num;
            // 重点理解这里剪枝，前提是候选数组已经有序，
            if (target >= temp) {
                path.add(num);
                // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
                backTrack(candidates, target, temp, path, i);
                // 状态重置 移除无效元素
                path.remove(path.size() - 1);
            } else {
                break;
            }
        }
    }

    //全排列
    // https://leetcode.cn/problems/permutations/solutions/9914/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
    public static List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        int len = nums.length;
        boolean[] used = new boolean[len];
        permuteBackTrack(nums, 0, new ArrayList<>(), used);
        return res;

    }


    private static void permuteBackTrack(int[] nums, int index, List<Integer> list, boolean[] used) {
/**
  * 变量 path 所指向的列表 在深度优先遍历的过程中只有一份 ，深度优先遍历完成以后，回到了根结点，成为空列表。
  *
  * 在 Java 中，参数传递是 值传递，对象类型变量在传参的过程中，复制的是变量的地址。这些地址被添加到 res 变量，但实际上指向的是同一块内存地址，
 *  因此我们会看到 6个空的列表对象。解决的方法很简单，在 res.add(path); 这里做一次拷贝即可。
  */
        if (index == nums.length) {
//            res.add(list);
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.println(i);
            if (!used[i]) {
                list.add(nums[i]);
                used[i] = true;
                System.out.println("  递归之前 => " + list);
                System.out.println(Arrays.toString(used));
                permuteBackTrack(nums, index + 1, list, used);
                used[i] = false;
                list.remove(list.size() - 1);
                System.out.println("  递归之hou => " + list);
                System.out.println(Arrays.toString(used));

            }
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        permute(nums);
        System.out.println(res);
    }


}
