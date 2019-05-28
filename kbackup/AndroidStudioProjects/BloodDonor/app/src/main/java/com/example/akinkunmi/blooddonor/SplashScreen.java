package com.example.akinkunmi.blooddonor;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


public class SplashScreen extends AppCompatActivity {


    private View mProgressView; //new input
    private View mLoginFormView; //new input

    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mProgressView = findViewById(R.id.login_progress); //new input

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),HomeScreen.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }


}
