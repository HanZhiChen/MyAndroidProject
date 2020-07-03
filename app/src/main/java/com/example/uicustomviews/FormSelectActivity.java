package com.example.uicustomviews;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FormSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formselect_layout);
        //隐藏原来默认的title
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        //更改title的文本，以便与本活动对应
        TextView titleText = (TextView)findViewById(R.id.title_text);
        titleText.setText("请选择");
    }
}
