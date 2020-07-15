package com.example.uicustomviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.utils.HttpUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class TestHttpUtilAcitvity extends AppCompatActivity {
    private static final String TAG = "TestHttpUtilAcitvity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_http_util_acitvity);
        final Map<String, String> testMap = new HashMap<>();
        testMap.put("address","测试234");
        Button button = (Button)findViewById(R.id.send_request);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpUtil.sendOkHttpRequest("http://www.nthxqn.cn/kjqdxt/ajaction/v2/mysql/?type=data&cmd=save",
                        testMap, new okhttp3.Callback(){

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                String responseData = response.body().string();
                                try {
                                    JSONObject jsonObject = new JSONObject(responseData);
                                    String status = null;

                                    status = jsonObject.getString("status");
                                    Log.d(TAG, "status is "+status);
                                    if(status.equalsIgnoreCase("ok")){
                                        Log.d(TAG, "work in line 49");
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(TestHttpUtilAcitvity.this, "数据上传成功", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    }else {
                                        Toast.makeText(TestHttpUtilAcitvity.this, "数据上传未成功，请重试", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                            }
                        });
            }
        });
    }
}