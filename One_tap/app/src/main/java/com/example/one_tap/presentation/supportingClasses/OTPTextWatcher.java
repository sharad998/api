
package com.example.one_tap.presentation.supportingClasses;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.one_tap.R;


public final class OTPTextWatcher implements TextWatcher {
    EditText[] editTextArray;
    EditText editText;
    View view;




    public OTPTextWatcher(@NonNull View view, EditText[] editTextArray) {
        this.editTextArray = editTextArray;
        this.view=view;
    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }



    @Override
    public void afterTextChanged(Editable s) {
        int ln=s.toString().length();
        switch (view.getId()) {
            case R.id.editTextOne:
            switch (s.length()) {
                case 1:
                    editTextArray[1].requestFocus();
                    break;
            }
            break;

            case R.id.editTextTwo:
                switch (s.length()) {
                    case 1:
                        editTextArray[2].requestFocus();
                        break;
                    case 0:
                        editTextArray[0].requestFocus();
                        break;
                }
                break;

            case R.id.editTextThree:
                switch(s.length()){
                    case 1:
                        editTextArray[3].requestFocus();
                        break;
                    case 0:
                        editTextArray[1].requestFocus();
                        break;
                }
                break;

            case R.id.editTextFour:
                switch(s.length()){
                    case 1:
                        editTextArray[4].requestFocus();
                        break;
                    case 0:
                        editTextArray[2].requestFocus();
                        break;
                }
                break;

            case R.id.editTextFive:
                switch(s.length()){
                    case 1:
                        editTextArray[5].requestFocus();
                        break;
                    case 0:
                        editTextArray[3].requestFocus();
                        break;
                }
                break;
            case R.id.editTextSix:
                switch ((s.length())){
                    case 0:
                        editTextArray[4].requestFocus();
                        break;
                }
                break;



        }
    }
}