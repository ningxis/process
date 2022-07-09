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
        //todo:dingning 2022/7/9 下午 11:55  未能实现可配置化，也不能完全循环打印，还有线程间通信写法未尝试
        //晚上是安静的好时候，适合写代码思考，有看到一些导航网站，真的挺棒的，感叹互联网真的给了我们太多便利，信息差就是我们效率的提升方向
        //想做的还很多，继续加油
        /*
        世界上唯有两样东西能让我们的内心受到深深的震撼，一是我们头顶浩瀚灿烂的星空，一是我们心中崇高的道德法则。
        ——康德《实践理性批判》

         */
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
