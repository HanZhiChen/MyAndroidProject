package com.example.uicustomviews;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapters.VentilationTableAdapter;
import com.example.pojo.VentilationData;
import com.example.utils.HttpUtil;
import com.example.utils.JsonParseUtil;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Response;

public class VentilationTableActivity extends BaseActivity implements VentilationTableAdapter.SaveEditListener{
    private static final String TAG = "VentilationTableActivit";

//    private List<String> ventilationDataFields = new ArrayList<>();

    private Map<String, String> ventilationDataMap = new HashMap<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventilation_table_layout);

        super.hideDefaultTitle();

        //更改title的文本，以便与本活动对应
        TextView titleText = (TextView)findViewById(R.id.title_text);
        titleText.setText("井下通风数据采集页");
        titleText.setTextSize(20);

        initVentilationDataFields();//初始化通风列表字段数据

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        Set<String> set = ventilationDataMap.keySet();

        VentilationTableAdapter adapter = new VentilationTableAdapter(set.toArray(), VentilationTableActivity.this);
        recyclerView.setAdapter(adapter);


        Button commitButton = (Button) findViewById(R.id.title_empty);
        commitButton.setText("提交数据");
        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSave();
            }
        });




    }

    private  void initVentilationDataFields(){
        Class ventilationDataClass = VentilationData.class;
        Field[] fields = ventilationDataClass.getDeclaredFields();
        for(Field field : fields){
            ventilationDataMap.put(field.getName(), null);
        }
//        Log.d(TAG, ventilationDataFields.toString());
    }


    @Override
    public void saveEdit(String fieldName, String data) {
        ventilationDataMap.put(fieldName, data);
    }

    private void onSave(){
        //处理存储edittext的map

        Set<String> keySet = ventilationDataMap.keySet();
//        VentilationData ventilationData = new VentilationData();
//
        for(String keyStr : keySet){
            Log.d(TAG, keyStr + " : " + ventilationDataMap.get(keyStr));
            //这里还要做表单填写数据验证合法性，只不过暂时先没做。

//            StringBuilder setMethodName = new StringBuilder("set");
//            setMethodName.append(keyStr.substring(0,1).toUpperCase()).append(keyStr.substring(1).toLowerCase());
////            Log.d(TAG, getMethodName.toString());
//            try {
//                Method m = VentilationData.class.getDeclaredMethod(setMethodName.toString(), String.class);
//                m.invoke(ventilationData, ventilationDataMap.get(keyStr));
//
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
        }
//        Log.d(TAG, "onSave: runHttpUtil...");
        HttpUtil.sendOkHttpRequest("http://www.nthxqn.cn/kjqdxt/ajaction/v2/mysql/?type=data&cmd=save",
                ventilationDataMap, new okhttp3.Callback(){


                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Log.d(TAG, "onSave: runHttpUtil...");
                        String responseData = response.body().string();
                        JSONObject jsonObject = JsonParseUtil.parseJSONWithJSONObject(responseData);
                        String status = JsonParseUtil.getJsonValue(jsonObject, "status");
                        if(status.equalsIgnoreCase("ok")){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(VentilationTableActivity.this, "数据上传成功", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(VentilationTableActivity.this, "数据上传未成功，请重试", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(VentilationTableActivity.this, "数据上传未成功，请重试", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }


}
