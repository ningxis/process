package com.dn.bean;

import lombok.Data;

/**
* @description: 
* @author dingning
* @date 2022/5/17 13:53
* @version 1.0
*/
@Data
public class Node {

    public Node next;
    public String value;

    public Node(String value){
        this.value = value;
    }

}
