package com.dn.algorithm.leetcode;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author skyline
 * @version 1.0
 * @description: 2022年7月25日17:03:16  截止时间 2022年8月1日17:03:32
 * @date 2022/7/25 16:30
 */
public class ElevenWeek {

//智力题

    //文本转换，直接在最后一行追加
    private static void fileTransfer() {
        try {
            FileReader fileReader = new FileReader("D:/小远/pictures/company.txt");
            BufferedReader br = new BufferedReader(fileReader);
            String temp = "";
            Set<String> set = new HashSet<>();
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:/小远/pictures/companyTest.txt", true));
            //readLine()：表示读取一行文本。
            while ((temp = br.readLine()) != null) {
                if (set.add(temp)) {
                    bw.write(temp + "\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        fileTransfer();
    }
}
