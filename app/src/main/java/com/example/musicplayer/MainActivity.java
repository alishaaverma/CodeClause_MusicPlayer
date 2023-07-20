package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView music,m;
private static int Splash_timeout=4500;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        music=findViewById(R.id.tv2);
        m=findViewById(R.id.tv1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,MusicPage.class);
                startActivity(intent);
                finish();
            }
        },Splash_timeout);
        Animation animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.animation1);
        music.startAnimation(animation);
        Animation animation2= AnimationUtils.loadAnimation(MainActivity.this,R.anim.animation2);
        m.startAnimation(animation2);
    }
}