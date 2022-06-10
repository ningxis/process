package com.dn.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

/**
 * @author dingning
 * @version 1.0
 * @description:
 * @date 2022/6/10 9:39
 */
public class ZhihuService {

    private static int i;

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        test02("https://www.zhihu.com/api/v4/answers/1022398794/root_comments?limit=20&offset=0&order=normal&status=open");
    }

    // TODO: dinging 2022/6/10 后续考虑数据库存储


    private static void test02(String url) {
        boolean isEnd = false;
        while (!isEnd) {
            String result = test01(url, "utf-8");
            JSONObject jsonObject = JSON.parseObject(result);
            JSONArray jsonArray = JSONObject.parseArray(jsonObject.get("data").toString());
            if (jsonArray.size() == 0) {
                return;
            }
            url = objectParseJSONObject(jsonObject.get("paging")).get("next").toString();
            isEnd = Boolean.parseBoolean(objectParseJSONObject(jsonObject.get("paging")).get("next").toString());
            for (Object o : jsonArray) {
                i++;
                JSONObject jsonObject1 = JSON.parseObject(o.toString());
                String comment = jsonObject1.get("content").toString();
                String author = objectParseJSONObject(objectParseJSONObject(jsonObject1.get("author")).get("member")).get("name").toString();
                String time = dateFormatter.format(jsonObject1.get("created_time"));
                int vote_count = (int) jsonObject1.get("vote_count");
                System.out.println(i + "||" + author + "||" + comment + "||" + vote_count  + "||"  + time);
            }
        }
    }

    private static JSONObject objectParseJSONObject(Object o) {
        return JSON.parseObject(JSONObject.toJSONString(o));
    }

    private static String test01(String apiUrl, String decode) {
        String str = null;
        HttpURLConnection con = null;
        try {
            str = null;
            URL url = new URL(apiUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        con.setReadTimeout(5000);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        try {
            if (con.getResponseCode() == 200) {
                str = formatIsToString(con.getInputStream(), decode);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return str;
    }

    public static String formatIsToString(InputStream is, String decode) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = -1;
        try {
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            baos.flush();
            baos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toString(decode);
    }

}
