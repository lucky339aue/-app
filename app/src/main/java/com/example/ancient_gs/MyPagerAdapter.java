package com.example.ancient_gs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

public class MyPagerAdapter extends PagerAdapter {

    private Context context;
    private int[] images = {R.drawable.img_5, R.drawable.img_6};


    public MyPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);

        if (position == 1) {
            Button button = view.findViewById(R.id.zc);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 跳转
                    Intent intent = new Intent(context,zc.class);
                    context.startActivity(intent);
                }
            });
            Button button2 = view.findViewById(R.id.dl);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 跳转
                    Intent intent = new Intent(context,dl.class);
                    context.startActivity(intent);
                }
            });
        } else {
            // If not on the second page, hide the button
            Button button = view.findViewById(R.id.zc);
            button.setVisibility(View.GONE);
            Button button2 = view.findViewById(R.id.dl);
            button2.setVisibility(View.GONE);
        }

        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
