package com.dn.algorithm.leetcode;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author dingning
 * @date 2022/7/9 上午 11:48
 **/
public class NineWeek {

    private static String flag = "A";

    private static int printSize = 35;

    private static int count = 0;

    public static void main(String[] args) {

    }

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
}
