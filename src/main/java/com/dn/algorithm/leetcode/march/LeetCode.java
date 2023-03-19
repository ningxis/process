package com.dn.algorithm.leetcode.march;

/**
 * @author dingning
 * @date 2023/3/15 下午 07:11
 **/
public class LeetCode {


    //hello world
    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }

    private static int[] countBits(int n){
        int[] bits = new int[n + 1];
        for(int i = 0; i < bits.length; i++){
            bits[i] = countOneBit(i);
        }
        return bits;
    }

    private static int countOneBit(int x){
        int one = 0;
        while(x > 0){
            x = x & (x - 1);
            one++;
        }
        return one;
    }

    //下一个排列
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length <= 1){
            return;
        }
        int i = nums.length - 2;
        while(i >= 0 && nums[i] >= nums[i + 1]){
            i--;
        }
        if(i >= 0){
            int j = nums.length - 1;
            while(j >= 0 && nums[i] >= nums[j]){
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }


    private static void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    private static void reverse(int[] nums, int start, int end){
        //双指针升序
        while(start < end){
            swap(nums, start, end);
            start++; end--;
        }
    }

    public static int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;


        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }
            if (nums[mid] < nums[right]) {
                if (target > nums[mid] && target <= nums[right] ) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if(nums[mid] > target && target >= nums[left]){
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

}
