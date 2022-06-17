package com.dn.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author skyline
 * @version 1.0
 * @description:
 * @date 2022/6/17 17:41
 */
public class LocalDateTimeService {

    public static void main(String[] args) {
        String time = "2022-06-17 17:44:35";

        String time1 = "2022-06-17";

        String time2 = "17:44:35";



        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");

        //当前时间 年月日
        LocalDate localDate = LocalDate.now();
        localDate = LocalDate.parse(time1,dtf1);
        System.out.println(dtf1.format(LocalDateTime.now()));
        System.out.println(localDate);

        //当前时间 时分秒
        LocalTime localTime = LocalTime.now();
        localTime = LocalTime.parse(time2,dtf2);
        System.out.println(dtf2.format(LocalDateTime.now()));
        System.out.println(localTime);

        //当前时间 年月日时分秒
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = LocalDateTime.parse(time,dtf);
        System.out.println(dtf.format(LocalDateTime.now()));
        System.out.println(localDateTime);
    }


}
