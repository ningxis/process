package com.dn.algorithm.leetcode;

import java.util.*;

/**
 * @author dingning
 * @date 2024/3/30 下午 05:03
 **/
public class April {

    public static void main(String[] args) {
//        int[] arrays = new int[]{50,40,70,90,80,10,20,60,12,12,1,1,0,0,2,3,4,5,3,2,1,0,4,23,7};
        int[] arrays = new int[]{50,40,70,90,80,10,20,60};
        System.out.println("排序前：" + Arrays.toString(arrays));
        quickSort3( 0, arrays.length - 1,arrays);
        System.out.println("排序后：" + Arrays.toString(arrays));
    }

    private static void quickSort2(int[] arrays, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int j = right;
        int privot = arrays[left];
        while (i < j) {
            while (i < j && arrays[j] >= privot) {
                j--;
                arrays[i] = arrays[j];
            }

            while (i < j && arrays[i] <= privot) {
                i++;
                arrays[j] = arrays[i];
            }

//            if (i < j) {
//                swap(i, j, arrays);
//                System.out.println("排序中：" + Arrays.toString(arrays));
//            }
        }
        arrays[left] = privot;
//        swap(i, left, arrays);
        quickSort2(arrays, left, i - 1);
        quickSort2(arrays, j + 1, right);
    }


    private static void quickSort(int[] arrays, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = partition(left, right, arrays);
        quickSort(arrays, left, mid - 1);
        quickSort(arrays, mid + 1, right);
    }

    private static int partition(int i, int j, int[] arrays) {
        int privot = arrays[i];
        int left = i;
        int right = j;
        while (left < right) {
            while (left < right && arrays[right] >= privot) {
                right--;
            }
            while (left < right && arrays[left] <= privot) {
                left++;
            }
            if (left < right) {
                swap(left, right, arrays);
                System.out.println("中间位置是"+ privot+"交换" + left + "和" + right);
                System.out.println("排序中：" + Arrays.toString(arrays));
            }
        }
//        swap(left, right, arrays);
//        arrays[left] = privot;
        swap(i, left, arrays);
        return left;
    }



    private static void quickSort3(int i, int j, int[] a) {
        if (i >= j) {
            return;
        }
        int mid = partition2(i, j, a);
        quickSort3(i, mid - 1, a);
        quickSort3(mid + 1, j, a);
    }
    private static int partition2(int i, int j, int[] a) {
        int key = a[i];
        int left = i;
        int right = j;
        while(left <right) {
            while(left < right && a[right] >= key) {
                right--;
            }
            while(left < right && a[left] <= key) {
                left++;
            }
            if (left < right) {
                swap(left, right, a);
            }
        }
        swap(i, left, a);
        return left;
    }

    private static void swap(int left, int right, int[] arrays) {
        int temp = arrays[left];
        arrays[left] = arrays[right];
        arrays[right] = temp;
    }

    private static void testCollection() {
        List<Integer> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(1212);
        list.add(10, 45673);
        list.add(10, 45672);
        list.add(10, 45671);
        list.add(10, 45670);
        list.add(10, 45689);
        System.out.println(list.size());
        System.out.println(Arrays.toString(list.toArray()));
        map.put("e3", "3241");
        map.put("chenmo", "沉默");
        map.put("wanger", "王二");
        map.put("chenqingyang", "陈清扬");
        map.put("xiaozhuanling", "小转铃");
        map.put("fangxiaowan", "方小婉");

// 遍历 HashMap
        for (String key : map.keySet()) {
            int h = 0;
            int n = 16;
            int hash = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
            System.out.println(h);
            int i = (n - 1) & hash;
            // 打印 key 的 hash 值 和 索引 i
            System.out.println(key + "的hash值 : " + hash + " 的索引 : " + i);
        }
        int aa = Integer.MAX_VALUE;
        System.out.println(aa >> 2);
        System.out.println(aa >> 1);
        System.out.println(aa << 1);
        System.out.println(aa * 2);
        System.out.println(aa * 4);
        System.out.println(aa << 2);
    }
}
