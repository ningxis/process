package com.dn.strategy.factory;

import com.dn.strategy.factory.fac.HuaweiFactory;
import com.dn.strategy.factory.fac.XmFactory;
import com.dn.strategy.factory.intf.KeyBoard;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author dingning
 * @date 2024/3/20 下午 03:51
 **/
public class Test {

    @Override
    public String toString() {
//        System.out.println("3413431241");
        return "456789";
    }

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
        LocalDate of = LocalDate.of(2015, 4, 5);
        System.out.println(of.format(DateTimeFormatter.ofPattern("MMMdd,yyyy")));
        System.out.println(of.format(DateTimeFormatter.ofPattern("E,MMM dd,yyyy")));
        System.out.println(of.format(DateTimeFormatter.ofPattern("MM/dd/yy")));

    }



}
