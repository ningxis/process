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

    //todo:dingning 2022/5/5 22:54  属性必须包装类

    private String userName;

    private Integer age;

    private Integer userId;

//todo:dingning 2022/5/5 22:55  可以用object
    //Map<String, Map<String, String>> 改成Map<String, Map<String, Object>>保证程序的健壮性，可拓展
    private Map<String, Map<String, String>> items;


}
