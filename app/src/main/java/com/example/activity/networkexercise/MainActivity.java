package com.example.activity.networkexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText content;
    private Button searchBtn;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

//        new Thread(networkTask).start();

        goSearch();


    }

    private void goSearch() {
        content = findViewById(R.id.ediText_name);
        searchBtn = findViewById(R.id.btn_search);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = content.getText().toString().trim();
                path = "http://music.163.com/api/search/get/web?csrf_token=hlpretag=&hlposttag=&s=" +name+
                        "&type=1&offset=0&total=true&limit=20";
                try {
                    URL url=new URL(path);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    int responseCode=conn.getResponseCode();
                    System.out.println("状态码："+responseCode);
                    if (responseCode==200){
                        InputStream is=conn.getInputStream();
                        String response = getStringFromInputStream(is);

                        System.out.println(response);
                    }                               
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("输入的内容: " + name);
            }
        });
    }

    private static String getStringFromInputStream(InputStream is)
            throws IOException, IOException {
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



//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Bundle data = msg.getData();
//            String val = data.getString("value");
//            Log.i("mylog", "请求结果为-->" + val);
//            // UI界面的更新等相关操作
//        }
//    };

    /**
     * 网络操作相关的子线程
     */
//    Runnable networkTask = new Runnable() {
//
//        @Override
//        public void run() {
//            goSearch();
//        }
//    };



}