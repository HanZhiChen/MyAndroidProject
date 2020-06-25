package com.example.uicustomviews;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText accountEdit;
    private EditText pwdEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化账号和密码的EditText控件
        accountEdit = (EditText)findViewById(R.id.nameEdit);
        pwdEdit = (EditText)findViewById(R.id.pwdEdit);

        //隐藏原来默认的title
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        //更改title的文本，以便与本活动对应
        TextView titleText = (TextView)findViewById(R.id.title_text);
        titleText.setText("登陆");

        //将title控件中的title_empty按钮设置为重置输入账号密码功能。
        Button resetButton = (Button)findViewById(R.id.title_empty);
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                accountEdit.setText("");
                pwdEdit.setText("");
            }
        });




        //给注册按钮绑定注册活动
        Button registerButton = (Button)findViewById(R.id.toRegisterActivityButton);
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });



        //尝试从启动此活动的意图中，获取账号密码并设置到EditText中。
        autofillEdit();
    }

    private void autofillEdit(){
        Intent intent = getIntent();
        String account = intent.getStringExtra("account");
        String pwd = intent.getStringExtra("pwd");
        if(account != null && pwd != null){
            accountEdit.setText(account);
            pwdEdit.setText(pwd);

        }
    }
}