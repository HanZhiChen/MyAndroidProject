package com.example.utils;

import android.util.Log;

import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class HttpUtil {
    private static final String TAG = "HttpUtil";
    public static void sendOkHttpRequest(String address, Map<String, String> dataMap, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();

        Request request = null;

        if(null != dataMap){

            FormBody.Builder requestBody = new FormBody.Builder();

            Set<Map.Entry<String, String>> entrySet = dataMap.entrySet();
            for (Map.Entry<String, String> element : entrySet){
                String key = element.getKey();
                String value = element.getValue();

                //不把Map中，value为null或""的键值对添加到responseBody中去。
                if(!(null == value || value.equals(""))){
                    requestBody.add(key,value);
                }

            }

            request = new Request.Builder().url(address).post((RequestBody) requestBody.build()).build();
        }else{
            request = new Request.Builder().url(address).build();
        }



        client.newCall(request).enqueue(callback);
        Log.d(TAG, "sendOkHttpRequest: ");

    }
}
