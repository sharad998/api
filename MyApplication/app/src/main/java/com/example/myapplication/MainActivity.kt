package com.example.myapplication

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var email  = findViewById<EditText>(R.id.inputEmail) as TextInputEditText
        var password :EditText=findViewById<EditText>(R.id.inputPassword)
        var til: TextInputLayout=findViewById<TextInputLayout>(R.id.lay)

        var temail= email.text.toString()
        var tpassword= password.text.toString()

        if ('@' !in temail){
            til.helperText="enter valid email id"

        }


    }

    //fun onclick(view: View) {}
    //fun i1(view: View) {}
    //fun i2(view: View) {}
}