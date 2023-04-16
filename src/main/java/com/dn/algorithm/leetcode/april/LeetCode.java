package com.dn.algorithm.leetcode.april;

import java.util.*;

/**
 * @author dingning
 * @date 2023/4/12 下午 10:01
 **/
public class LeetCode {

    public class ListNode {
        int val;
        ListNode next, prev;

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

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        if (head.next == null) {
            return head;
        }
        //迭代从前到后
        // ListNode prev = null;
        // ListNode cur = head;
        // while(cur != null){
        //     ListNode temp = cur.next;
        //     cur.next = prev;
        //     prev = cur;
        //     cur = temp;
        // }
        // return prev;
        //递归从后到前
        // ListNode last = reverseList(head.next);
        // head.next.next = head;
        // head.next = null;
        // return last;
        //递归从前到后
        // return reverse(null, head);
        //栈
        if (head == null || head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode node = new ListNode(-1);
        ListNode cur = node;
        while (stack.size() > 0) {
            ListNode temp = stack.pop();
            cur.next = temp;
            cur = temp;
        }
        cur.next = null;
        return node.next;
    }

    private ListNode reverse(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;
        }
        ListNode node = cur.next;
        cur.next = prev;
        return reverse(cur, node);
    }

    //二分查找 左闭右闭
    private int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                return mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /*左闭右开区间查找
        while(left < right)，这里使用 < ,因为left == right在区间[left, right)是没有意义的
        if (nums[middle] > target) right 更新为 middle，因为当前nums[middle]不等于target，去左区间继续寻找，而寻找区间是左闭右开区间，所以right更新为middle，即：下一个查询区间不会去比较nums[middle]
    */
    private int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] == target) {
                return mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    //移除元素
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (val != nums[fastIndex]) {
                slowIndex++;
                nums[slowIndex] = nums[fastIndex];
            }
        }
        return slowIndex;
    }

    //双循环移除元素
    public int removeElement2(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int number = nums.length;
        for (int i = 0; i < number; i++) {
            if (nums[i] == val) {
                //所有元素左移,number数组容量也减小
                for (int j = i + 1; j < number; j++) {
                    nums[j - 1] = nums[j];
                }
                number--;//此处的number值减小之后，相当于循环少处理了
                i--;//左移之后i要重新处理
            }
        }
        return number;
    }

    //长度最小的子数组
    public static int minSubArrayLen(int target, int[] nums) {
        //暴力法
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int min = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum < target) {
                for (int j = i + 1; j < nums.length; j++) {
                    sum += nums[j];
                    if (sum >= target) {
                        min = Math.min(min, j - i + 1);
                        break;
                    }
                }
            } else {
                return 1;
            }
        }
        return min == nums.length ? 0 : min;//有可能不存在这个数
    }

    //滑动窗口
    public static int minSubArrayLen2(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int sum = 0;
        int min = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                end = i;
                min = Math.min(min, end - start + 1);
                sum -= nums[start];
                start++;
            }
        }
        return min == nums.length ? 0 : min;
    }


    //螺旋矩阵 II
    public static int[][] generateMatrix(int n) {
        int startX = 0;
        int startY = 0;
        int loop = n / 2;
        int mid = n / 2;
        int count = 1;
        int offset = 1;
        int[][] nums = new int[n][n];
        int i, j;
        while (loop-- > 0) {
            //上边
            for (j = startY; j < n - offset; j++) {
                nums[startX][j] = count++;
            }
            //右边
            for (i = startX; i < n - offset; i++) {
                nums[i][j] = count++;
            }
            //下边
            for (; j > startX; j--) {
                nums[i][j] = count++;
            }
            //左边
            for (; i > startY; i--) {
                nums[i][j] = count++;
            }
            // 第二圈开始的时候，起始位置要各自加1， 例如：第一圈起始位置是(0, 0)，第二圈起始位置是(1, 1)
            // offset 控制每一圈里每一条边遍历的长度
            offset += 1;
            startX++;
            startY++;
            offset++;
        }
        if (n % 2 == 1) {
            nums[mid][mid] = count;
        }
        return nums;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode node = new ListNode(-1, head);
        ListNode cur = node;
        ListNode pre = node;
        while (cur != null) {
            if (head.val != val) {
                cur.next = head;
                head = head.next;
                cur = cur.next;
                pre = head;
            } else {
                pre.next = head.next;
                head = head.next;
                cur = head;
            }
        }
        return node.next;
    }

    /**
     * Your MyLinkedList object will be instantiated and called as such:
     * MyLinkedList obj = new MyLinkedList();
     * int param_1 = obj.get(index);
     * obj.addAtHead(val);
     * obj.addAtTail(val);
     * obj.addAtIndex(index,val);
     * obj.deleteAtIndex(index);
     */

    //单链表

    //记录链表中元素的数量  //size存储链表元素的个数
    int size;
    //记录链表的虚拟头结点和尾结点
    ListNode head, tail;


    //初始化链表
    public LeetCode() {
        size = 0;
        head = new ListNode(0);

        //双链表
        //初始化操作
        this.size = 0;
        this.head = new ListNode(0);
        this.tail = new ListNode(0);
        //这一步非常关键，否则在加入头结点的操作中会出现null.next的错误！！！
        head.next = tail;
        tail.prev = head;
    }

    //获取第index个节点的数值，注意index是从0开始的，第0个节点就是头结点
    public int get(int index) {
        //如果index非法，返回-1
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode currentNode = head;
        //包含一个虚拟头节点，所以查找第 index+1 个节点
        for (int i = 0; i <= index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.val;
    }

    //在链表最前面插入一个节点，等价于在第0个元素前添加
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    //在链表的最后插入一个节点，等价于在(末尾+1)个元素前添加
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
    // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
    // 如果 index 大于链表的长度，则返回空
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }
        size++;
        //找到要插入节点的前驱
        ListNode pred = head;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        ListNode toAdd = new ListNode(val);
        toAdd.next = pred.next;
        pred.next = toAdd;
    }

    //删除第index个节点
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;
        if (index == 0) {
            head = head.next;
            return;
        }
        ListNode pred = head;
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }
        pred.next = pred.next.next;
    }

    //双链表
    public int get2(int index) {
        //判断index是否有效
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode cur = this.head;
        //判断是哪一边遍历时间更短
        if (index >= size / 2) {
            //tail开始
            cur = tail;
            for (int i = 0; i < size - index; i++) {
                cur = cur.prev;
            }
        } else {
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
        }
        return cur.val;
    }

    public void addAtHead2(int val) {
        //等价于在第0个元素前添加
        addAtIndex(0, val);
    }

    public void addAtTail2(int val) {
        //等价于在最后一个元素(null)前添加
        addAtIndex(size, val);
    }

    public void addAtIndex2(int index, int val) {
        //index大于链表长度
        if (index > size) {
            return;
        }
        //index小于0
        if (index < 0) {
            index = 0;
        }
        size++;
        //找到前驱
        ListNode pre = this.head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        //新建结点
        ListNode newNode = new ListNode(val);
        newNode.next = pre.next;
        pre.next.prev = newNode;
        newNode.prev = pre;
        pre.next = newNode;

    }

    public void deleteAtIndex2(int index) {
        //判断索引是否有效
        if (index < 0 || index >= size) {
            return;
        }
        //删除操作
        size--;
        ListNode pre = this.head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next.next.prev = pre;
        pre.next = pre.next.next;
    }

    //两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // ListNode dummy = new ListNode(0, head);
        // ListNode cur = dummy;
        // ListNode first,second,temp;
        // while(cur.next != null && cur.next.next != null){
        //     temp = cur.next.next.next;
        //     first = cur.next;
        //     second = cur.next.next;
        //     cur.next = second;
        //     second.next = first;
        //     first.next = temp;
        //     cur = first;
        // }
        // return dummy.next;
        ListNode next = head.next;
        ListNode newNode = swapPairs(next.next);
        next.next = head;
        head.next = newNode;
        return next;
    }

    //删除链表的倒数第N个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        int depth = getDepth(head);
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        for (int i = 0; i < depth - n; i++) {
            head = head.next;
        }
        head.next = head.next.next;
        return dummy;
    }

    private int getDepth(ListNode head) {
        int depth = 0;
        while (head != null) {
            head = head.next;
            depth++;
        }
        return depth;
    }

    //环形链表2
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode fast1 = head;
                ListNode slow1 = slow;
                while (fast1 != slow1) {
                    fast1 = fast1.next;
                    slow1 = slow1.next;
                }
                return fast1;
            }
        }
        return null;
    }

    //有效的字母异位词
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            }
            if (map.get(c) == 1) {
                map.remove(c);
            } else {
                map.put(c, map.get(c) - 1);
            }
        }
        return map.isEmpty();
    }

    //两个数组的交集
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        for (int i : nums2) {
            if (!set.contains(i)) {
                res.add(i);
            }
        }
        int[] nums = new int[res.size()];
        int index = 0;
        for (Integer integer : res) {
            nums[index] = integer;
            index++;
        }
        return nums;
    }

    //快乐数
    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        System.out.println("---------入参：-----" + n);
        while (n != 0) {
            int sum = 0;
            while (n != 0) {
                int temp2 = n % 10;
                sum += temp2 * temp2;
                n /= 10;
            }
            if (sum == 1) {
                return true;
            }
            n = sum;
            System.out.println("出参：" + sum);
            if (!set.add(n)) {
                return false;
            }
        }
        return false;
    }

    //四数之和2
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        //超出时间限制
//        int sum = 0;
//        int count = 0;
//        for (int i : nums1) {
//            for (int i1 : nums2) {
//                    for (int i2 : nums3) {
//                        for (int i3 : nums4) {
//                            sum += i + i1 + i2 + i3;
//                            System.out.println(sum);
//                            if(sum == 0){
//                                count++;
//                            }
//                        }
//                    }
//            }
//        }
//        return count;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            for (int i1 : nums2) {
                int sum = i + i1;
                if (map.containsKey(sum)) {
                    map.put(sum, (map.get(sum)) + 1);
                } else {
                    map.put(sum, 1);
                }
            }
        }

        for (int i : nums3) {
            for (int i1 : nums4) {
                int sum = i + i1;
                if (map.containsKey(-sum)) {
                    res++;
                }
            }
        }
        return res;
    }

    //赎金信
    public boolean canConstruct(String ransomNote, String magazine) {
//        if (magazine == null || magazine.length() == 0) {
//            return false;
//        }
//        Map<Character, Integer> map = new HashMap<>();
//        for (Character c : magazine.toCharArray()) {
//            if (map.containsKey(c)) {
//                map.put(c, map.get(c) + 1);
//            } else {
//                map.put(c, 1);
//            }
//        }
//        for (Character c : ransomNote.toCharArray()) {
//            if(map.containsKey(c)){
//                if(map.get(c) > 1){
//                    map.put(c, map.get(c) - 1);
//                } else {
//                    map.remove(c);
//                }
//            } else {
//                return false;
//            }
//        }
//        return true;
        int[] record = new int[26];
        for (char c : magazine.toCharArray()) {
            record[c - 'a'] += 1;
        }
        for (char c : ransomNote.toCharArray()) {
            record[c - 'a'] -= 1;
            if (record[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    //三数之和
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            //初始值大于0，就不用考虑后面的情况
            if(first > 0){
                break;
            }
            //过滤相同元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //注意set的位置，存放后续已经遍历过的元素
            Set<Integer> set = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                int third = nums[j];
                int second = -(first + third);
                if (set.contains(second)){
                    res.add(Arrays.asList(first, second, third));
                    //过滤相同元素
                    while (j < nums.length - 1 && nums[j + 1] == nums[j]){
                        j++;
                    }
                }
                //遍历过的元素全部存下来
                set.add(third);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }
}
