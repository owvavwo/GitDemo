package com.hui.gps_geocoding;

import android.util.Log;

import com.hui.gps_geocoding.HttpCallbackListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by HUI on 2016/1/19.
 */
public class HttpUtil {

    public static void sendHttpRequest (final String address, final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("HttpUtil","address is "+address);
                    URL url = new URL(address);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream is = connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while((line=br.readLine())!= null) {
                        sb.append(line);
                    }
                    Log.d("HttpUtil","response is "+ sb.toString());
                    if (listener != null) {
                        listener.onFinish(sb.toString());
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    if (listener != null) {
                        listener.onError(e);
                    }
                } catch (IOException e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
