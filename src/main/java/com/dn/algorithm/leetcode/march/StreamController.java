package com.dn.algorithm.leetcode.march;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author dingning
 * @date 2023/4/8 下午 09:54
 **/
public class StreamController {


    private int methodA(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private int methodB(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2;
    }

    private int methodC(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 3;
    }

    private int methodD(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 4;
    }

    private int methodE(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 5;
    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> AToCFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("结果输出：" + methodA());
            return methodC();
        });
        CompletableFuture<Integer> BToDFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("结果输出：" + methodB());
            return methodD();
        });
        CompletableFuture<Integer> EFuture = CompletableFuture.supplyAsync(this::methodE);
        CompletableFuture.allOf(AToCFuture, BToDFuture, EFuture).join();
        System.out.println(AToCFuture.get() + BToDFuture.get() + EFuture.get());
    }
}
