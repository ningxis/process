package com.dn.algorithm.leetcode.ten;

/**
 * @author dingning
 * @date 2022/10/17 下午 08:55
 **/
public class Singleton {
    private static final Singleton villian = new Singleton();

    //指令重排序，
    private static volatile Singleton lazy = null;

    private static Singleton getInstanceByVillian(){
        return villian;
    }

    private static Singleton getInstanceByLazy(){
        if(lazy == null){
            return new Singleton();
        }
        return lazy;
    }

    //私有化构造方法,防止被初始化
    private Singleton(){

    }

    //还会有指令重排序问题
    private static Singleton getInstanceByDoubleCheck(){
        // 第一次检测
        if (lazy == null) {
            synchronized (Singleton.class){
               //第二次检测
                if(lazy == null){
                    /*
                        指令重排序：
                        1、分配对象的内存空间
                        2、初始化对象
                        3、设置instance对象指向刚才分配的内存空间地址
                        这个指令执行的顺序，可能是1、3、2，
                        如果线程A执行完1和3，instance对象还未完成初始化，但是已经不再指向null。此时线程B抢占到CPU资源，执行第第一次检测结果为false
                        ，从而返回一个还未初始化完成的instance对象，从而出导致问题出现
                     */
                    lazy = new Singleton();
                }
            }
        }
        return lazy;
    }


}
