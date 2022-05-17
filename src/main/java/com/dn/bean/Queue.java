package com.dn.bean;

import lombok.Data;

/**
 * @author dingning
 * @date 2022/5/17 20:33
 **/
@Data
public class Queue {

    //队头
    public int font;
    //队尾
    public int rear;
    //队列大小
    public int size;
    //队列数据
    public int[] data;

    public void add(int value){
        if(isFull()) {
            throw new RuntimeException("添加失败，队列已满");
        }
        rear++;
        data[rear] = value;
    }


    public int get(){
        if(isEmpty()) {
            throw new RuntimeException("获取失败，队列为空");
        }
        font++;
        return data[font];
    }

    //判断队列是否满了
    public boolean isFull(){
        return rear == size-1;
    }

    //判断队列是否满了
    public boolean isEmpty(){
        return font == size-1;
    }

}
