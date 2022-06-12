package com.dn.rpc.dubbo;

/**
 * @author dingning
 * @date 2022/6/12 13:48
 **/
public class MessageServiceImpl implements MessageService{
    @Override
    public void sayHello() {
        System.out.println("this is hello,welcome to my messageService !");
    }
}
