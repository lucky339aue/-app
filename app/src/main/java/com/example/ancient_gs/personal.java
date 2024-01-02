package com.example.ancient_gs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class personal extends AppCompatActivity {
    TextView username;
    private Button zhuye,tuijian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people);

        tuijian=findViewById(R.id.tuijain);
        zhuye=findViewById(R.id.zhuye);
        username=findViewById(R.id.me_account);
        zhuye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(personal.this,jintai.class);
                startActivity(intent);
            }
        });
        tuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(personal.this, sc.class);
                startActivity(intent);
            }
        });
        Intent intent = getIntent();
        if (intent.hasExtra("USERNAME")) {
            String Username = intent.getStringExtra("USERNAME");
            saveUsername(Username);
            // 找到用于显示用户名的 TextView
            // 将用户名设置到 TextView 中
            username.setText(Username);
        }
        // 从SharedPreferences或ViewModel中获取用户名
        String savedUsername = getSavedUsername();
        username.setText(savedUsername);
//        // 获取从登录页面传递过来的数据
//        Intent intent = getIntent();
//        String name = intent.getStringExtra("USERNAME");
//
//        // 找到用于显示用户名的 TextView
//        TextView usernameTextView =
//
//        // 将用户名设置到 TextView 中
//        username.setText(name);
    }
    private String getSavedUsername() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return preferences.getString("USERNAME", "");
    }

    private void saveUsername(String username) {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("USERNAME", username);
        editor.apply();
    }

}
