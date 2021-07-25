package com.example.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    final private int SPLASH_SCREEN_TIME_OUT=5000;
    ProgressDialog pe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
       //// pe=new ProgressDialog(this);
       // pe.setMessage("..setting up app for you..");
       // pe.setCancelable(false);
      //  pe.show();



        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, PhoneAuth.class);
                startActivity(i);
                finish();

            }

            },SPLASH_SCREEN_TIME_OUT
            );
    }
}