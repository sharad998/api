package com.example.one_tap.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.one_tap.Data.User;
import com.example.one_tap.R;
import com.google.gson.Gson;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        Gson gson = new Gson();
        User userDetails= gson.fromJson(getIntent().getStringExtra("userDetails"),User.class);
        Toast.makeText(this, userDetails.getData().getFirstName().toUpperCase(),Toast.LENGTH_LONG).show();

    }
}