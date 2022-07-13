package com.dn.algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author dingning
 * @date 2022/7/9 上午 11:48
 * dingning 2022/7/9 下午 11:55  未能实现可配置化，也不能完全循环打印，还有线程间通信写法未尝试
 * 晚上是安静的好时候，适合写代码思考，有看到一些导航网站，真的挺棒的，感叹互联网真的给了我们太多便利，信息差就是我们效率的提升方向
 * 想做的还很多，继续加油
 *
 *         世界上唯有两样东西能让我们的内心受到深深的震撼，一是我们头顶浩瀚灿烂的星空，一是我们心中崇高的道德法则。
 *         ——康德《实践理性批判》
 *
 *          */
public class NineWeek {

    private static String flag = "A";

    private static int printSize = 35;

    private static int count = 0;



    //循环打印abc
    //只能打印28次
    @Test
    public void cyclePrintABC() {
        Thread threadA = new Thread(() -> {
            while (true) {
                if ("A".equals(flag) && count < printSize) {
                    count++;
                    System.out.println("第" + count + "次打印");
                    System.out.println("A");
                    flag = "B";
                }
            }
        });
        Thread threadB = new Thread(() -> {
            while (true) {
                if ("B".equals(flag)  && count < printSize) {
                    System.out.println("B");
                    flag = "C";
                }
            }
        });
        Thread threadC = new Thread(() -> {
            while (true) {
                if ("C".equals(flag)  && count < printSize) {
                    System.out.println("C");
                    flag = "A";
                }
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }

    @Test
    public void cyclePrintABCByCallable(){
        FutureTask<String> futureTask = new FutureTask<>(()->{
            System.out.println("A");
            return "A";
        });
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int tmp = nums1[0];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(tmp >= nums2[j] || nums1[i] == 0){
                    tmp = nums1[i];
                    nums1[i] = nums2[j];
                }else{
                    tmp = nums2[j];
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(nums1));
    }

    private static void mergeCopy(int[] nums1, int m, int[] nums2, int n){
        if (nums1 == null && nums2 == null) {
            return;
        }
        int[] tmp = new int[m + n];
        int index = 0;
        int left = 0;
        int right = 0;
        while (left < m && right < n) {
            if (nums1[left] > nums2[right]) {
                tmp[index] = nums2[right];
                right++;
            }else{
                tmp[index] = nums1[left];
                left++;
            }
            index++;
        }
        while (left < m) {
            tmp[index] = nums1[left];
            left++;
            index++;
        }
        while (right < n) {
            tmp[index] = nums2[right];
            right++;
            index++;
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = tmp[i];
        }
    }

    private static void mergeCopy1(int[] nums1, int m, int[] nums2, int n){
        //1.check
        if (nums1 == null && nums2 == null) {
            return;
        }
        if (nums1 == null && nums2 != null) {
            nums1 = nums2;
        }
        //2.init
        int[] temp = new int[m + n];
        int index1 = 0, index2 = 0;
        //3.excute
        for (int i = 0; i < m + n; ++i) {
            if (index1 >= m) {
                temp[i] = nums2[index2];
                index2++;
            } else if (index2 >= n) {
                temp[i] = nums1[index1];
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                temp[i] = nums2[index2];
                index2++;
            } else {
                temp[i] = nums1[index1];
                index1++;
            }
        }
        //4.exchange
        for (int j = 0; j < m + n; ++j) {
            nums1[j] = temp[j];
        }
    }

    private static int leetCode26(int[] nums){
        if(nums.length == 0){
            return 0;
        }
        //用快慢指针解决问题
        int tmp = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[tmp] != nums[i]){
                tmp++;
                nums[tmp] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return tmp + 1;
    }

    private static int leetCode26Copy(int[] nums){
        if(nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while(q < nums.length){
            if(nums[p] != nums[q]){
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        System.out.println(Arrays.toString(nums));
        return p + 1;
    }

    private static int leetCode28(String haystack, String needle){
        if(haystack.length() == 0 || needle.length() == 0){
            return 0;
        }
        if(haystack.equals(needle)){
            return 0;
        }
        for (int i = 0; i < haystack.length(); i++) {
            if(haystack.startsWith(needle, i)){
                return i;
            }
        }
        return -1;
    }

    //kmp算法
    public int strStr(String text, String pattern) {
        if(text == null || pattern == null){
            return -1;
        }
        if(pattern.length() == 0){
            return 0;
        }
        // 首先得到前缀表
        // 前缀表是一个一维数组，数组的下标表示遍历pattern的指针位置(j),数组的值表示当pattern的指针j和text的指针i对应的字符内容不同时(坏字符),j指针需要移动
        // 到pattern的位置
        int[] next = getNext(pattern);
        // 定义两个指针，i用来遍历text，j用来遍历pattern
        int i = 0;
        int j = 0;
        char[] charsT = text.toCharArray();
        char[] charsP = pattern.toCharArray();
        while(j < charsP.length && i < charsT.length){
            // 当指针i和j位置上的元素不匹配时，需要将j指针通过next数组移动到指定位置上
            if(charsT[i] != charsP[j]){
                j = next[j];
                // 如果j等于-1，说明j指针已经pattern的最前面了，并且已经没有共有字符串了，直接将j指针和i指针同时往后移动一位j++
                // 这里就能体现将next[0]设置成-1的巧妙了，这样j++，i++的时候就意味着把text当做一个全新的字符串，除去了[0,i)之前的元素，j=0也从pattern
                // 的起始位置开始匹配
                if(j == -1){
                    i++;
                    j++;
                }
            }else{
                //如果匹配上，那就正常进行下一个字符的匹配
                i++;
                j++;
            }
        }
        // 循环结束后，如果j和pattern长度相同，说明全部匹配完了，也就是在text中找到了第一次出现pattern的位置，这时候i指针已经到了pattern字符位置的最后一个字符的位置
        // 起始位置需要减掉pattern的长度
        if(j == charsP.length){
            return i - j;
        }
        return -1;
    }

    public int[] getNext(String pattern){
        // 先求出字符串的前缀表
        char[] charArr = pattern.toCharArray();
        int[] next = new int[charArr.length];
        // 因为字符串的第一个元素没有前后缀，所以共有最大字符串长度为0
        next[0] = 0;
        int len = 0;
        int i = 1;
        /*
            i   str          next[i]
            0   A            0
            1   AB           0
            2   ABA          1
            3   ABAB         2
            4   ABABC        0
            5   ABABCA       1
            6   ABABCAB      2
            7   ABABCABA     3
            8   ABABCABAA    1
        */
        while (i < charArr.length){
            // 1.举例：比如这次进来的字符串是上面的AB,此时上一次的共有字符串长度是len=0(因为上一次就一个A字符，没有共有字符串，当然是0)，
            // 要想判断这次共有字符串长度会不会加1，只需要判断这次的字符串AB比上次字符串A多出来的字符(也就是B)是不是和上次共有字符串长度位置上的字符相等
            // 也就是charArr[1(i)] == charArr[0(len)]?，这里是不等，所以不能加1
            // 2.比如这次进来的是ABA，上一次是AB，那么多出来的这个A和上次AB的共有字符串长度位置(len=0)上的字符是否相等，显然charArr[0] == charArr[2]，所以len++;
            // 3.再比如：这次进来的是ABAB,上一次是ABA,上一次的共有字符串长度是len=1，判断这次多出来的字符B是不是和charArr[1]相等，显然相等，len++;
            if(charArr[i] == charArr[len]){
                len++;
                next[i] = len;
                i++;
            }else{
                // 如果不相等，说明这次多出来的这个字符并没有让共有字符串的长度加1，而且，可能还会直接影响到上一次的共有字符串长度
                // 这里的做法是：因为多出来一个字符，而且charArr[i] != charArr[len]，那这次已经不能拿上一次共有字符串位置上的字符来做比较了，必须拿上上一次的结果
                // 比如：这次进来的是ABABC,上一次是ABAB，上一次共有字符串是AB,len=2,那charArr[2(len)]是A，和这次的多出来的C已经不一样了，那上次的len已经不能作为判断依据了，
                // 必须拿上上一次的len,于是i不变，也就是说下一轮循环还是ABABC，但是len要拿上上一轮的长度，也就是AB这个字符串共有字符串的长度值，len=1，
                // 此时charArr[1(len)]是B，还是和C不相同，说明这次的len还是不能作为判断，于是i继续不变，下一轮还是ABABC，len拿A的共有字符串长度值，len=0，
                // 此时charArr[0(len)]是A，还是和C不相同，说明这次的len还是不能作为判断，理论上还得去那更早一次的len值，但是这时候有个临界情况，因为已经拿到第一次进来的len了，
                // len拿不到更早一次的值了，或者说到这已经没有共有字符串了，说明这次加多出来的字符C。彻底让这个字符串ABABC没有了共有字符串，也就是len=0，可以放心的将这一轮字符串
                // 的共有字符串长度设为0了，这轮len值设置完毕，i++，进行下一轮设置
                if(len > 0){
                    len = next[len-1];
                }else{
                    next[i] = len;
                    i++;
                }
            }
        }
        // 到此，前缀表已经设置完毕，但是有个问题，就是next[0]和next[1]的位置一直都是0，为了后续使用的方便，需要将""和只有一个字符的字符串共有前缀区别开，
        // 而且，对共有字符串来说，前缀表的最后一项就是字符串本身的共有字符串长度，这个在实际使用的时候没有意义，所以直接将整个前缀表往后平移一位，空出来的
        // next[0]赋值为-1
        for (int j = next.length  -1; j > 0; j--) {
            next[j] = next[j-1];
        }
        next[0] = -1;
//        for (int m = 0; m < next.length; m++) {
//            System.out.print(next[m] + "");
//        }
        return next;
    }

    private static int leetCode35(int[] nums,int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
            if(map.get(target) != null){
                return map.get(target);
            }
            if(target < nums[i]){
                return i;
            }
        }
        return nums.length;
    }


    //最大子数组和
    private static int leetCode53(int[] nums){
        //滑动窗口实现
        if(nums.length == 0){
            return 0;
        }
        return 1;
    }

    //算法题目前碰到的几种解法：递归、滑动窗口、双指针、动态规划、分治
    public static void main(String[] args) {
//        merge(new int[]{1,2,3,0,0,0},3,new int[]{2,5,6},3);
        Objects.equals("","");//比较两个对象的值是否相等，且不为空
//        System.out.println(leetCode26(new int[]{1,1,2}));
//        System.out.println(leetCode26(new int[]{0,0,1,1,1,2,2,3,3,4}));
//        System.out.println(leetCode28("hello","ll"));
        System.out.println(leetCode35(new int[]{1},1));
    }


}
