package com.dn.strategy.factory.intf;

/**
 * @author dingning
 * @date 2024/3/20 下午 03:44
 **/
public class HuaweiKeyBoard implements KeyBoard{

    @Override
    public void print() {
        System.out.println("华为键盘输出");
    }

    @Override
    public void input() {
        System.out.println("华为键盘输入");
    }
}
