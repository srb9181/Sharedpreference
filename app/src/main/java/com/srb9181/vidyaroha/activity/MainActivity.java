package com.srb9181.vidyaroha.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.srb9181.vidyaroha.R;
import com.srb9181.vidyaroha.adapters.ViewPagerAdapter;
import com.srb9181.vidyaroha.receiver.MyReceiver;
import com.srb9181.vidyaroha.utils.Constants;

public class MainActivity extends AppCompatActivity {



    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        tabLayout = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("Greetings"));
        tabLayout.addTab(tabLayout.newTab().setText("Take Photo"));

        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        IntentFilter intentFilter = new IntentFilter(
                Intent.ACTION_DATE_CHANGED);

        getApplicationContext().registerReceiver(new MyReceiver(),intentFilter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences  sharedPreferences = getSharedPreferences(Constants.APP, Context.MODE_PRIVATE);
        int count = sharedPreferences.getInt(Constants.APP_COUNT,0);

        count++;

        SharedPreferences.Editor preferences = getSharedPreferences(Constants.APP, Context.MODE_PRIVATE).edit();
        preferences.putInt(Constants.APP_COUNT,count);
        preferences.commit();
    }
}
