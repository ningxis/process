package com.dn.bean;

import lombok.Data;

import java.util.Map;

/**
 * @author dingning
 * @date 2022/5/5 17:23
 * 上下文
 **/
@Data
public class ProcessApplicationContext {

    private String userName;

    private int age;

    private int userId;


    private Map<String, Map<String, String>> items;


}
