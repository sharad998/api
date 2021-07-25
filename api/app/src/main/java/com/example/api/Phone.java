package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;

public class Phone extends AppCompatActivity
{

    EditText otp;
    int RESOLVE_HINT = 1000;
    private static final int CREDENTIAL_PICKER_REQUEST = 1;
    GoogleApiClient googleClientApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        otp=findViewById(R.id.OTP);
        requestHint();


        otp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                requestHint();
                return false;
            }


        });



    }

    private void requestHint() {
        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();

         googleClientApi= new GoogleApiClient.Builder(this).addApi(Auth.CREDENTIALS_API).build();
        PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(
                googleClientApi, hintRequest);
        try {
            startIntentSenderForResult(intent.getIntentSender(),
                    RESOLVE_HINT, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == RESULT_OK && data != null) {
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                String phoneNumber = credential.getId();
               otp = (EditText) findViewById(R.id.OTP);
                otp.setText(credential.getId());
                // credential.getId(); <-- E.164 format phone number on 10.2.+ devices
                Log.d("verify", "onActivityResult: ");

            }
        }

    }
}
