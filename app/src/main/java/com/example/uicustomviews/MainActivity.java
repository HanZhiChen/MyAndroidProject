package com.example.uicustomviews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.HttpUtil;
import com.example.utils.JsonParseUtil;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private EditText accountEdit;
    private EditText pwdEdit;
    private CheckBox rememberPass;
    private Button login;
    private Button register;

    private SharedPreferences pref;

    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        super.hideDefaultTitle();


        //初始化账号和密码的EditText控件
        accountEdit = (EditText)findViewById(R.id.nameEdit);
        pwdEdit = (EditText)findViewById(R.id.pwdEdit);

        rememberPass = (CheckBox)findViewById(R.id.remember_pass);

        login = (Button)findViewById(R.id.loginButton);

        register = (Button)findViewById(R.id.toRegisterActivityButton);

        pref = PreferenceManager.getDefaultSharedPreferences(this);


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
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });

        //登陆按钮点击事件，调用登陆方法。
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = pwdEdit.getText().toString();
                loginService(account, password);
            }
        });

        //尝试从启动此活动的意图中，获取账号密码并设置到EditText中。
        autofillEdit(null);





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    autofillEdit(data);
                }
        }
    }

    private void autofillEdit(Intent intent){
        String account = null;
        String pwd = null;
        if(null != intent){
            account = intent.getStringExtra("account");
            pwd = intent.getStringExtra("pwd");
        }


        if(account == null && pwd == null){
            /*
            如果从intent中获取的账号密码都为空，说明当前活动并不是从RegisterActivity启动。
            就将保存在SharedPreference中的账号密码信息读取出来并自动填充。
             */

            boolean isRemember = pref.getBoolean("remember_password", false);
            if(isRemember){
                //将账号和密码都设置到文本框内
                account = pref.getString("account", "");
                pwd = pref.getString("password", "");
                accountEdit.setText(account);
                pwdEdit.setText(pwd);
                rememberPass.setChecked(true);
            }

        }else{
            /*
            如果从intent中获取的账号密码不为空。
             */
            accountEdit.setText(account);
            pwdEdit.setText(pwd);
        }
    }

    private void loginService(final String account, final String password){
        //如果账号是admin且密码是123456，就认为登陆成功。
        Map<String, String> account_passwordMap = new HashMap<>();
        account_passwordMap.put(account, password);

        HttpUtil.sendOkHttpRequest("http://www.nthxqn.cn/kjqdxt/ajaction/v2/mysql/?type=mining&cmd=login",
                account_passwordMap, new okhttp3.Callback(){
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        try {
                            String responseData = response.body().string();
                            Log.d(TAG, responseData);
                            JSONArray jsonArray = JsonParseUtil.parseJSONWithJSONArray(responseData);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String responseAccount = JsonParseUtil.getJsonValue(jsonObject, "YHM");
                            if(responseAccount.equalsIgnoreCase(account)){
                                //登陆成功就将当前账号密码保存至SharedPreferences
                                editor = pref.edit();
                                if(rememberPass.isChecked()){//检查复选框是否被选中
                                    editor.putBoolean("remember_password", true);
                                    editor.putString("account", account);
                                    editor.putString("password", password);

                                }else{
                                    editor.clear();
                                }
                                editor.apply();

                                Intent intent = new Intent(MainActivity.this, FormSelectActivity.class);
                                intent.putExtra("account", responseAccount);
                                startActivity(intent);
                                finish();

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "登录失败，请重试", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }catch (Exception e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "登录失败，请重试", Toast.LENGTH_SHORT).show();
                                }
                            });
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "登录失败，请重试", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
        if(account.equals("admin") && password.equals("123456")){





        }else{
            Toast.makeText(MainActivity.this, "account or password is " +
                    "invalid", Toast.LENGTH_SHORT).show();

        }

    }
}