package com.example.one_tap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.one_tap.API.APIClient;
import com.example.one_tap.API.APIInterface;
import com.example.one_tap.Data.User;
import com.example.one_tap.databinding.ActivityVerificationBinding;
import com.example.one_tap.presentation.DetailsActivity;
import com.example.one_tap.presentation.supportingClasses.OTPTextWatcher;
import com.example.one_tap.presentation.viewmodel.PhoneAndOTPViewModel;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Verification extends AppCompatActivity {

    public ActivityVerificationBinding homeBinding;
    public PhoneAndOTPViewModel masterModel;
    public String OTPText;
    APIInterface apiInterface;
    public String number;
    public int track=0;


    private static final String TAG0 = "PhoneAuthActivity";
    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";
    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_CODE_SENT = 2;
    private static final int STATE_VERIFY_FAILED = 3;
    private static final int STATE_VERIFY_SUCCESS = 4;
    private static final int STATE_SIGNIN_FAILED = 5;
    private static final int STATE_SIGNIN_SUCCESS = 6;


    // [START declare_auth]

    private FirebaseAuth mAuth;

    // [END declare_auth]



    private boolean mVerificationInProgress = false;
    private String mVerificationId;

    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    int RESOLVE_HINT = 1000;
    private static final int CREDENTIAL_PICKER_REQUEST = 1;
    GoogleApiClient googleClientApi;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Restore instance state

        if (savedInstanceState != null) {

            onRestoreInstanceState(savedInstanceState);

        }



        apiInterface= APIClient.getClient().create(APIInterface.class);




        homeBinding= DataBindingUtil.setContentView(this,R.layout.activity_verification);
        masterModel= new ViewModelProvider(this).get(PhoneAndOTPViewModel.class);

        EditText[] editTexts= {homeBinding.includeOtpCard.editTextOne,
                homeBinding.includeOtpCard.editTextTwo,
                homeBinding.includeOtpCard.editTextThree,
                homeBinding.includeOtpCard.editTextFour,
        homeBinding.includeOtpCard.editTextFive,
        homeBinding.includeOtpCard.editTextSix};

        homeBinding.includeOtpCard.editTextOne.addTextChangedListener(new OTPTextWatcher(editTexts[0], editTexts));
        homeBinding.includeOtpCard.editTextTwo.addTextChangedListener(new OTPTextWatcher(editTexts[1], editTexts));
        homeBinding.includeOtpCard.editTextThree.addTextChangedListener(new OTPTextWatcher(editTexts[2], editTexts));
        homeBinding.includeOtpCard.editTextFour.addTextChangedListener(new OTPTextWatcher(editTexts[3], editTexts));
        homeBinding.includeOtpCard.editTextFive.addTextChangedListener(new OTPTextWatcher(editTexts[4],editTexts));
        homeBinding.includeOtpCard.editTextSix.addTextChangedListener(new OTPTextWatcher(editTexts[5],editTexts));

        OTPText= otpText();

        masterModel.setPhoneNumber(Objects.requireNonNull(homeBinding.includeNumberCard.mobile.getText()).toString());
        masterModel.setOTP(OTPText);



        homeBinding.includeNumberCard.getOtpClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validatePhoneNumber()) {

                    return;

                }

                startPhoneNumberVerification(homeBinding.includeNumberCard.mobile.getText().toString());

//                homeBinding.includeNumberCard.mobile.getText().toString().length()>9)
//                    homeBinding.card1.setVisibility(View.GONE);
//                    homeBinding.card2.setVisibility(View.VISIBLE);
//                    homeBinding.card3.setVisibility(View.GONE);
//
//                    track=1;
//
//                    //APICall apiCall= new APICall(apiInterface);
//                    //User user = apiCall.verify(homeBinding.includeNumberCard.mobile.getText().toString());
//                   //Toast.makeText(getApplicationContext() , user.getData().getFirstName().toUpperCase(),Toast.LENGTH_SHORT).show();
//



            }
        });

        homeBinding.includeOtpCard.submitOTPClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if((homeBinding.includeOtpCard.editTextOne.getText().toString().length()>0)
//                        & (homeBinding.includeOtpCard.editTextTwo.getText().toString().length()>0)
//                        & (homeBinding.includeOtpCard.editTextThree.getText().toString().length()>0)
//                        &(homeBinding.includeOtpCard.editTextFour.getText().toString().length()>0)
//                )
//
                if (TextUtils.isEmpty(otpText())) {

                    //mBinding.fieldVerificationCode.setError("Cannot be empty.");
                    homeBinding.includeOtpCard.submitOTPClick.setError("Cannot be empty.");

                    return;

                }



                track=2;

                verifyPhoneNumberWithCode(mVerificationId, otpText());

                verifyAccount(homeBinding.includeNumberCard.mobile.getText().toString());
                Toast.makeText(getApplicationContext(),homeBinding.includeNumberCard.mobile.getText().toString(),Toast.LENGTH_LONG).show();


            }
        });


        homeBinding.includeOtpCard.resendOTPClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resendVerificationCode(homeBinding.includeNumberCard.mobile.getText().toString(), mResendToken);

            }
        });



        // [START initialize_auth]

        // Initialize Firebase Auth

        mAuth = FirebaseAuth.getInstance();

        // [END initialize_auth]



        // Initialize phone auth callbacks

        // [START phone_auth_callbacks]

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

                Log.d(TAG0, "onVerificationCompleted:" + credential);

                // [START_EXCLUDE silent]

                mVerificationInProgress = false;

                // [END_EXCLUDE]



                // [START_EXCLUDE silent]

                // Update the UI and attempt sign in with the phone credential

                updateUI(STATE_VERIFY_SUCCESS, credential);
                verifyAccount(homeBinding.includeNumberCard.mobile.getText().toString());

                // [END_EXCLUDE]

               // signInWithPhoneAuthCredential(credential);

            }




            @Override

            public void onVerificationFailed(FirebaseException e) {

                Log.w(TAG0, "onVerificationFailed", e);

                // [START_EXCLUDE silent]

                mVerificationInProgress = false;

                // [END_EXCLUDE]



                if (e instanceof FirebaseAuthInvalidCredentialsException) {

                    // Invalid request

                    // [START_EXCLUDE]
                    homeBinding.includeNumberCard.mobile.setError("Invalid phone number.");

                    //mBinding.fieldPhoneNumber.setError("Invalid phone number.");

                    // [END_EXCLUDE]

                } else if (e instanceof FirebaseTooManyRequestsException) {

                    // The SMS quota for the project has been exceeded

                    // [START_EXCLUDE]

                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",

                            Snackbar.LENGTH_SHORT).show();

                    // [END_EXCLUDE]

                }



                // Show a message and update the UI

                // [START_EXCLUDE]

                updateUI(STATE_VERIFY_FAILED);

                // [END_EXCLUDE]

            }



            @Override

            public void onCodeSent(@NonNull String verificationId,

                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {


                Log.d(TAG0, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later

                mVerificationId = verificationId;

                mResendToken = token;



                // [START_EXCLUDE]

                // Update UI

                updateUI(STATE_CODE_SENT);

                // [END_EXCLUDE]

            }

        };

        // [END phone_auth_callbacks]








    }


    @Override

    public void onStart() {

        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.

        FirebaseUser currentUser = mAuth.getCurrentUser();

        updateUI(currentUser);
        requestHint();



        // [START_EXCLUDE]

        if (mVerificationInProgress && validatePhoneNumber()) {

            //fieldPhoneNumber=findViewById(R.id.fieldPhoneNumber);

            startPhoneNumberVerification(homeBinding.includeNumberCard.mobile.getText().toString());

        }

        // [END_EXCLUDE]

    }

    // [END on_start_check_user]


    @Override
    protected void onPostResume() {
        super.onPostResume();

    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    @Override
    public void onBackPressed() {
        switch(track){
            case 1: {
                homeBinding.card1.setVisibility(View.VISIBLE);
                homeBinding.card2.setVisibility(View.GONE);
                homeBinding.card3.setVisibility(View.GONE);
                track=0;
                break;
            }

            case 2:{
                homeBinding.card1.setVisibility(View.GONE);
                homeBinding.card2.setVisibility(View.VISIBLE);
                homeBinding.card3.setVisibility(View.GONE);
                track=1;
                break;
            }

            default:Toast.makeText(this, "value is zero",Toast.LENGTH_LONG).show();

        }
    }


    //--------------------------------------------------Verify in database---------------------------------------

    public void verifyAccount(String number){


        Call<User> call= apiInterface.VerifyNumber(number);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userDetail =response.body();

                Intent intent= new Intent(getApplicationContext(), DetailsActivity.class);
                Gson gson = new Gson();
                String myJson = gson.toJson(userDetail);
                intent.putExtra("userDetails",myJson);

                if(userDetail.getMessage()==null){
                    startActivity(intent);
                }


                else{
                    homeBinding.card1.setVisibility(View.GONE);
                    homeBinding.card2.setVisibility(View.GONE);
                    homeBinding.card3.setVisibility(View.VISIBLE);
                   Toast.makeText(getApplicationContext(), "new number",Toast.LENGTH_LONG).show();
                }






                //Log.e("Madhu",userDetails.getSuccess().toString());


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("more","failed");





            }
        });


    }




  //      ---------------------------Verify in database END----------------------------------------




    @Override

    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress);

    }



    @Override

    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        mVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS);

    }





    private void startPhoneNumberVerification(String phoneNumber) {

        // [START start_phone_auth]

        PhoneAuthOptions options =

                PhoneAuthOptions.newBuilder(mAuth)

                        .setPhoneNumber(phoneNumber)       // Phone number to verify

                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit

                        .setActivity(this)                 // Activity (for callback binding)

                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks

                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

        // [END start_phone_auth]



        mVerificationInProgress = true;

    }



    private void verifyPhoneNumberWithCode(String verificationId, String code) {

        // [START verify_with_code]

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // [END verify_with_code]

      signInWithPhoneAuthCredential(credential);
    }



    // [START resend_verification]

    private void resendVerificationCode(String phoneNumber,

                                        PhoneAuthProvider.ForceResendingToken token) {

        PhoneAuthOptions options =

                PhoneAuthOptions.newBuilder(mAuth)

                        .setPhoneNumber(phoneNumber)       // Phone number to verify

                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit

                        .setActivity(this)                 // Activity (for callback binding)

                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks

                        .setForceResendingToken(token)     // ForceResendingToken from callbacks

                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    // [END resend_verification]

    // [START sign_in_with_phone]

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information

                            Log.d(TAG0, "signInWithCredential:success");



                            FirebaseUser user = task.getResult().getUser();

                            // [START_EXCLUDE]

                            updateUI(STATE_SIGNIN_SUCCESS, user);

                            // [END_EXCLUDE]

                        } else {

                            // Sign in failed, display a message and update the UI

                            Log.w(TAG0, "signInWithCredential:failure", task.getException());

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                                // The verification code entered was invalid

                                // [START_EXCLUDE silent]

                                homeBinding.includeOtpCard.submitOTPClick.setError("Invalid code.");
                                //mBinding.fieldVerificationCode.setError("Invalid code.");

                                // [END_EXCLUDE]

                            }

                            // [START_EXCLUDE silent]

                            // Update UI

                            updateUI(STATE_SIGNIN_FAILED);

                            // [END_EXCLUDE]

                        }

                    }

                });

    }

    // [END sign_in_with_phone]


    private void signOut() {

        mAuth.signOut();

        updateUI(STATE_INITIALIZED);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == RESULT_OK && data != null) {
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                String phoneNumber = credential.getId();
                homeBinding.includeNumberCard.mobile.setText(phoneNumber);
                // credential.getId(); <-- E.164 format phone number on 10.2.+ devices
                Log.d("verify", "onActivityResult: ");

            }
        }


    }





    private void updateUI(int uiState) {

        updateUI(uiState, mAuth.getCurrentUser(), null);

    }



    private void updateUI(FirebaseUser user) {

        if (user != null) {

            updateUI(STATE_SIGNIN_SUCCESS, user);

        } else {

            updateUI(STATE_INITIALIZED);

        }

    }



    private void updateUI(int uiState, FirebaseUser user) {

        updateUI(uiState, user, null);

    }



    private void updateUI(int uiState, PhoneAuthCredential cred) {

        updateUI(uiState, null, cred);

    }



    private void updateUI(int uiState, FirebaseUser user, PhoneAuthCredential cred) {


        switch (uiState) {

            case STATE_INITIALIZED:

                // Initialized state, show only the phone number field and start button

                enableViews(homeBinding.card1);

                disableViews(homeBinding.card2,homeBinding.card3);


                break;

            case STATE_CODE_SENT:

                // Code sent state, show the verification field, the


                enableViews(homeBinding.card2);

                disableViews(homeBinding.card1,homeBinding.card3,homeBinding.includeOtpCard.resendOTPClick);

               // detail.setText(R.string.status_code_sent);

                break;

            case STATE_VERIFY_FAILED:

                // Verification has failed, show all options

                enableViews(homeBinding.card2,homeBinding.includeOtpCard.resendOTPClick);
                disableViews(homeBinding.card1,homeBinding.card2);

                track=1;

               // detail.setText(R.string.status_verification_failed);

                break;

            case STATE_VERIFY_SUCCESS:

                // Verification has succeeded, proceed to firebase sign in

//                disableViews(buttonStartVerification,buttonVerifyPhone,buttonResend, fieldPhoneNumber,
//
//                        fieldVerificationCode);




                // Set the verification text based on the credential

//                if (cred != null) {
//
//                    if (cred.getSmsCode() != null) {
//
//                        fieldVerificationCode.setText(cred.getSmsCode());
//
//                    } else {
//
//                        fieldVerificationCode.setText(R.string.instant_validation);
//
//                    }

//                }



                break;

            case STATE_SIGNIN_FAILED:

                // No-op, handled by sign-in check

               // detail.setText(R.string.status_sign_in_failed);

                break;

            case STATE_SIGNIN_SUCCESS:

                // Np-op, handled by sign-in check

                break;

        }



        if (user == null) {

            // Signed out

//            phoneAuthFields.setVisibility(View.VISIBLE);
//
//            signedInButtons.setVisibility(View.GONE);
//
//
//
//            status.setText(R.string.signed_out);

        } else {

            // Signed in

//            phoneAuthFields.setVisibility(View.GONE);
//
//            signedInButtons.setVisibility(View.VISIBLE);
//
//            details_screen.setVisibility(View.VISIBLE);
//            top_ui.setVisibility(View.GONE);







//            enableViews(fieldPhoneNumber, fieldVerificationCode);
//
//            fieldPhoneNumber.setText(null);
//
//            fieldVerificationCode.setText(null);
//
//
//
//            status.setText(R.string.signed_in);
//
//            detail.setText(getString(R.string.firebase_status_fmt, user.getUid()));


        }

    }


    private boolean validatePhoneNumber() {

        String phoneNumber = homeBinding.includeNumberCard.mobile.getText().toString();
        // String phoneNumber = mBinding.fieldPhoneNumber.getText().toString();

        if (TextUtils.isEmpty(phoneNumber)) {

            homeBinding.includeNumberCard.mobile.setError("Invalid phone number.");

            return false;

        }



        return true;

    }



    private void enableViews(View... views) {

        for (View v : views) {

            v.setEnabled(true);
            v.setVisibility(View.VISIBLE);

        }

    }



    private void disableViews(View... views) {

        for (View v : views) {

            v.setVisibility(View.GONE);

        }

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

    public String otpText(){
        String text= homeBinding.includeOtpCard.editTextOne.getText().toString()+
                homeBinding.includeOtpCard.editTextTwo.getText().toString()+
                homeBinding.includeOtpCard.editTextThree.getText().toString()+
                homeBinding.includeOtpCard.editTextFour.getText().toString()+
                homeBinding.includeOtpCard.editTextFive.getText().toString()+
                homeBinding.includeOtpCard.editTextSix.getText().toString();

        return text;
    }



}