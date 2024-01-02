package com.example.ancient_gs;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class zc extends AppCompatActivity implements View.OnClickListener{
    private Button dl,qk;
    private EditText et_account,et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc);
        init();
    }

    private void init(){
        et_account = findViewById(R.id.account);
        et_password = findViewById(R.id.password);
        qk=findViewById(R.id.qk);
        dl=findViewById(R.id.dl);
        dl.setOnClickListener(this);
        qk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.dl){
            String account = et_account.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            if(TextUtils.isEmpty(account)){
                Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                return;
            } else if (TextUtils.isEmpty(password)){
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean isSaveSuccess = false;

            /* 使用SharedPreferences方式保存用户名及密码  或者  使用文件存储方式保存用户名及密码*/
            // 使用文件存储方式保存用户名及密码
            try {
                boolean success = FileSaveQQ.saveUserInfo(this, account, password);
                if (success) {
                    isSaveSuccess = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }//----

            if (isSaveSuccess) {
                Toast.makeText(this, "注册成功,请登录", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(zc.this,dl.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        }
        if (view.getId() == R.id.qk){
        et_account.setText("");
        et_password.setText("");
        }

    }
}