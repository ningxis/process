package com.dn;

import com.dn.bean.ProcessApplicationContext;
import com.dn.exception.ServiceResult;
import com.dn.service.ActivityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

@SpringBootTest
class ProcessApplicationTests {

    @Autowired
    private ActivityServiceImpl activityService;

    @Test
    void contextLoads() {
        ProcessApplicationContext context = new ProcessApplicationContext();
        context.setAge(9);
        context.setUserName("长安");
        context.setUserId(11);
        ServiceResult result = activityService.activityTrigger(context);
        System.out.println(result);
    }

    @Test
    // 参考文章 https://blog.csdn.net/admin123404/article/details/111168902
    void testAsync() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = new ForkJoinPool();
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("Hello World"),threadPool);
        System.out.println(future.get());
        //如果方法存在executor参数，就使用executor执行任务;
        //否则默认使用公用的ForkJoinPool.commonPool()作为执行异步任务的线程池。
        CompletableFuture<ProcessApplication> future1 = CompletableFuture.supplyAsync(ProcessApplication::new, threadPool);
        System.out.println(future1.get());
        //异步线程任务执行完后调用 whenComplete 方法
        future1.whenComplete((processApplication, throwable) -> System.out.println("执行完成之后的回调"));

    }

}
