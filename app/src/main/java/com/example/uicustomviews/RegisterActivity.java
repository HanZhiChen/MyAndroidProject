package com.example.uicustomviews;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private String account;
    private String pwd;
    private EditText accountEdit;
    private EditText pwdEdit;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

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
        titleText.setText("注册");

        //将title控件中的title_empty按钮设置为重置输入账号密码功能。
        Button resetButton = (Button)findViewById(R.id.title_empty);
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                accountEdit.setText("");
                pwdEdit.setText("");
            }
        });

        //给注册按钮绑定注册事件。
        Button registeButton = (Button)findViewById(R.id.registeButton);
        registeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                account = accountEdit.getText().toString();
                pwd = pwdEdit.getText().toString();
//                Log.d(TAG, account);
//                Log.d(TAG, pwd);

                //执行注册方法需要时间，同时显示进度条。
//                ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
//                progressDialog.setTitle("Registing...");
//                progressDialog.setMessage("waiting...");
//                progressDialog.setCancelable(false);
//                progressDialog.show();
                boolean isSuccess = registing(account, pwd);

                if(isSuccess == true){

                    //注册成功，自动进入登陆活动，自动登陆。
                    Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("account", account);
                    intent.putExtra("pwd", pwd);
                    setResult(RESULT_OK, intent);
//                   progressDialog.dismiss();
//                    startActivity(intent);
                    finish();
                }else{
                    //注册失败。
                    Toast.makeText(RegisterActivity.this, "注册失败，请重试", Toast.LENGTH_SHORT).show();
                }


            }
        });

        }

    private boolean registing(String account, String pwd) {

        //等待3000毫秒，模拟注册行为。
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return true;
    };
}
