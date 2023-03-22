package com.dn.algorithm.leetcode.march;

import com.dn.bean.TreeNode;

import java.util.*;

/**
 * @author dingning
 * @date 2023/3/15 下午 07:11
 **/
public class LeetCode {


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


    //在排序数组中查找元素的第一个和最后一个位置
    private static int[] searchRange(int[] nums, int target) {
        int left = findIndex(nums, target - 1);
        int right = findIndex(nums, target) - 1;
        if(left <= right && nums[left] == target){
            return new int[]{left, right};
        }
        return new int[]{-1, -1};

    }

    //查找target目标数据下标
    private static int findIndex(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){
                ans = mid;
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }
        return ans;
    }

    private static List<List<Integer>> res = new ArrayList<>();

    //组合总和
    public List<List<Integer>> combinationSumCopy(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0){
            return res;
        }
        backTrackCopy(new ArrayList<>(), candidates, target, 0, 0);
        return res;

    }

    private static void backTrackCopy(List<Integer> path, int[] candidates, int target, int sum, int start){
        if(sum == target){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = start; i < candidates.length; i++){
            int temp = sum + candidates[i];
            if(target >= temp){
                path.add(candidates[i]);
                backTrackCopy(path, candidates, target, temp,i);
                path.remove(path.size() - 1);
            } else{
                break;
            }
        }
    }


    //全排列
    public List<List<Integer>> permute(int[] nums) {
        if(nums == null || nums.length == 0){
            return res;
        }
        boolean[] booleans = new boolean[nums.length];
        backTrack(nums, 0, new ArrayList<>(), booleans);
        return res;


    }

    private static void backTrack(int[] nums, int start, List<Integer> path, boolean[] booleans){
        if(start == nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(!booleans[i]){
                path.add(nums[i]);
                booleans[i] = true;
                backTrack(nums, start + 1, path, booleans);
                booleans[i] = false;
                path.remove(path.size() - 1);
            }

        }
    }

    //字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        if(strs.length < 1){
            return res;
        }
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (map.containsKey(key)){
                map.get(key).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            }
        }
        return new ArrayList<>(map.values());
    }

    //55. 跳跃游戏
    public static boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0){
            return false;
        }
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i] + i;
            k = Math.max(k , temp);
            if(k >= nums.length - 1){
                return true;
            }
        }
        return false;
    }

    //颜色分类
    public static void sortColors(int[] nums) {
        int n0 = 0;
        int n1 = 0;
        int n2 = 0;
        for (int num : nums) {
            if(num == 0) n0++;
            if(num == 1) n1++;
            if(num == 2) n2++;
        }
        for (int i = 0; i < nums.length; i++) {
            if(n0 > 0){
                nums[i] = 0;
                n0--;
            } else if (n1 > 0){
                nums[i] = 1;
                n1--;
            } else {
                nums[i] = 2;
                n2--;
            }
        }
    }

    private static int[] sortColors1(int[] nums) {
        int left = 0, right = nums.length - 1;
        int cur = 0;
        while(cur <= right){

            if(nums[cur] > 1){
                int temp = nums[right];
                nums[right] = nums[cur];
                nums[cur] = temp;
                System.out.println("交换了2");
                right--;
            } else if(nums[cur] < 1){
                int temp = nums[left];
                nums[left] = nums[cur];
                nums[cur] = temp;
                System.out.println("交换了0");
                left++;
                cur++;
            } else {
                cur++;
            }
            System.out.println(Arrays.toString(nums) + " left:"+left + " right:" + right + " cur:" + cur);
        }
        return nums;
    }

    //子集
    private static List<List<Integer>> subsets(int[] nums){

        backTrack2(nums, 0, new ArrayList<>());
        return res;
    }

    private static void backTrack2(int[] nums, int start, List<Integer> path){

        res.add(new ArrayList<>(path));

        for(int i = 0; i < nums.length; i++){
            path.add(nums[i]);
            backTrack2(nums, start + 1, path);
            path.remove(path.size() - 1);
        }
    }

    //不同的二叉搜索树
    private static int numTrees(int n){
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++){
            for (int j = 1; j < n + 1; j++){
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }

    private static TreeNode pre = null;

    //二叉树展开为链表
    private static void flatten(TreeNode root) {
        if(root == null){
            return;
        }
        flatten(root.left);
        flatten(root.right);
        root.right = pre;
        root.left = null;
        pre = root;
        TreeNode pre = null;
    }


    public static int longestConsecutive(int[] nums) {
//        if(nums == null || nums.length == 0) {
//            return 0;
//        }
//        Arrays.sort(nums);
//        int length = 0;
//        int temp = 0;
//        for(int i = 1; i < nums.length; i++){
//            if(nums[i] == nums[i - 1] + 1){
//                temp++;
//                length = Math.max(length, temp);
//            } else if(nums[i] == nums[i - 1]) {
//                continue;
//            } else{
//                temp = 0;
//            }
//        }
//        return length + 1;
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return 1;
        }
        Arrays.sort(nums);
        int max = 1;
        int tempMax = 1;
        for(int i = 1;i<nums.length;i++){
            if((nums[i-1]) == nums[i]){
                continue;
            }
            if((nums[i-1] + 1) == nums[i]){
                tempMax++;
            }else{
                max = Math.max(tempMax,max);
                tempMax = 1;
            }
        }
        max = Math.max(tempMax,max);
        return max;
    }



    //hello world
    public static void main(String[] args) {
        System.out.println((longestConsecutive(new int[]{100,4,200,1,3,2})));
        System.out.println((longestConsecutive(new int[]{1,2,0,1})));
        System.out.println((longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1})));
        System.out.println((longestConsecutive(new int[]{100,4,200,1,3,2})));
    }

}
