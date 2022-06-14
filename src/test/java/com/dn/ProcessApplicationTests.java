package com.dn;

import com.dn.bean.ProcessApplicationContext;
import com.dn.exception.ServiceResult;
import com.dn.rpc.dubbo.MessageService;
import com.dn.rpc.dubbo.MessageServiceImpl;
import com.dn.rpc.dubbo.RpcFramework;
import com.dn.service.ActivityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

@SpringBootTest
class ProcessApplicationTests {

    @Autowired
    private ActivityServiceImpl activityService;



    @Test
    //权限修饰符必须为public才能运行测试

    //如果指定元素尚不存在，则将其添加到此集合中（可选操作）。更正式地说，如果集合不包含元素 e2，则将指定的元素 e 添加到此集合中，
    // 使得 (e==null ?e2==null:e.equals(e2))。如果该集合已包含该元素，则调用将保持该集合不变并返回 false。
    // 结合对构造函数的限制，这确保了集合永远不会包含重复的元素。上述规定并不意味着集合必须接受所有元素；
    // 集合可以拒绝添加任何特定元素，包括 null，并抛出异常，如 Collection.add 规范中所述。
    // 单独的集合实现应该清楚地记录对它们可能包含的元素的任何限制。参数：e - 要添加到此集合的元素返回：
    // 如果此集合尚未包含指定元素，则为 true 抛出：UnsupportedOperationException - 如果此集合不支持添加操作 ClassCastException
    // - 如果指定元素的类阻止它被添加到这个集合 NullPointerException – 如果指定元素为 null 并且这个集合不允许空元素 IllegalArgumentException
    // – 如果指定元素的某些属性阻止它被添加到这个集合
    public void testSet(){
        Set<String> set = new HashSet<>();
        set.add("!");
        set.add("1");
        set.add("341134321421");
        set.add("32");
        set.add("323");
        set.add("5345535");
        boolean add = set.add("!");
        System.out.println(add);//返回元素是否添加
        add = set.add(null);
        System.out.println(add);//是否能添加null
        System.out.println(set);
        //结果打印：false
        //true
        //[null, !, 1, 3411343241421, 323, 32, 5345535]
    }

    @Test
    public void producer(){
        //服务提供者只需要暴露出接口
        MessageService service = new MessageServiceImpl();
        try {
            RpcFramework.export(2333, service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void consumer(){
        //服务调用者只需要设置依赖
        MessageService service = null;
        try {
            service = RpcFramework.refer(MessageService.class, "127.0.0.1", 2333);
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.sayHello();
    }

    /*
        a b e
        c d
     */

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
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("Hello World"), threadPool);
        System.out.println(future.get());
        //如果方法存在executor参数，就使用executor执行任务;
        //否则默认使用公用的ForkJoinPool.commonPool()作为执行异步任务的线程池。
        CompletableFuture<ProcessApplication> future1 = CompletableFuture.supplyAsync(ProcessApplication::new, threadPool);
        System.out.println(future1.get());
        //异步线程任务执行完后调用 whenComplete 方法
        future1.whenComplete((processApplication, throwable) -> System.out.println("执行完成之后的回调"));

    }

    @Test
    void testHelloWorld(){
        System.out.println(randomString(-229985452) + " " + randomString(-147909649));
    }



    public static String randomString(int i) {
        Random ran = new Random(i);
        StringBuilder sb = new StringBuilder();
        while (true) {
            int k = ran.nextInt(27);
            if (k == 0)
                break;

            sb.append((char) ('`' + k));
        }
        return sb.toString();
    }

}
