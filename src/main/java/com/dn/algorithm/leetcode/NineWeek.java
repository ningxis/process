package com.dn.algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;
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

    public static void main(String[] args) {
//        merge(new int[]{1,2,3,0,0,0},3,new int[]{2,5,6},3);
        Objects.equals("","");//比较两个对象的值是否相等，且不为空
        System.out.println(leetCode26(new int[]{1,1,2}));
        System.out.println(leetCode26(new int[]{0,0,1,1,1,2,2,3,3,4}));
    }


}
