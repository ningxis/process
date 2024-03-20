package com.dn.strategy.factory;

import com.dn.strategy.factory.fac.HuaweiFactory;
import com.dn.strategy.factory.fac.XmFactory;
import com.dn.strategy.factory.intf.KeyBoard;

/**
 * @author dingning
 * @date 2024/3/20 下午 03:51
 **/
public class Test {

    //抽象工厂模式
    public static void main(String[] args) {
        //小米显示器
        XmFactory xm = new XmFactory();
        xm.createMonitor().play();
        KeyBoard xmKeyBoard = xm.createKeyBoard();
        xmKeyBoard.print();
        xmKeyBoard.input();
        HuaweiFactory huaweiFactory = new HuaweiFactory();
        huaweiFactory.createMainFrame().run();
    }


}
