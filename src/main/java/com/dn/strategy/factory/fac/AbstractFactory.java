package com.dn.strategy.factory.fac;

import com.dn.strategy.factory.intf.KeyBoard;
import com.dn.strategy.factory.intf.MainFrame;
import com.dn.strategy.factory.intf.Monitor;

/**
 * @author dingning
 * @date 2024/3/20 下午 03:49
 **/
public interface AbstractFactory {

    KeyBoard createKeyBoard();

    MainFrame createMainFrame();

    Monitor createMonitor();
}
