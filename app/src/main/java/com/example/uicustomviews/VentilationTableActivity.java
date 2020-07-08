package com.example.uicustomviews;

import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapters.VentilationTableAdapter;
import com.example.pojo.VentilationData;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class VentilationTableActivity extends BaseActivity {
    private static final String TAG = "VentilationTableActivit";

    private List<String> ventilationDataFields = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventilation_table_layout);

        super.hideDefaultTitle();

        initVentilationDataFields();//初始化通风列表字段数据

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        VentilationTableAdapter adapter = new VentilationTableAdapter(ventilationDataFields);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.data_edit);


    }

    private  void initVentilationDataFields(){
        Class ventilationDataClass = VentilationData.class;
        Field[] fields = ventilationDataClass.getDeclaredFields();
        for(Field field : fields){
            ventilationDataFields.add(field.getName());
        }
//        Log.d(TAG, ventilationDataFields.toString());
    }


}
