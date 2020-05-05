package com.example.infoflgs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Act_SplashScren extends AppCompatActivity {

    boolean lockSplash=false;
    TextView txMoarefi,time;

    Typeface typefaceSlpash;
    int count=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act__splash_scren);
        txMoarefi=findViewById(R.id.Moarefi);
        time=findViewById(R.id.time);
        typefaceSlpash=Typeface.createFromAsset(getAssets(),"Bardiya-.ttf");
        txMoarefi.setTypeface(typefaceSlpash);

        SharedPreferences sharedPreferences=getSharedPreferences("splash", Context.MODE_PRIVATE);
        lockSplash=sharedPreferences.getBoolean("lock",false);


        if (lockSplash){
            startActivity(new Intent(Act_SplashScren.this, Act_passForm.class));
            finish();
        }

        else {

            Timer timer=new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            count--;
                            time.setText(count+"");

                            if (count==0){
                                lockSplash = true;
                                SharedPreferences sharedPreferences = getSharedPreferences("splash", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("lock", lockSplash);
                                startActivity(new Intent(Act_SplashScren.this, Act_passForm.class));
                                finish();
                                editor.apply();
                            }


                        }
                    });
                }
            },1,1500);



/*

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    lockSplash = true;
                    SharedPreferences sharedPreferences = getSharedPreferences("splash", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("lock", lockSplash);
                    startActivity(new Intent(Act_SplashScren.this, MainActivity.class));
                    finish();
                    editor.apply();
                }
            }, 3000);*/


        }
    }




}
