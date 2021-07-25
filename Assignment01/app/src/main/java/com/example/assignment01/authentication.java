package com.example.assignment01;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.example.assignment01.phone;

import java.util.concurrent.TimeUnit;

public class authentication extends AppCompatActivity {
    String phoneNumber;
    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private static final String TAG = "PhoneAuthActivity";

    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private Activity phone;

    public authentication (Activity phone,String phoneNumber){
        this.phoneNumber=phoneNumber;
        this.phone=phone;
    };

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);

                // [START_EXCLUDE silent]

                mVerificationInProgress = false;

                // [END_EXCLUDE]



                // [START_EXCLUDE silent]

                // Update the UI and attempt sign in with the phone credential

                //updateUI(STATE_VERIFY_SUCCESS, credential);

                // [END_EXCLUDE]

                //signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);

                // [START_EXCLUDE silent]

                mVerificationInProgress = false;

                // [END_EXCLUDE]


                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                    // Invalid request

                    // [START_EXCLUDE]
                    TextInputLayout number = findViewById(R.id.layout_number);

                    number.setError("Invalid phone number.");

                    // [END_EXCLUDE]

                } else if (e instanceof FirebaseTooManyRequestsException) {

                    // The SMS quota for the project has been exceeded

                    // [START_EXCLUDE]

                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",

                            Snackbar.LENGTH_SHORT).show();

                    // [END_EXCLUDE]

                }
            }

                @Override

                public void onCodeSent(@NonNull String verificationId,

                        @NonNull PhoneAuthProvider.ForceResendingToken token) {

                    // The SMS verification code has been sent to the provided phone number, we

                    // now need to ask the user to enter the code and then construct a credential

                    // by combining the code with a verification ID.

                    Log.d(TAG, "onCodeSent:" + verificationId);



                    // Save verification ID and resending token so we can use them later

                    mVerificationId = verificationId;

                    mResendToken = token;



                    // [START_EXCLUDE]

                    // Update UI

                   // updateUI(STATE_CODE_SENT);

                    // [END_EXCLUDE]

                }



        };
        PhoneAuthOptions options= PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber).setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(phone).setCallbacks(mCallbacks).build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

}



