package com.example.androidjsonbody;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by bhavin on 1/20/2019.
 */

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler() {
    }

    public String makeServiceCall(String requestUrl){

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(requestUrl);
            connection = (HttpURLConnection)url.openConnection();
            connection.connect();

            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer  buffer = new StringBuffer();

            String line = "";
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }
            String finalJsonObj = buffer.toString();

            return finalJsonObj;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                connection.disconnect();
            }


            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String convertSteamToString(InputStream stream) {
        String sb=null;
        return sb;

    }
}
