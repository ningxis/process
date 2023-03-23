package com.dn.algorithm.leetcode.march;

import com.dn.bean.ListNode;
import com.dn.bean.TreeNode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dingning
 * @date 2023/3/15 下午 07:11
 **/
public class LeetCode extends LinkedHashMap<Integer, Integer> {


    private static int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 0; i < bits.length; i++) {
            bits[i] = countOneBit(i);
        }
        return bits;
    }

    private static int countOneBit(int x) {
        int one = 0;
        while (x > 0) {
            x = x & (x - 1);
            one++;
        }
        return one;
    }

    //下一个排列
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }


    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    private static void reverse(int[] nums, int start, int end) {
        //双指针升序
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

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
            if (nums[mid] < nums[right]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (nums[mid] > target && target >= nums[left]) {
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
        if (left <= right && nums[left] == target) {
            return new int[]{left, right};
        }
        return new int[]{-1, -1};

    }

    //查找target目标数据下标
    private static int findIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = nums.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private static List<List<Integer>> res = new ArrayList<>();

    //组合总和
    public List<List<Integer>> combinationSumCopy(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        backTrackCopy(new ArrayList<>(), candidates, target, 0, 0);
        return res;

    }

    private static void backTrackCopy(List<Integer> path, int[] candidates, int target, int sum, int start) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int temp = sum + candidates[i];
            if (target >= temp) {
                path.add(candidates[i]);
                backTrackCopy(path, candidates, target, temp, i);
                path.remove(path.size() - 1);
            } else {
                break;
            }
        }
    }


    //全排列
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        boolean[] booleans = new boolean[nums.length];
        backTrack(nums, 0, new ArrayList<>(), booleans);
        return res;


    }

    private static void backTrack(int[] nums, int start, List<Integer> path, boolean[] booleans) {
        if (start == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!booleans[i]) {
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
        if (strs.length < 1) {
            return res;
        }
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (map.containsKey(key)) {
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
        if (nums == null || nums.length == 0) {
            return false;
        }
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i] + i;
            k = Math.max(k, temp);
            if (k >= nums.length - 1) {
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
            if (num == 0) n0++;
            if (num == 1) n1++;
            if (num == 2) n2++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (n0 > 0) {
                nums[i] = 0;
                n0--;
            } else if (n1 > 0) {
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
        while (cur <= right) {

            if (nums[cur] > 1) {
                int temp = nums[right];
                nums[right] = nums[cur];
                nums[cur] = temp;
                System.out.println("交换了2");
                right--;
            } else if (nums[cur] < 1) {
                int temp = nums[left];
                nums[left] = nums[cur];
                nums[cur] = temp;
                System.out.println("交换了0");
                left++;
                cur++;
            } else {
                cur++;
            }
            System.out.println(Arrays.toString(nums) + " left:" + left + " right:" + right + " cur:" + cur);
        }
        return nums;
    }

    //子集
    private static List<List<Integer>> subsets(int[] nums) {

        backTrack2(nums, 0, new ArrayList<>());
        return res;
    }

    private static void backTrack2(int[] nums, int start, List<Integer> path) {

        res.add(new ArrayList<>(path));

        for (int i = 0; i < nums.length; i++) {
            path.add(nums[i]);
            backTrack2(nums, start + 1, path);
            path.remove(path.size() - 1);
        }
    }

    //不同的二叉搜索树
    private static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    private static TreeNode pre = null;

    //二叉树展开为链表
    private static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        root.right = pre;
        root.left = null;
        pre = root;
        TreeNode pre = null;
    }

    boolean isBST = true; //外部变量

    //验证二叉搜索树
    public boolean isValidBST(TreeNode root) {
        // write code here
        inOrder(root);
        return isBST;
    }

    // 通过遍历+外部变量实现
    private void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        /* 中序遍历位置 */
        if (pre != null && pre.val >= root.val) { //不是递增有序
            isBST = false;
            return;
        }
        pre = root; //当前节点中序遍历结束，变成前一个遍历的节点
        inOrder(root.right);
    }


    //
    private static void flattenNew(TreeNode root) {
        TreeNode node = new TreeNode(-1);
        inorder(root, node);
        return;
    }

    private static TreeNode inorder(TreeNode root, TreeNode node) {
        if (root == null) {
            return root;
        }
        inorder(root.left, node);
        node.right = root;
        node = node.right;
        inorder(root.right, node);
        return node;
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
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        Arrays.sort(nums);
        int max = 1;
        int tempMax = 1;
        for (int i = 1; i < nums.length; i++) {
            if ((nums[i - 1]) == nums[i]) {
                continue;
            }
            if ((nums[i - 1] + 1) == nums[i]) {
                tempMax++;
            } else {
                max = Math.max(tempMax, max);
                tempMax = 1;
            }
        }
        max = Math.max(tempMax, max);
        return max;
    }

    int cap;
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public LeetCode(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 将 key 变为最近使用
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            // 修改 key 的值
            cache.put(key, val);
            // 将 key 变为最近使用
            makeRecently(key);
            return;
        }

        if (cache.size() >= this.cap) {
            // 链表头部就是最久未使用的 key
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        // 将新的 key 添加链表尾部
        cache.put(key, val);
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        // 删除 key，重新插入到队尾
        cache.remove(key);
        cache.put(key, val);
    }

    public ListNode sortList(ListNode head) {
        // 1、递归结束条件
        if (head == null || head.next == null) {
            return head;
        }

        // 2、找到链表中间节点并断开链表 & 递归下探
        ListNode midNode = middleNode(head);
        ListNode rightHead = midNode.next;
        midNode.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        // 3、当前层业务操作（合并有序链表）
        return mergeTwoLists(left, right);
    }

    //  找到链表中间节点（876. 链表的中间结点）
    private ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 合并两个有序链表（21. 合并两个有序链表）
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode sentry = new ListNode(-1);
        ListNode curr = sentry;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        curr.next = l1 != null ? l1 : l2;
        return sentry.next;
    }


    //排序链表
    public ListNode sortListNew(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode middleNodeNew = middleNodeNew(head);
        ListNode rightNode = middleNodeNew.next;
        middleNodeNew.next = null;

        ListNode left = sortList(middleNodeNew);
        ListNode right = sortList(rightNode);

        return mergetTwoListsNew(left, right);
    }

    private ListNode middleNodeNew(ListNode root){
        if(root == null || root.next == null){
            return root;
        }
        ListNode slow = root;
        ListNode fast = root;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode mergetTwoListsNew(ListNode node1, ListNode node2){
        if(node1 == null){
            return node2;
        }
        if(node2 == null){
            return node1;
        }
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while(node1 != null && node2 != null){
            if(node1.val < node2.val){
                cur.next = node1;
                node1 = node1.next;
            } else {
                cur.next = node2;
                node2 = node2.next;
            }
            cur = cur.next;
        }
        cur.next = node1 == null ? node2 : node1;
        return head.next;

    }


    //hello world
    public static void main(String[] args) {
        System.out.println((longestConsecutive(new int[]{100, 4, 200, 1, 3, 2})));
        System.out.println((longestConsecutive(new int[]{1, 2, 0, 1})));
        System.out.println((longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1})));
        System.out.println((longestConsecutive(new int[]{100, 4, 200, 1, 3, 2})));
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
