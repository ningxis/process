package com.dn.algorithm.leetcode.ten;

import okhttp3.*;

import java.io.IOException;

/**
 * @author dingning
 * @date 2022/10/26 下午 11:35
 **/
public class FourWeek {

    public static void main(String[] args) {
        testOkHttp3();
    }

    //同步回调GET
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


    //异步回调GET
    private static void asyncOkHttp3(){
        OkHttpClient client = new OkHttpClient();
        String url = "https://rms.utry-robot.com";
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("调用失败回调");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("异步回调成功" + response.body().string());
            }
        });

    }
}
