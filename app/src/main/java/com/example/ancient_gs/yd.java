package com.example.ancient_gs;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class yd extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ViewPager viewPager = findViewById(R.id.viewPager);
        MyPagerAdapter adapter = new MyPagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Do nothing
            }

            @Override
            public void onPageSelected(int position) {
                updateIndicators(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Do nothing
            }
        });
    }

    private void updateIndicators(int position) {
        View indicator1 = findViewById(R.id.indicator1);
        View indicator2 = findViewById(R.id.indicator2);

        switch (position) {
            case 0:
                indicator1.setBackgroundResource(R.drawable.indicator_selected);
                indicator2.setBackgroundResource(R.drawable.indicator_unselected);
                break;
            case 1:
                indicator1.setBackgroundResource(R.drawable.indicator_unselected);
                indicator2.setBackgroundResource(R.drawable.indicator_selected);
                break;
        }
    }
}

