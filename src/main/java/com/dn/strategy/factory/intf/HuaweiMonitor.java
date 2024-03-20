package com.dn.strategy.factory.intf;

/**
 * @author dingning
 * @date 2024/3/20 下午 03:46
 **/
public class HuaweiMonitor implements Monitor{
    @Override
    public void play() {
        System.out.println("华为显示器");
    }
}
