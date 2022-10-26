package com.dn.algorithm.leetcode.ten;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author dingning
 * @date 2022/10/26 下午 11:35
 **/
public class FourWeek {

    public static void main(String[] args) {
        testOkHttp3();
    }

    private static void testOkHttp3(){
        OkHttpClient client = new OkHttpClient();
        String url = "https://rms.utry-robot.com";
        Request request = new Request.Builder().url(url).build();
        for (int i = 0; i < 10; i++) {
            try {
                Response response = client.newCall(request).execute();
                if(response.isSuccessful()){
                    System.out.println(response.body().string());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
