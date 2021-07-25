package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.media.MediaRouter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialsClient;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {


    int RESOLVE_HINT = 1000;

    CredentialsClient mCredentialsClient;
    GoogleApiClient googleClientApi = new GoogleApiClient.Builder(this).addApi(Auth.CREDENTIALS_API).build();
    TextView text =findViewById(R.id.text);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        /*


        public void requestPhoneNumber(SimpleCallback<String> callback){
            phoneNumberCallback = callback;
            HintRequest hintRequest = new HintRequest.Builder()
                    .setPhoneNumberIdentifierSupported(true)
                    .build();

            PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(googleApiClient, hintRequest);
            try {
                startIntentSenderForResult(intent.getIntentSender(), PHONE_NUMBER_RC, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                Log.e("TAG", "Could not start hint picker Intent", e);
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PHONE_NUMBER_RC) {
                if (resultCode == RESULT_OK) {
                    Credential cred = data.getParcelableExtra(Credential.EXTRA_KEY);
                    if (phoneNumberCallback != null){
                        phoneNumberCallback.onSuccess(cred.getId());
                    }
                }
                phoneNumberCallback = null;
            }
        }

*/

        /*
private void requestHint(){
    HintRequest hintRequest =new HintRequest.Builder().setPhoneNumberIdentifierSupported(true).build();

    PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(apiClient, hintRequest);
    startIntentSenderForResult(intent.getIntentSender(), RESOLVE_HINT,0,0,0);
        };*/

    }

    /*public void requestHint() {

        HintRequest hintRequest = new HintRequest.Builder()
                .setPhoneNumberIdentifierSupported(true)
                .build();

        PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(
                googleClientApi, hintRequest);
        try {
            startIntentSenderForResult(intent.getIntentSender(),
                    RESOLVE_HINT, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            Log.e("TAG","Could not start hint picker Intetn",e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESOLVE_HINT) {
            //Log.d("verify", "step1");
            if (resultCode == RESULT_OK) {
                //Log.d("verify", "step2");
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                //Log.d("verify", "step3");
               String phoneNumber = credential.getId();
                // credential.getId(); <-- E.164 format phone number on 10.2.+ devices
                //Log.d("verify", phoneNumber);




            }
            else {
                Log.d("verify", "no number");
            }
        }

    }

    public void smsListener(){
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        Task<Void> task =client.startSmsRetriever();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

        }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }



}
*/
}
