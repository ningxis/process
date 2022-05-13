package com.dn.exception;
/**
* @description: 
* @author dingning
* @date 2022/5/13 12:34
* @version 1.0
 * 全局异常处理
*/
public class ExceptionUtils {


    public void getException(Throwable e){
        //进行相应业务异常的捕获和处理
        if(e instanceof RuntimeException){
            System.out.println("运行时异常");
        }

    }

}
