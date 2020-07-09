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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
                onSave(v);
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

    void onSave(View view){
        //处理存储edittext的map
        //如判断客户名称是否填写且不为空格
        Set<String> keySet = ventilationDataMap.keySet();
        for(String str : keySet){
            Log.d(TAG, str + " : " + ventilationDataMap.get(str));
        }
//        if(map.get(0)!=null&&!map.get(0).trim().equals("")){
//            //遍历处理map存储的内容
//            for (int i=0; i<ventilationDataFields.size(); i++){
//                Log.d(TAG, ventilationDataFields.get(i)+" : "+map.get(i));
//
//            }
//
//        }else{
//            Toast.makeText(this,"客户名称必填且不可为空",Toast.LENGTH_SHORT).show();
//        }
    };
}
