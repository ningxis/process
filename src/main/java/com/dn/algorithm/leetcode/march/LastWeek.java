package com.dn.algorithm.leetcode.march;

import com.dn.bean.TreeNode;

import java.util.HashMap;

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

    public static void main(String[] args) {

    }
}
