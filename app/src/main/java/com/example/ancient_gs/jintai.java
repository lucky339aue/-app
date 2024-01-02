package com.example.ancient_gs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class jintai extends AppCompatActivity {
    private Button wode, tuijian;
    TextView tv0, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12, tv13, tv14, tv15, tv16, tv17;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jintai);

        tuijian = findViewById(R.id.tuijain);
        wode = findViewById(R.id.wode);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jintai.this, personal.class);
                startActivity(intent);
            }
        });

        tuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(jintai.this, sc.class);
                startActivity(intent);
            }
        });

        // 初始化 TextView
        tv0 = findViewById(R.id.textViewTitle1);
        tv1 = findViewById(R.id.textViewAuthor1);
        tv2 = findViewById(R.id.textViewContent1);
        tv3 = findViewById(R.id.textViewTitle2);
        tv4 = findViewById(R.id.textViewAuthor2);
        tv5 = findViewById(R.id.textViewContent2);
        tv6 = findViewById(R.id.textViewTitle3);
        tv7 = findViewById(R.id.textViewAuthor3);
        tv8 = findViewById(R.id.textViewContent3);
        tv9 = findViewById(R.id.textViewTitle4);
        tv10 = findViewById(R.id.textViewAuthor4);
        tv11 = findViewById(R.id.textViewContent4);
        tv12 = findViewById(R.id.textViewTitle5);
        tv13 = findViewById(R.id.textViewAuthor5);
        tv14 = findViewById(R.id.textViewContent5);
        tv15 = findViewById(R.id.textViewTitle6);
        tv16 = findViewById(R.id.textViewAuthor6);
        tv17 = findViewById(R.id.textViewContent6);

        // 创建或打开数据库
//        DatabaseHelper dbHelper = new DatabaseHelper(jintai.this);

        // 查询随机0-500数据
//        List<String[]> poemsList = dbHelper.queryMultiplePoems(6);
//        List<String> formattedPoems = new ArrayList<>();
//        for (String[] result : poemsList) {
//            formattedPoems.add(result[0]); // title
//            formattedPoems.add(result[1]); // author
//            formattedPoems.add(result[2]); // content
//        }
//
//        // 设置 TextView 文本，添加空值检查
//        setTextViewText(tv0, formattedPoems, 0);
//        setTextViewText(tv1, formattedPoems, 1);
//        setTextViewText(tv2, formattedPoems, 2);
//        setTextViewText(tv3, formattedPoems, 3);
//        setTextViewText(tv4, formattedPoems, 4);
//        setTextViewText(tv5, formattedPoems, 5);
//        setTextViewText(tv6, formattedPoems, 6);
//        setTextViewText(tv7, formattedPoems, 7);
//        setTextViewText(tv8, formattedPoems, 8);
//        setTextViewText(tv9, formattedPoems, 9);
//        setTextViewText(tv10, formattedPoems, 10);
//        setTextViewText(tv11, formattedPoems, 11);
//        setTextViewText(tv12, formattedPoems, 12);
//        setTextViewText(tv13, formattedPoems, 13);
//        setTextViewText(tv14, formattedPoems, 14);
//        setTextViewText(tv15, formattedPoems, 15);
//        setTextViewText(tv16, formattedPoems, 16);
//        setTextViewText(tv17, formattedPoems, 17);

        // 设置下拉刷新监听器
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 在这里执行刷新数据的操作
                refreshData();
            }
        });
    }

    private void setTextViewText(TextView textView, List<String> data, int index) {
        if (textView != null && data.size() > index) {
            textView.setText(data.get(index));
        }
    }

    private void refreshData() {
        // 在这里执行刷新数据的具体操作，例如重新从数据库加载数据
        // ...
        DatabaseHelper dbHelper = new DatabaseHelper(jintai.this);
        List<String[]> poemsList = dbHelper.queryMultiplePoems(6);
        List<String> formattedPoems = new ArrayList<>();
        for (String[] result : poemsList) {
            formattedPoems.add(result[0]); // title
            formattedPoems.add(result[1]); // author
            formattedPoems.add(result[2]); // content
        }

        // 设置 TextView 文本，添加空值检查
        setTextViewText(tv0, formattedPoems, 0);
        setTextViewText(tv1, formattedPoems, 1);
        setTextViewText(tv2, formattedPoems, 2);
        setTextViewText(tv3, formattedPoems, 3);
        setTextViewText(tv4, formattedPoems, 4);
        setTextViewText(tv5, formattedPoems, 5);
        setTextViewText(tv6, formattedPoems, 6);
        setTextViewText(tv7, formattedPoems, 7);
        setTextViewText(tv8, formattedPoems, 8);
        setTextViewText(tv9, formattedPoems, 9);
        setTextViewText(tv10, formattedPoems, 10);
        setTextViewText(tv11, formattedPoems, 11);
        setTextViewText(tv12, formattedPoems, 12);
        setTextViewText(tv13, formattedPoems, 13);
        setTextViewText(tv14, formattedPoems, 14);
        setTextViewText(tv15, formattedPoems, 15);
        setTextViewText(tv16, formattedPoems, 16);
        setTextViewText(tv17, formattedPoems, 17);
        // 刷新完成后停止刷新动画
        swipeRefreshLayout.setRefreshing(false);
    }
}
