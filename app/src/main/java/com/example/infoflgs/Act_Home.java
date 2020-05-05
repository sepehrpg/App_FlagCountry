package com.example.infoflgs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class Act_Home extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__home);

        try {

            pager=findViewById(R.id.ViewPagerAct);
            tabLayout=findViewById(R.id.TabAct);
            pager.setAdapter(new AdapterViewPager(getSupportFragmentManager()));

            tabLayout.setupWithViewPager(pager);
            tabLayout.getTabAt(0).setText("صفحه 1");
            tabLayout.getTabAt(1).setText("صفحه 2");
            tabLayout.getTabAt(2).setText("برگزیده ها ");
            tabLayout.getTabAt(0).select();

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
