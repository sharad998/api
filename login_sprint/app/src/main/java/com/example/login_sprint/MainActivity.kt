package com.example.login_sprint

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)

    /*var email :EditText= findViewById<EditText>(R.id.email_text)
    var password: EditText = findViewById<EditText>(R.id.password_text)
    var btnLogin: Button = findViewById<Button>(R.id.btnLogin)
    var forgotLink : TextView = findViewById<TextView>(R.id.forgotPassword)
    var signupLink :TextView = findViewById<TextView>(R.id.gotoRegister)

    //var btnLogin = findViewById<Button>(R.id.btnLogin)   cannot write this way,possibility of null pointer

    even above thing throws exception of null pointer...cannot declare this way

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener(View.OnClickListener {
            fun onClick(v: View) {
                login()

            }
        })

        gotoRegister.setOnClickListener(View.OnClickListener {
            fun onClick(v: View) {
                var i = Intent(this, Sign_up::class.java)
                startActivityForResult(i, 0)
            }

        })

    }

    fun login() {
        Log.d("tag", "Login")

        if (!validate()) {
            onLoginFailed()
            return
        }

        btnLogin.isEnabled = false

        var temail = email_text.text.toString()
        var tpassword = password_text.text.toString()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == RESULT_OK) {
            this.finish()

        }
    }


    override fun onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true)
    }

    fun onLoginSuccess() {

        btnLogin.isEnabled = true
        finish()
    }

    fun onLoginFailed() {
        btnLogin.isEnabled = true
        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_LONG).show()
    }

    fun validate(): Boolean {
        var valid = true
        val temail = email_text.text.toString()
        val tpassword = password_text.text.toString()
        if (temail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(temail).matches()) {
            email_text.error = "enter a valid email address"
            valid = false
        } else {
            email_text.error = null
        }
        if (tpassword.isEmpty() || tpassword.length < 4 || tpassword.length > 10) {
            password_text.error = "between 4 and 10 alphanumeric characters"
            valid = false
        } else {
            password_text.error = null
        }
        return valid
    }


}
