package com.dn.strategy.factory.intf;

/**
 * @author dingning
 * @date 2024/3/20 下午 03:43
 **/
public class XmKeyBoard implements KeyBoard{

    @Override
    public void print() {
        System.out.println("小米键盘打印");
    }

    @Override
    public void input() {
        System.out.println("小米键盘输入");
    }
}
