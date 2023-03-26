package com.dn.algorithm.leetcode.march;

import com.dn.bean.TreeNode;

import java.util.*;

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
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middleNodeNew = middleNodeNew(head);
        ListNode rightNode = middleNodeNew.next;
        middleNodeNew.next = null;

        ListNode left = sortList(middleNodeNew);
        ListNode right = sortList(rightNode);

        return mergetTwoListsNew(left, right);
    }

    private ListNode middleNodeNew(ListNode root) {
        if (root == null || root.next == null) {
            return root;
        }
        ListNode slow = root;
        ListNode fast = root;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode mergetTwoListsNew(ListNode node1, ListNode node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
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

    //岛屿的最大数量
    private static int maxAreaOfGrid(char[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    sum++;
                }
            }
        }
        return sum;
    }

    private static void dfs(char[][] grid, int row, int col) {
        if (!isValid(grid, row, col)) {
            return;
        }
        if (grid[row][col] != '1') {
            return;
        }
        grid[row][col] = '2';
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }


    //岛屿的最大面积
    private static int maxAreaOfGrid1(char[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    int area = dfs1(grid, i, j);
                    max = Math.max(max, area);
                }
            }
        }
        return max;
    }

    private static int dfs1(char[][] grid, int row, int col) {
        if (!isValid(grid, row, col)) {
            return 1;
        }
        if (grid[row][col] != '1') {
            return 0;
        }
        if (grid[row][col] != '0') {
            return 1;
        }
        grid[row][col] = '2';
        return 1 + dfs1(grid, row - 1, col)
                + dfs1(grid, row + 1, col)
                + dfs1(grid, row, col - 1)
                + dfs1(grid, row, col + 1);
    }

    private static boolean isValid(char[][] grid, int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    // 节点的入度: 使用数组保存每个节点的入度,
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1.课号和对应的入度
        Map<Integer, Integer> inDegree = new HashMap<>();
        // 将所有的课程先放入
        for (int i = 0; i < numCourses; i++) {
            inDegree.put(i, 0);
        }
        // 2.依赖关系, 依赖当前课程的后序课程
        Map<Integer, List<Integer>> adj = new HashMap<>();


        // 初始化入度和依赖关系
        for (int[] relate : prerequisites) {
            // (3,0), 想学3号课程要先完成0号课程, 更新3号课程的入度和0号课程的依赖(邻接表)
            int cur = relate[1];
            int next = relate[0];
            // 1.更新入度
            inDegree.put(next, inDegree.get(next) + 1);
            // 2.当前节点的邻接表
            if (!adj.containsKey(cur)) {
                adj.put(cur, new ArrayList<>());
            }
            adj.get(cur).add(next);
        }

        // 3.BFS, 将入度为0的课程放入队列, 队列中的课程就是没有先修, 可以学的课程
        Queue<Integer> q = new LinkedList<>();
        for (int key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                q.offer(key);
            }
        }
        // 取出一个节点, 对应学习这门课程.
        // 遍历当前邻接表, 更新其入度; 更新之后查看入度, 如果为0, 加入到队列
        while (!q.isEmpty()) {
            int cur = q.poll();
            // 遍历当前课程的邻接表, 更新后继节点的入度
            if (!adj.containsKey(cur)) {
                continue;
            }
            List<Integer> successorList = adj.get(cur);

            for (int k : successorList) {
                inDegree.put(k, inDegree.get(k) - 1);
                if (inDegree.get(k) == 0) {
                    q.offer(k);
                }
            }
        }

        // 4.遍历入队, 如果还有课程的入度不为0, 返回fasle
        for (int key : inDegree.keySet()) {
            if (inDegree.get(key) != 0) {
                return false;
            }
        }
        return true;
    }

    //实现 Trie (前缀树)
    class TrieNode {
        boolean isEnd;
        TrieNode[] next;

        TrieNode() {
            this.isEnd = false;
            this.next = new TrieNode[26];
        }
    }

    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.next[ch - 'a'] == null) {
                node.next[ch - 'a'] = new TrieNode();
            }
            node = node.next[ch - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node.next[ch - 'a'] == null) {
                return false;
            }
            node = node.next[ch - 'a'];
        }
        return node.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (node.next[ch - 'a'] == null) {
                return false;
            }
            node = node.next[ch - 'a'];
        }
        return true;
    }

    //数组中的第K个最大元素
    private int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.add(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.poll();
    }

    //最大正方形
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }
        int height = matrix[0].length;
        int width = matrix.length;
        int maxSquare = 0;
        int[][] dp = new int[width + 1][height + 1];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (matrix[i][j] == '1') {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]), dp[i][j]) + 1;
                    maxSquare = Math.max(maxSquare, dp[i + 1][j + 1]);
                }
            }
        }
        return maxSquare * maxSquare;
    }

    //二叉树的最近公共祖先
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            //只要当前根节点是p和q中的任意一个，就返回（因为不能比这个更深了，再深p和q中的一个就没了）
            return root;
        }
        //根节点不是p和q中的任意一个，那么就继续分别往左子树和右子树找p和q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //p和q都没找到，那就没有
        if (left == null && right == null) {
            return null;
        }
        //左子树没有p也没有q，就返回右子树的结果
        if (left == null) {
            return right;
        }
        //右子树没有p也没有q就返回左子树的结果
        if (right == null) {
            return left;
        }
        //左右子树都找到p和q了，那就说明p和q分别在左右两个子树上，所以此时的最近公共祖先就是root
        return root;
    }

    //除自身以外数组的乘积
    public static int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length == 0){
            return nums;
        }
        int p = 1, q = 1;
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = p;
            p *= nums[i];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = q * res[i];
            q *= nums[i];
        }
        return res;
    }

    //搜索二维矩阵 II
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int row = 0;
        int col = matrix[0].length;
        while(matrix[row][col] != target){
            if(matrix[row][col] < target){
                row++;
            } else if(matrix[row][col] > target){
                col--;
            } else{
                return true;
            }
        }
        return false;
    }

    public static boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            if(matrix[i][0] > target){
                break;
            }
            if(matrix[i][matrix[0].length - 1] < target){
                continue;
            }
            int result = binarySearch(matrix[i], target);
            if(result != -1){
                return true;
            }
        }
        return false;
    }

    //二分查找
    private static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while(left <= right){
            mid = left + (right - left) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            } else if(nums[mid] < target){
                left = mid + 1;
            } else {
                return 1;
            }
        }
        return -1;
    }


    //hello world
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        System.out.println(searchMatrix1(matrix, 5));
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
