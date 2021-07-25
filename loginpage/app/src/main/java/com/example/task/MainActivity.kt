package com.example.loginpage

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior.setTag
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.VolleyLog.setTag
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.task.Myrequest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registration.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


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

        supportActionBar?.hide()

        btnLogin.setOnClickListener(){
                login()

            }


        gotoRegister.setOnClickListener() {
                var i = Intent(this, Registration::class.java)
                startActivityForResult(i, 0)
            }

        password_layout.setStartIconOnClickListener(){
                // r_rePassword_text.transformationMethod=PasswordAuthentication
                if (password_layout.startIconDrawable!!.equals(R.drawable.ic_openlock)) {
                    //password_text.transformationMethod =
                      //  PasswordTransformationMethod.getInstance()
                    password_text.setTransformationMethod(null)
                    password_layout.setStartIconDrawable(R.drawable.ic_lock)
                }
                else {
                    //password_text.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    password_layout.setStartIconDrawable(R.drawable.ic_openlock)
                    password_text.setTransformationMethod(PasswordTransformationMethod())
                }

        }


    }

    private fun login() {
        Log.d("tag", "Login")

        var temail = email_text.text.toString()
        var tpassword = password_text.text.toString()

        if (!validate() ) {
            onLoginFailed()

        }
        //else {cross(temail,tpassword)}

        btnLogin.isEnabled = false




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
       // if (temail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(temail).matches()) {
           // email_text.error = "enter a valid email address"
           // valid = false
       // } else {
        //    email_text.error = null
       // }
        if (tpassword.isEmpty() || tpassword.length < 4 || tpassword.length > 30) {
            password_layout.setEndIconActivated(false)

            password_text.error = "between 4 and 10 alphanumeric characters"
            valid = false
        } else {
            password_text.error = null
        }
        return valid
    }

    var myRequest : Myrequest =  Myrequest("partner", "", "", "string");

    /*
    fun cross(username: String, password:String){
        var queue: RequestQueue? =null
        queue=Volley.newRequestQueue(this@MainActivity)

        queue.cancelAll(Any())
        var c: StringRequest=crossRequest(username,password)
        c.setTag(Any())

    }

    fun crossRequest(username: String, password:String ): StringRequest {
        var final: String = ""
        var API = "&api key =6ffaf52bc1d74ee9b7d840dd17a9d588"
        var username_search = "&q="
        var password_search="&q="
        var url_prefix = "https://bfl-api-dev.azure-api.net/LDAPAuthSessionMngWS/AuthADID"
        var url = url_prefix+API+username_search+username+password_search+password



        return StringRequest(Request.Method.GET,url,
            Response.Listener<String> {
                fun onResponse(response:String) {
                    try {
                        var result: JSONObject = JSONObject(response).getJSONObject("list")
                        var maxItems = result.getInt("end")
                        var resultlist: JSONArray = result.getJSONArray("item")
                    } catch (e: JSONException){
                        Toast.makeText(this@MainActivity,e.message,Toast.LENGTH_LONG).show()
                    }
                }

        },
            Response.ErrorListener {
                fun onErrorResponse(error: VolleyError){
                    Toast.makeText(this@MainActivity,"loginpage is not responding", Toast.LENGTH_LONG).show()

                }
            })


    }
    */
    }




