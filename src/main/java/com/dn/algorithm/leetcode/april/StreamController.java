package com.dn.algorithm.leetcode.april;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

/**
 * @author skyline
 * @version 1.0
 * @description:
 * @date 2023/4/10 15:04
 */
public class StreamController {




    private Integer methodA() {
        int number = 1;
        try {
            Thread.sleep(number * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return number;
    }

    private Integer methodB() {
        int number = 10;
        try {
            Thread.sleep(number * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return number;
    }


    private Integer methodC() {
        int number = 3;
        try {
            Thread.sleep(number * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return number;
    }

    private Integer methodD() {
        int number = 4;
        try {
            Thread.sleep(number * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return number;
    }

    private Integer methodE() {
        int number = 5;
        try {
            Thread.sleep(number * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return number;
    }


    private Integer methodF() {
        int number = 6;
        try {
            Thread.sleep(number * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return number;
    }

    private Integer methodG() {
        int number = 7;
        try {
            Thread.sleep(number * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return number;
    }

    @Test
    public void task(){
        long l = System.currentTimeMillis();
        CompletableFuture<Integer> futureA = CompletableFuture.supplyAsync(this::methodA);
        CompletableFuture<Integer> futureB = CompletableFuture.supplyAsync(this::methodB);
        CompletableFuture<Integer> futureC = futureA.thenApply(methodA -> {
            System.out.println(methodA);
            return methodC();
        });
        CompletableFuture<Integer> futureD = futureB.thenApply(methodB -> {
            System.out.println(methodB);
            return methodD();
        });
        CompletableFuture<Integer> futureE = CompletableFuture.supplyAsync(this::methodE);
        CompletableFuture.allOf(futureC, futureD, futureE);

        CompletableFuture<Integer> futureF = CompletableFuture.supplyAsync(this::methodF);
        CompletableFuture<Integer> futureG = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(futureA.get() + futureB.get() + futureC.get() + futureD.get() + futureE.get() + futureF.get());
            } catch (Throwable throwable) {
                handleException(throwable);
            }
            System.out.println(System.currentTimeMillis() - l);
            return methodG();
        });
        try {
            System.out.println(futureG.get());
        } catch (Throwable throwable){
            handleException(throwable);
        }



    }

    private void handleException(Throwable throwable){
        if (throwable instanceof CompletionException || throwable instanceof ExecutionException) {
            if (throwable.getCause() != null) {
                System.out.println(throwable.getCause());
            }
        }
        System.out.println(throwable.getCause());
    }


}
