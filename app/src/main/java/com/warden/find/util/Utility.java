package com.warden.find.util;

import android.util.Log;


import com.google.gson.Gson;
import com.warden.find.gson.GankFuLi;

import java.util.List;

/**
 * Created by Warden on 2017/4/18.
 */

public class Utility {
    public static GankFuLi handleMeiZiResponse(String response) {
        try {
            Gson gson = new Gson();
            GankFuLi gankFuLi = gson.fromJson(response, GankFuLi.class);
            List<GankFuLi.MeiZi> meiZiList = gankFuLi.getMeizis();

            Log.d("MSGjson",meiZiList.toString());
            for (GankFuLi.MeiZi meiZi:meiZiList
                    ) {
                Log.d("MSGjson",meiZi.getUrl());
            }
            return gankFuLi;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
