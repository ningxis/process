package com.dn.exception;

import lombok.Data;

/**
 * @author dingning
 * @date 2022/5/5 17:29
 * 服务响应结果
 **/
@Data
public class ServiceResult<T> {

    public String msg;
    public int code;
    public T data;

    public ServiceResult(String msg,int code ,T data){
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public ServiceResult(String msg,int code){
        this.msg = msg;
        this.code = code;
    }


    public static ServiceResult toSuccessResult(){
        return new ServiceResult<>(ServiceExceptionEnum.SUCCESS.getMsg(),ServiceExceptionEnum.SUCCESS.getCode()) ;
    }

}
