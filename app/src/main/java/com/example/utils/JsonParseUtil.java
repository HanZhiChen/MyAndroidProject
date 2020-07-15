package com.example.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParseUtil {

    public static JSONArray parseJSONWithJSONArray(String jsonData) {
        JSONArray jsonArray = null;
        try{
            jsonArray = new JSONArray(jsonData);

        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static JSONObject parseJSONWithJSONObject(String jsonData) {
        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject(jsonData);



        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String getJsonValue(JSONObject jsonObject, String key){
        String values = null;
        if(null == jsonObject){
            throw new RuntimeException("jsonObject can not be null");
        }else{
            try {
                values  = jsonObject.getString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return values;
    }

}
