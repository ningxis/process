package com.dn.algorithm.leetcode.ten;

/**
 * @author dingning
 * @date 2022/10/20 下午 11:07
 **/
public class Single {

    private Single(){

    }

    //饿汉式
    private static volatile Single single = new Single();


    public static Single getInstance(){
        return single;
    }

    //懒汉式
    private static Single single1 = null;
    public static Single getInstance1(){
        if(single1 == null){
            single1 = new Single();
        }
        return  single1;
    }


    private static volatile Single single2;

    //双检锁
    public static Single getInstance2(){
        if(single2 == null){
            synchronized (Single.class){
                if(single2 == null){
                    single2 = new Single();
                }
            }
        }
        return single2;
    }

    //todo:dingning 2022/10/22 下午 02:49  volatile、if


}
