package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
     val RESOLVE_HINT = 1000;
    private static final int CREDENTIAL_PICKER_REQUEST = 1;
    GoogleApiClient googleClientApi = new GoogleApiClient.Builder(this).addApi(Auth.CREDENTIALS_API).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);


        private void requestHint () {
            HintRequest hintRequest = new HintRequest.Builder()
                    .setPhoneNumberIdentifierSupported(true)
                    .build();

            PendingIntent intent = Auth.CredentialsApi.getHintPickerIntent(
                    googleClientApi, hintRequest);
            try {
                startIntentSenderForResult(intent.getIntentSender(),
                        RESOLVE_HINT, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREDENTIAL_PICKER_REQUEST) {
            if (resultCode == RESULT_OK && data != null) {
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                String phoneNumber = credential.getId();
                // credential.getId(); <-- E.164 format phone number on 10.2.+ devices
                Log.d("verify", "onActivityResult: ");

            }
        }
}