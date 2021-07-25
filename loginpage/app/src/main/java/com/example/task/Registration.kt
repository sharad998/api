package com.example.loginpage

import android.app.Activity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.task.DatePickerFragment
import kotlinx.android.synthetic.main.activity_registration.*
import java.text.DateFormat
import java.util.*


class Registration : AppCompatActivity(), DatePickerFragment.DatePickerListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        supportActionBar?.hide()


        signUpButton.setOnClickListener() {

            signup()

        }

        goToLogin.setOnClickListener() {

            finish()

        }


        r_dob_layout.setStartIconOnClickListener() {


            var datePickerFragment: DialogFragment=DatePickerFragment()

           datePickerFragment.isCancelable=false
            datePickerFragment.show(supportFragmentManager,"date picker")


        }

         r_rePassword_layout.setStartIconOnClickListener() {

                // r_rePassword_text.transformationMethod=PasswordAuthentication
                if (r_rePassword_layout.startIconDrawable!!.equals(R.drawable.ic_openlock)) {
                    r_rePassword_text.transformationMethod =PasswordTransformationMethod()
                    r_rePassword_layout.setStartIconDrawable(R.drawable.ic_lock)
                } else {
                    r_rePassword_text.transformationMethod = null
                    r_rePassword_layout.setStartIconDrawable(R.drawable.ic_openlock)

            }
        }

        r_password_layout.setStartIconOnClickListener() {

            // r_rePassword_text.transformationMethod=PasswordAuthentication
            if ( r_password_layout.startIconDrawable!!.equals(R.drawable.ic_openlock)) {
                r_password_text.transformationMethod =PasswordTransformationMethod()
                r_password_layout.setStartIconDrawable(R.drawable.ic_lock)
            } else {
                r_password_text.transformationMethod = null
                r_password_layout.setStartIconDrawable(R.drawable.ic_openlock)

            }
        }
    }


    private fun signup() {
        Log.d("SignupActivity", "Signup")

        // if (validateRequestPermissionsRequestCode(0) is false)

        if (!validate()) {
            onSignupFailed()
            return
        }

        signUpButton.isEnabled = false
        var temail = r_email_text.text.toString()
        var tpassword = r_password_text.text.toString()
    }


    //var signUpButton: Button? =findViewById<Button>(R.id.signUpButton)
    //signUpButton.isEnabled=false   cannot use this outside a function why?

    fun onsignupSuccess() {
        signUpButton.isEnabled = true
        setResult(Activity.RESULT_OK, null)
        finish()
    }

    fun onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show()

        signUpButton.isEnabled = true
    }

    fun validate(): Boolean {
        var valid = true
        val temail = r_email_text.text.toString()
        val tpassword = r_password_text.text.toString()
        val trepassword = r_rePassword_text.text.toString()
        val dob = r_dob_text.text.toString()
        val phone = r_phone_text.text.toString()
        if (temail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(temail).matches()) {
            r_email_text.error = "enter a valid email address"
            valid = false
        } else {
            r_email_text.error = null
        }
        if (tpassword.isEmpty() || tpassword.length < 4 || tpassword.length > 10) {
            r_password_text.error = "between 4 and 10 alphanumeric characters"
            valid = false
        } else {
            r_password_text.error = null
        }

        if (tpassword != trepassword) {
            r_rePassword_text.error = "password does not match"
            valid=false
        }

        if (phone.isEmpty() || phone.length < 10 || !Patterns.PHONE.matcher(phone).matches()) {
            r_phone_layout.error = "enter 10 digit mobile number"
            valid = false

        } else {
            r_phone_text.error = null
        }
        return valid
    }


    override fun onDateSet(datePicker: DatePicker?,year: Int,
                           month: Int,
                           day: Int
    ) {
        val cal = Calendar.getInstance()
        cal[Calendar.YEAR] = year
        cal[Calendar.MONTH] = month
        cal[Calendar.DAY_OF_MONTH] = day
        val date = DateFormat.getDateInstance().format(cal.time)
        r_dob_text!!.setText(date)
    }

}