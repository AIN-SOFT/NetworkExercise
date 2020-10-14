package com.example.activity.networkexercise;

import android.os.StrictMode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpUtils {

     private String urlPath;

    public HttpUtils(String urlPath) {
        this.urlPath = urlPath;
    }

//    public  policy(){
//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
//    }

    public String url(String urlPath ) throws IOException {

        URL url=new URL(urlPath);
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        int responseCode=conn.getResponseCode();
        System.out.println("状态码："+responseCode);
        if (responseCode==200){
            InputStream is=conn.getInputStream();
            String response = getStringFromInputStream(is);
            return response;
        }else {
            return "在工具类中有请求错误";
        }

    }



    public static String getStringFromInputStream(InputStream is)
            throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // 模板代码 必须熟练
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        String state = os.toString();// 把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
        os.close();
        return state;
    }
}
