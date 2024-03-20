package com.dn.strategy.factory.fac;

import com.dn.strategy.factory.intf.*;

/**
 * @author dingning
 * @date 2024/3/20 下午 03:50
 **/
public class XmFactory implements AbstractFactory{
    @Override
    public KeyBoard createKeyBoard() {
        return new XmKeyBoard();
    }

    @Override
    public MainFrame createMainFrame() {
        return new XmMainFrame();
    }

    @Override
    public Monitor createMonitor() {
        return new XmMonitor();
    }
}
