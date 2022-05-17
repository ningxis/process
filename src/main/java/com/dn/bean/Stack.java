package com.dn.bean;

/**
 * @author dingning
 * @date 2022/5/17 19:29
 **/
public class Stack {

    int[] data;

    public Stack(){
        data = new int[0];
    }

    //添加一个元素
    public void add(int value){
        int[] ints = new int[data.length+1];
        for (int i = 0; i < data.length; i++) {
            ints[i] = data[i];
        }
        ints[data.length] = value;
        data = ints;
    }

    public int get(){
        int[] ints = new int[data.length-1];
        for (int i = 0; i < data.length -1; i++) {
            ints[i] = data[i];
        }
        data = ints;
        return data[data.length -1];
    }



}
