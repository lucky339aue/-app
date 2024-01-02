package com.example.ancient_gs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
public class dl extends AppCompatActivity implements View.OnClickListener {
    private EditText et_account, et_password;
    private Button dl, zc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dl);
        init();
    }

    private void init() {
        et_account = findViewById(R.id.account);
        et_password = findViewById(R.id.password);
        zc = findViewById(R.id.zc);
        dl = findViewById(R.id.dl);
        dl.setOnClickListener(this);
        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dl.this, zc.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        String loginAccount = et_account.getText().toString().trim();
        String loginPassword = et_password.getText().toString().trim();

        if (isValidLogin(loginAccount, loginPassword)) {
            Toast.makeText(dl.this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(dl.this, sc.class);
            intent.putExtra("USERNAME", loginAccount);
            startActivity(intent);
        } else {
            Toast.makeText(dl.this, "登录失败，账号或密码不正确", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidLogin(String username, String password) {
        try {
            FileInputStream fis = openFileInput("data.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String savedUsername = parts[0];
                    String savedPassword = parts[1];
                    if (username.equals(savedUsername) && password.equals(savedPassword)) {
                        return true; // 登录验证通过
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // 登录验证失败
    }
}
