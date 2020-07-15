package com.example.uicustomviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class FormSelectActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formselect_layout);

        super.hideDefaultTitle();

        Intent intent = getIntent();
        final String account = intent.getStringExtra("account");
        Toast.makeText(this, account+",欢迎您！", Toast.LENGTH_SHORT).show();

        //更改title的文本，以便与本活动对应
        TextView titleText = (TextView)findViewById(R.id.title_text);
        titleText.setText("请选择");

        Button formselectbutton1 = (Button) findViewById(R.id.formselectbutton1);
        formselectbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormSelectActivity.this, VentilationTableActivity.class);
                intent.putExtra("account", account);
                startActivity(intent);
            }
        });
    }
}
