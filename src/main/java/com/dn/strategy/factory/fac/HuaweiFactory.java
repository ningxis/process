package com.dn.strategy.factory.fac;

import com.dn.strategy.factory.intf.*;

/**
 * @author dingning
 * @date 2024/3/20 下午 03:51
 **/
public class HuaweiFactory implements AbstractFactory{
    @Override
    public KeyBoard createKeyBoard() {
        return new HuaweiKeyBoard();
    }

    @Override
    public MainFrame createMainFrame() {
        return new HuaweiMainFrame();
    }

    @Override
    public Monitor createMonitor() {
        return new HuaweiMonitor();
    }
}
