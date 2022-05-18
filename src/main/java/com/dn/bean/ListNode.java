package com.dn.bean;

import lombok.Data;

/**
* @description: 
* @author dingning
* @date 2022/5/17 13:53
* @version 1.0
*/
@Data
public class ListNode<E> {

    public ListNode<E> next;
    public E value;
    public ListNode<E> pre;

    public ListNode(ListNode<E> pre, E value, ListNode<E> next){
        pre.next = next;
        next.pre = pre;
        this.value = value;
    }

    public ListNode(E value){
        this.value = value;
    }

}
