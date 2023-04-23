package com.dn.algorithm.leetcode.april;

import java.util.*;

/**
 * @author skyline
 * @version 1.0
 * @description:
 * @date 2023/4/23 13:13
 */
public class Hot100 {

    //三数之和
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            int first = nums[i];
            if (first > 0) {
                return res;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
//            Set<Integer> set = new HashSet<>();
//            for (int j = i + 1; j < len; j++) {
//                int third = nums[j];
//                int second = -(first + third);
//                if (set.contains(second)) {
//                    res.add(Arrays.asList(first, second, third));
//                    while (j < len - 1 && nums[j] == nums[j + 1]) {
//                        j++;
//                    }
//                }
//                set.add(third);
//            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = first + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(first, nums[left], nums[right]));
                    left++;
                    right--;
                }
                if (sum > 0) {
                    right--;
                }
                if (sum < 0) {
                    left++;
                }
            }

        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(threeSum(new int[]{-1, -1, -1, 2}));
    }

}
