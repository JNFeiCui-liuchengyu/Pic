package com.feicui.pic;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by liuchengyu on 2016/6/22.
 */
public class HttpOnline {
    private StringBuffer sb;
    private static final String TAG = "HttpOnline";
    public String getData() {
        URL url = null;
        try {
            url = new URL("http://gank.io/api/random/data/福利/20");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            //读写操作
            InputStream    is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            //缓冲区读取
            sb = new StringBuffer();

            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                Log.d(TAG, "getData: "+sb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();

    }

}
