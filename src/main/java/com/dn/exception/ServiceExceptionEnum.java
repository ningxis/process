package com.dn.exception;
/*
    服务异常枚举
 */



public enum ServiceExceptionEnum {

    SUCCESS(200,"成功"),
    INIT(110,"初始化失败"),
    VALIDATOR(111,"校验失败,用户姓名不符合要求");

    private int code;
    private String msg;


    ServiceExceptionEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.msg;
    }

}
