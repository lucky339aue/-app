package com.example.ancient_gs;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class sc extends AppCompatActivity {
    TextView tv,tv1,tv2,tv3;
    private Button zhuye,wode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shici);

        wode=findViewById(R.id.wode);
        zhuye=findViewById(R.id.zhuye);
        zhuye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sc.this,jintai.class);
                startActivity(intent);
            }
        });
        wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sc.this,personal.class);
                startActivity(intent);
            }
        });
        // 在活动或片段中获取 TextView 的引用
        tv = findViewById(R.id.vtv);
        tv1 = findViewById(R.id.vtv1);
        tv2 = findViewById(R.id.vtv2);
        tv3 = findViewById(R.id.vtv3);
        // 加载字体文件
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "gushi.ttf");

        // 将字体应用于 TextView
        tv.setTypeface(myTypeface);
        tv1.setTypeface(myTypeface);
        tv2.setTypeface(myTypeface);
        tv3.setTypeface(myTypeface);
        // 创建或打开数据库
        DatabaseHelper dbHelper = new DatabaseHelper(sc.this);

        // 查询随机0-500数据
        String[] result = dbHelper.queryData();
        if (result != null && result.length >= 3) {
            String title = result[0];
            String author = result[1];
            String contents = result[2];
            System.out.println(contents);
            // 在这里使用获取到的数据
//            tv.setText(title + "\n\n" + author + "\n\n" + contents);
            String[] sentences = contents.split("。");
            StringBuilder truncatedContent = new StringBuilder();
            StringBuilder truncatedContent2 = new StringBuilder();

            // 构建包含前两句的字符串
            for (int i = 0; i < Math.min(sentences.length, 2); i++) {
                if (i==0){
                    truncatedContent.append(sentences[i]).append("。");
                }else {
                    truncatedContent2.append(sentences[i]).append("。");
                }
            }
            // 在这里使用获取到的数据
//            tv.setText(title + "\n\n" + author + "\n\n" + contents);
            tv.setText(title);
            tv1.setText(author);
            tv2.setText(truncatedContent);
            tv3.setText(truncatedContent2);
        } else {
            Log.d("Database Query", "No data found or invalid result.");
        }
    }
}



