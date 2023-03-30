package com.dn.algorithm.leetcode.march;

import com.dn.bean.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author skyline
 * @version 1.0
 * @description:
 * @date 2023/3/27 15:37
 */
public class LastWeek {

    //分割等和子集
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            for (int j = target; j >= num; --j) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }


    // 当前以root为终点且和为key的路径有map.get(key)条
    HashMap<Long, Integer> map = new HashMap<Long, Integer>() {{
        put(0L, 1);
    }};
    int targetSum;
    long sum;

    //路径总和 III
    public int pathSum(TreeNode root, int _targetSum) {
        /*
        HashMap+先序遍历
         */
        targetSum = _targetSum;
        return dfs(root);
    }

    // 该函数返回root为根的二叉树中，路径和为targetSum的路径数目
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        // 计算根节点到当前节点的和
        sum += root.val;
        // 当前节点的sum-前部分sum=targetSum，前部分=当前节点的sum-targetSum
        // 因此每当有一个前部分符合条件，就表示从前部分终点->当前节点的合法路径
        // 这个就是以当前节点为终点的合法路径
        int cur = map.getOrDefault(sum - targetSum, 0);

        // 后更新map，因为合法路径至少有一个节点，不能统计空路径
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        // 把sum计算了之后就可以递归计算终点在左右子树合法路径数
        int l = dfs(root.left), r = dfs(root.right);
        // 当前节点计算完了，要计算右边的节点，要进行撤回
        // 注意先用原来的sum计算map
        map.put(sum, map.get(sum) - 1);
        // 再将前缀和减去
        sum -= root.val;
        // 最终的合法路径数=以root为终点的+终点在root左右子树的 合法路径数 之和
        return cur + l + r;
    }

    //找到字符串中所有字母异位词
    public static List<Integer> findAnagrams(String s, String p) {
        /*
        滑窗+词频统计:
        题意等价于:在一个大小固定为p长度的窗口内,将符合条件的窗口起始索引加入结果
        如何判定是否符合要求?可以通过数组进行词频统计,判断s子串与p是否出现的字母与数量完全一致
         */
        List<Integer> res = new ArrayList<>();
        int[] sMap = new int[26]; // 记录s窗口子串的词频
        int[] pMap = new int[26]; // p的字母出现的词频
        int lenP = p.length(), lenS = s.length();
        if (lenP > lenS) return res;    // 子串p比主串s还大,直接返回res
        // 首个窗口
        for (int i = 0; i < lenP; i++) {
            sMap[s.charAt(i) - 'a']++;
            pMap[p.charAt(i) - 'a']++;
        }
        if (valid(sMap, pMap)) res.add(0);
        // i为新窗口左端的索引
        for (int i = 1; i + lenP - 1 < lenS; i++) {
            int j = i + lenP - 1;   // j为新窗口右端索引
            sMap[s.charAt(j) - 'a']++;
            sMap[s.charAt(i - 1) - 'a']--;  // i-1为退出窗口的索引
            System.out.println(Arrays.toString(sMap));
            if (valid(sMap, pMap)) res.add(i);  // 新窗口子串与p是异位词
        }
        return res;
    }

    // 判断当前s子串是否与p是异位词(排除法)
    private static boolean valid(int[] sMap, int[] pMap) {
        for (int i = 0; i < 26; i++) {
            if (sMap[i] != pMap[i]) return false;
        }
        return true;
    }

    //目标和
    public int findTargetSumWays(int[] nums, int target) {
        /*
        类01背包问题:
        设pos为取+的数字和,neg为取-的数字和(均为正数),则target=pos-neg=pos-(sum-pos)=2*pos-sum
        故pos=(sum+target)/2>=0且为常数,因此此问题等价于求有多少种方式用nums[i]凑成和为pos
        进而该问题抽象为:用价值与体积均为nums[i]的物品,恰好凑满容量为pos的背包方案数
        1.状态定义:dp[j]为恰好能凑满容量为j的背包方案数
        2.状态转移:背包容量能或者不能装下nums[i]
            2.1 当不能装下nums[i]时,方案数直接继承之前的dp[j]
            2.2 当能装下nums[i]时,总的方案数为不考虑nums[i]的方案数+有nums[i]参与新增的方案数
                dp[j] += dp[j - nums[i]],dp[j - nums[i]]种方案与nums[i]共同凑成pos,即1*dp[j - nums[i]]
        3.状态初始化:dp[0]=1,因为后面总会一直查找至j=0,如dp[3] += dp[3-3],空集是任意一条有效路径的起点,当属一条
        4.遍历顺序:i正序,j倒序
        5.返回形式:dp[pos]就是凑成pos总的方案数
         */
        int len = nums.length;
        int sum = 0;
        for (int num : nums) sum += num;
        // pos为小数||target绝对值比sum还大
        if ((sum + target) % 2 == 1 || Math.abs(target) > sum) return 0;
        int pos = (sum + target) / 2;
        int[] dp = new int[pos + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = pos; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[pos];
    }

    //盛最多水的容器
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while(left <= right){
            int area;
            if(height[left] <= height[right]){
                area = height[left] * (right - left);
                left++;
            } else {
                area = height[right] * (right - left);
                right--;
            }
            max = Math.max(area, max);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
