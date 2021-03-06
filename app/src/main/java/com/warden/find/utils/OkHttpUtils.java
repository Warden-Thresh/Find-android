package com.warden.find.utils;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by WangChenchen on 2016/8/19.
 */
public class OkHttpUtils {

    private static OkHttpUtils sInstance;
    private OkHttpClient mOkHttpClient;
    private OkHttpUtils() {
        mOkHttpClient = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpUtils getsInstance() {
        if (sInstance == null) {
            synchronized (OkHttpUtils.class) {
                sInstance = new OkHttpUtils();
            }
        }

        return sInstance;
    }

    public static String getResponse(String url) {
        return getsInstance().getResponseString(url);
    }

    private String getResponseString(String url) {
        String str = "";
        Request request = new Request.Builder().addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)").url(url).build();
        Call call = mOkHttpClient.newCall(request);
        try {
            Response res = call.execute();
            str = res.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    public static String sendOkHttpPost(String url, RequestBody formBody){


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(response.isSuccessful())
        {
            String str = null;
            try {
                str = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("服务器响应为: " + str);
            return str;

        }
        //client.newCall(request).enqueue(callback);
     return "";
    }
}
