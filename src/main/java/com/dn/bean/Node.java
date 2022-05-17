package com.dn.bean;

import lombok.Data;

/**
* @description: 
* @author dingning
* @date 2022/5/17 13:53
* @version 1.0
*/
@Data
public class Node<E> {

    public Node<E> next;
    public E value;
    public Node<E> pre;

    public Node(Node<E> pre,E value,Node<E> next){
        pre.next = next;
        next.pre = pre;
        this.value = value;
    }

    public Node(E value){
        this.value = value;
    }

}
