package com.example.assignment01;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class PhoneAuth extends AppCompatActivity implements

            View.OnClickListener {



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



       // private PhoneAuthBinding mBinding;

        private Button buttonStartVerification,buttonVerifyPhone,buttonResend,signOutButton;
        LinearLayout signedInButtons, phoneAuthFields, top_ui;
        LinearLayout details_screen;
        private EditText fieldPhoneNumber, fieldVerificationCode;
        private TextView titleText, detail,status;

        ///

    ////  ISD VARIABLES START......................................................................
    //................................................................................
    private static final String TAG= ISD.class.getSimpleName();
    private static final int AUTOCOMPLETE_REQUEST_CODE=22;
    public String extractedUserId;
    public int editableUserPosition;

    TextInputEditText address,mobile,name;
    TextView listItemName,listItemMobile,listItemAddress ;
    Button saveDetail;
    public ListView lv;
    AutoCompleteTextView autoCompleteGender;
    public FloatingActionButton logButton;
    public LinearLayout list,layout_detail;

    String[] value= new String[]{"Male", "Female","Cannot Disclose"};

    public FirebaseDatabase database;
    public DatabaseReference myRef;

    ArrayList<HashMap<String,String>> contactList;
    public String totalCount;


   ////ISD Variables END ..............
    ////


    Button OTP_request;
    int RESOLVE_HINT = 1000;
    private static final int CREDENTIAL_PICKER_REQUEST = 1;
    GoogleApiClient googleClientApi;




        @Override

        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);



            setContentView(R.layout.activity_phone_auth);



            // Restore instance state

            if (savedInstanceState != null) {

                onRestoreInstanceState(savedInstanceState);

            }



            // Assign click listeners

            buttonStartVerification= findViewById(R.id.buttonStartVerification);
            buttonVerifyPhone=findViewById(R.id.buttonVerifyPhone);
            buttonResend=findViewById(R.id.buttonResend);
            signOutButton=findViewById(R.id.signOutButton);
            fieldPhoneNumber= findViewById(R.id.fieldPhoneNumber);



            buttonStartVerification.setOnClickListener(this);

            buttonVerifyPhone.setOnClickListener(this);

            buttonResend.setOnClickListener(this);

            signOutButton.setOnClickListener(this);

            fieldPhoneNumber.setOnClickListener(this);



            // [START initialize_auth]

            // Initialize Firebase Auth

            mAuth = FirebaseAuth.getInstance();

            // [END initialize_auth]



            // Initialize phone auth callbacks

            // [START phone_auth_callbacks]

            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {



                @Override

                public void onVerificationCompleted(PhoneAuthCredential credential) {

                    // This callback will be invoked in two situations:

                    // 1 - Instant verification. In some cases the phone number can be instantly

                    //     verified without needing to send or enter a verification code.

                    // 2 - Auto-retrieval. On some devices Google Play services can automatically

                    //     detect the incoming verification SMS and perform verification without

                    //     user action.

                    Log.d(TAG0, "onVerificationCompleted:" + credential);

                    // [START_EXCLUDE silent]

                    mVerificationInProgress = false;

                    // [END_EXCLUDE]



                    // [START_EXCLUDE silent]

                    // Update the UI and attempt sign in with the phone credential

                    updateUI(STATE_VERIFY_SUCCESS, credential);

                    // [END_EXCLUDE]

                    signInWithPhoneAuthCredential(credential);

                }




                @Override

                public void onVerificationFailed(FirebaseException e) {

                    // This callback is invoked in an invalid request for verification is made,

                    // for instance if the the phone number format is not valid.

                    Log.w(TAG0, "onVerificationFailed", e);

                    // [START_EXCLUDE silent]

                    mVerificationInProgress = false;

                    // [END_EXCLUDE]



                    if (e instanceof FirebaseAuthInvalidCredentialsException) {

                        // Invalid request

                        // [START_EXCLUDE]
                        fieldPhoneNumber=findViewById(R.id.fieldPhoneNumber);
                        fieldPhoneNumber.setError("Invalid phone number.");

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

                    // The SMS verification code has been sent to the provided phone number, we

                    // now need to ask the user to enter the code and then construct a credential

                    // by combining the code with a verification ID.

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


            ///START ISD CODE..................
            //.............................
            contactList = new ArrayList<>();
            database=FirebaseDatabase.getInstance();
            myRef=database.getReference();




            logButton= findViewById(R.id.log);
            logButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    final Dialog dialog= new Dialog(PhoneAuth.this);
                    dialog.setContentView(R.layout.count);
                    final TextView textview= dialog.findViewById(R.id.text);
                    final TextView f_textview=dialog.findViewById(R.id.f_text);

                    database=FirebaseDatabase.getInstance();
                    myRef=database.getReference();
                    //Query query= myRef.getParent();
                    //assert query != null;
                    Query q=myRef.orderByChild("gender").equalTo("GenderFemale");
                    q.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            long f_count=snapshot.getChildrenCount();
                            totalCount=Long.toString(f_count);
                            f_textview.setText("total logs by female="+totalCount);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    myRef.addValueEventListener(new ValueEventListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            long count = snapshot.getChildrenCount();
                            totalCount = Long.toString(count);
                            textview.setText("Total logs="+totalCount);

                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    ImageButton dialogButton=dialog.findViewById(R.id.imButton);
                    dialogButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Dismissed..!!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();

                }
            });



            ////location

            TextInputLayout search= findViewById(R.id.layout_address);

            String apiKey=getString(R.string.api_key);

            if(!Places.isInitialized()){
                Places.initialize(getApplicationContext(),apiKey);
            }
            PlacesClient placesClient=Places.createClient(this);


            search.setStartIconOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSearchCalled();
                }
            });


            /////
            ///////dropdown menu

            autoCompleteGender=findViewById(R.id.l_gender);
            ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,value);
            //ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,value);
            autoCompleteGender.setThreshold(1);
            autoCompleteGender.setAdapter(adapter);
            autoCompleteGender.setScrollBarSize(20);
            autoCompleteGender.setDropDownAnchor(R.id.l_gender);

            ///////
            /////////database storage


            saveDetail=findViewById(R.id.saveDetails);
            saveDetail.setOnClickListener(new View.OnClickListener(){

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    name=findViewById(R.id.l_name);
                    address=findViewById(R.id.l_address);
                    mobile=findViewById(R.id.l_mobile);

                    final String Name=name.getText().toString();
                    final String Address=address.getText().toString();
                    final String Contact=mobile.getText().toString();
                    final String Gender=autoCompleteGender.getText().toString();

                    if(saveDetail.getText().toString()=="Update"){
                        store(Name,Contact,Address,Gender,extractedUserId);

                    }
                    else {
                        store(Name, Contact, Address, Gender);
                    }
                }
            });

            lv=findViewById(R.id.list);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    editableUserPosition=position;

                    String nameText= contactList.get(position).get("name");
                    String mobileText=contactList.get(position).get("mobile");
                    String addressText=contactList.get(position).get("address");
                    String genderText=contactList.get(position).get("gender");
                    extractedUserId=contactList.get(position).get("UserId");
                    name.setText(nameText);
                    mobile.setText(mobileText);
                    address.setText(addressText);
                    autoCompleteGender.setText(genderText);
                    saveDetail.setText("Update");
                    lv.setVisibility(View.GONE);
                    layout_detail.setVisibility(View.VISIBLE);





                }
            });

            ///////////



            ///////////
            /////ISD CODE END............................................................................................
            //..............................................................


        }



        // [START on_start_check_user]

        @Override

        public void onStart() {

            super.onStart();

            // Check if user is signed in (non-null) and update UI accordingly.

            FirebaseUser currentUser = mAuth.getCurrentUser();

            updateUI(currentUser);
            requestHint();



            // [START_EXCLUDE]

            if (mVerificationInProgress && validatePhoneNumber()) {

                fieldPhoneNumber=findViewById(R.id.fieldPhoneNumber);

                startPhoneNumberVerification(fieldPhoneNumber.getText().toString());

            }

            // [END_EXCLUDE]

        }

        // [END on_start_check_user]



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

                                    fieldVerificationCode=findViewById(R.id.fieldVerificationCode);
                                    fieldVerificationCode.setError("Invalid code.");
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

            buttonStartVerification= findViewById(R.id.buttonStartVerification);
            buttonVerifyPhone=findViewById(R.id.buttonVerifyPhone);
            buttonResend=findViewById(R.id.buttonResend);
            signOutButton=findViewById(R.id.signOutButton);

            signedInButtons=findViewById(R.id.signedInButtons);
            phoneAuthFields=findViewById(R.id.phoneAuthFields);

            fieldPhoneNumber= findViewById(R.id.fieldPhoneNumber);
            fieldVerificationCode=findViewById(R.id.fieldVerificationCode);

            details_screen=findViewById(R.id.details_screen);
            top_ui=findViewById(R.id.top_screen);

            titleText=findViewById(R.id.titleText);
            detail=findViewById(R.id.detail);
            status=findViewById(R.id.status);

            switch (uiState) {

                case STATE_INITIALIZED:

                    // Initialized state, show only the phone number field and start button

                    enableViews(buttonStartVerification,fieldPhoneNumber);

                    disableViews(buttonVerifyPhone, buttonResend, fieldVerificationCode);

                    detail.setText(null);

                    break;

                case STATE_CODE_SENT:

                    // Code sent state, show the verification field, the

                    enableViews(buttonVerifyPhone, buttonResend, fieldPhoneNumber, fieldVerificationCode);

                    disableViews(buttonStartVerification);

                    detail.setText(R.string.status_code_sent);

                    break;

                case STATE_VERIFY_FAILED:

                    // Verification has failed, show all options

                    enableViews(buttonStartVerification, buttonVerifyPhone, buttonResend, fieldPhoneNumber,

                            fieldVerificationCode);

                    detail.setText(R.string.status_verification_failed);

                    break;

                case STATE_VERIFY_SUCCESS:

                    // Verification has succeeded, proceed to firebase sign in

                    disableViews(buttonStartVerification,buttonVerifyPhone,buttonResend, fieldPhoneNumber,

                           fieldVerificationCode);

                    detail.setText(R.string.status_verification_succeeded);



                    // Set the verification text based on the credential

                    if (cred != null) {

                        if (cred.getSmsCode() != null) {

                            fieldVerificationCode.setText(cred.getSmsCode());

                        } else {

                            fieldVerificationCode.setText(R.string.instant_validation);

                        }

                    }



                    break;

                case STATE_SIGNIN_FAILED:

                    // No-op, handled by sign-in check

                    detail.setText(R.string.status_sign_in_failed);

                    break;

                case STATE_SIGNIN_SUCCESS:

                    // Np-op, handled by sign-in check

                    break;

            }



            if (user == null) {

                // Signed out

               phoneAuthFields.setVisibility(View.VISIBLE);

                signedInButtons.setVisibility(View.GONE);



                status.setText(R.string.signed_out);

            } else {

                // Signed in

                phoneAuthFields.setVisibility(View.GONE);

                signedInButtons.setVisibility(View.VISIBLE);

                details_screen.setVisibility(View.VISIBLE);
                top_ui.setVisibility(View.GONE);







                enableViews(fieldPhoneNumber, fieldVerificationCode);

                fieldPhoneNumber.setText(null);

                fieldVerificationCode.setText(null);



               status.setText(R.string.signed_in);

               detail.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            }

        }

/*
 private void updateUI(int uiState, FirebaseUser user, PhoneAuthCredential cred) {

            buttonStartVerification= findViewById(R.id.buttonStartVerification);
            buttonVerifyPhone=findViewById(R.id.buttonVerifyPhone);
            buttonResend=findViewById(R.id.buttonResend);
            signOutButton=findViewById(R.id.signOutButton);
            signedInButton=findViewById(R.id.signedInButtons);
            fieldPhoneNumber= findViewById(R.id.fieldPhoneNumber);
            fieldVerificationCode=findViewById(R.id.fieldVerificationCode);
            titleText=findViewById(R.id.titleText);
            detail=findViewById(R.id.detail);
            status=findViewById(R.id.status);

            switch (uiState) {

                case STATE_INITIALIZED:

                    // Initialized state, show only the phone number field and start button

                    enableViews(mBinding.buttonStartVerification, mBinding.fieldPhoneNumber);

                    disableViews(mBinding.buttonVerifyPhone, mBinding.buttonResend, mBinding.fieldVerificationCode);

                    mBinding.detail.setText(null);

                    break;

                case STATE_CODE_SENT:

                    // Code sent state, show the verification field, the

                    enableViews(mBinding.buttonVerifyPhone, mBinding.buttonResend, mBinding.fieldPhoneNumber, mBinding.fieldVerificationCode);

                    disableViews(mBinding.buttonStartVerification);

                    mBinding.detail.setText(R.string.status_code_sent);

                    break;

                case STATE_VERIFY_FAILED:

                    // Verification has failed, show all options

                    enableViews(mBinding.buttonStartVerification, mBinding.buttonVerifyPhone, mBinding.buttonResend, mBinding.fieldPhoneNumber,

                            mBinding.fieldVerificationCode);

                    mBinding.detail.setText(R.string.status_verification_failed);

                    break;

                case STATE_VERIFY_SUCCESS:

                    // Verification has succeeded, proceed to firebase sign in

                    disableViews(mBinding.buttonStartVerification, mBinding.buttonVerifyPhone, mBinding.buttonResend, mBinding.fieldPhoneNumber,

                            mBinding.fieldVerificationCode);

                    mBinding.detail.setText(R.string.status_verification_succeeded);



                    // Set the verification text based on the credential

                    if (cred != null) {

                        if (cred.getSmsCode() != null) {

                            mBinding.fieldVerificationCode.setText(cred.getSmsCode());

                        } else {

                            mBinding.fieldVerificationCode.setText(R.string.instant_validation);

                        }

                    }



                    break;

                case STATE_SIGNIN_FAILED:

                    // No-op, handled by sign-in check

                    mBinding.detail.setText(R.string.status_sign_in_failed);

                    break;

                case STATE_SIGNIN_SUCCESS:

                    // Np-op, handled by sign-in check

                    break;

            }



            if (user == null) {

                // Signed out

                mBinding.phoneAuthFields.setVisibility(View.VISIBLE);

                mBinding.signedInButtons.setVisibility(View.GONE);



                mBinding.status.setText(R.string.signed_out);

            } else {

                // Signed in

                mBinding.phoneAuthFields.setVisibility(View.GONE);

                mBinding.signedInButtons.setVisibility(View.VISIBLE);



                enableViews(mBinding.fieldPhoneNumber, mBinding.fieldVerificationCode);

                mBinding.fieldPhoneNumber.setText(null);

                mBinding.fieldVerificationCode.setText(null);



                mBinding.status.setText(R.string.signed_in);

                mBinding.detail.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            }

        }

 */

        private boolean validatePhoneNumber() {

            fieldPhoneNumber=findViewById(R.id.fieldPhoneNumber);
            String phoneNumber = fieldPhoneNumber.getText().toString();
           // String phoneNumber = mBinding.fieldPhoneNumber.getText().toString();

            if (TextUtils.isEmpty(phoneNumber)) {

                fieldPhoneNumber.setError("Invalid phone number.");

                return false;

            }



            return true;

        }



        private void enableViews(View... views) {

            for (View v : views) {

                v.setEnabled(true);

            }

        }



        private void disableViews(View... views) {

            for (View v : views) {

                v.setEnabled(false);

            }

        }




        @Override

        public void onClick(View view) {
            fieldPhoneNumber=findViewById(R.id.fieldPhoneNumber);
            fieldVerificationCode=findViewById(R.id.fieldVerificationCode);

            switch (view.getId()) {

               // case R.id.fieldPhoneNumber:
                   // requestHint();


                case R.id.buttonStartVerification:

                    if (!validatePhoneNumber()) {

                        return;

                    }



                    startPhoneNumberVerification(fieldPhoneNumber.getText().toString());
                    //startPhoneNumberVerification(mBinding.fieldPhoneNumber.getText().toString());

                    break;

                case R.id.buttonVerifyPhone:

                    //String code = mBinding.fieldVerificationCode.getText().toString();
                    String code = fieldVerificationCode.getText().toString();


                    if (TextUtils.isEmpty(code)) {

                        //mBinding.fieldVerificationCode.setError("Cannot be empty.");
                        fieldVerificationCode.setError("Cannot be empty.");

                        return;

                    }



                    verifyPhoneNumberWithCode(mVerificationId, code);

                    break;

                case R.id.buttonResend:
                    resendVerificationCode(fieldPhoneNumber.getText().toString(), mResendToken);
                    //resendVerificationCode(mBinding.fieldPhoneNumber.getText().toString(), mResendToken);

                    break;

                case R.id.signOutButton:

                    signOut();

                    break;

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


    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == RESULT_OK && data != null) {
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                String phoneNumber = credential.getId();
                fieldPhoneNumber = findViewById(R.id.fieldPhoneNumber);
                fieldPhoneNumber.setText(credential.getId());
                // credential.getId(); <-- E.164 format phone number on 10.2.+ devices
                Log.d("verify", "onActivityResult: ");

            }
        }

    }

    */







    ////////ISD CODE BEGIN..............................................
    ///................................................
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.search:
                onSearchCalled();
                return true;
            default:
                return false;
            case android.R.id.home:
                finish();
                return true;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    public void onSearchCalled(){
        List<Place.Field> fields= Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
        Intent i = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN,fields).setCountry("IN").build(this);
        startActivityForResult(i,AUTOCOMPLETE_REQUEST_CODE);
    }

  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {

                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId() + ", " + place.getAddress());

                Toast.makeText(PhoneAuth.this, "ID: " + place.getId() + "address:" + place.getAddress() + "Name:" + place.getName() + " latlong: " + place.getLatLng(), Toast.LENGTH_LONG).show();

                String location = place.getAddress();

                address=findViewById(R.id.address);
                address.setText(location);




                // do query with address



            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {

                // TODO: Handle the error.

                assert data != null;
                Status status = Autocomplete.getStatusFromIntent(data);

                Toast.makeText(PhoneAuth.this, "Error: " + status.getStatusMessage(), Toast.LENGTH_LONG).show();

                assert status.getStatusMessage() != null;
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {

                // The user canceled the operation.

            }

        }
    }

   */

    public void store(final String Name, final String Contact, final String Address, final String Gender){
        database= FirebaseDatabase.getInstance();
        myRef = database.getReference();
        final String UserId = myRef.push().getKey();
        try {

            Users user = new Users(UserId, Name, Contact, Address, Gender);
            // final Random random= new Random();
            // String UserId=String.valueOf(random.nextInt(10000));

            HashMap<String, String> contacts = new HashMap<>();
            contacts.put("name",Name);
            contacts.put("mobile", Contact);
            contacts.put("address",Address);
            contacts.put("gender", Gender);
            //contactList.add(contacts);

            assert UserId != null;
            myRef.child(UserId).setValue(contacts)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Write was successful!
                            // ...
                            Toast.makeText(PhoneAuth.this, "Successful", Toast.LENGTH_SHORT).show();





                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Write failed
                            Toast.makeText(PhoneAuth.this, "Failed", Toast.LENGTH_SHORT).show();
                            // ...
                        }
                    });
        }catch (Exception e){
            Toast.makeText(this, "Failed reference", Toast.LENGTH_SHORT).show();
        }


        database=FirebaseDatabase.getInstance();
        myRef=database.getReference();
        //Query query= myRef.getParent();
        //assert query != null;
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                contactList.clear();

                Long count=snapshot.getChildrenCount();
                totalCount=count.toString();
                for (DataSnapshot child: snapshot.getChildren()){

                    HashMap<String, String> candidate= (HashMap<String, String>) child.getValue();
                    candidate.put("UserId",child.getKey());
                    contactList.add(candidate);




                }

                layout_detail = findViewById(R.id.layout_detail);
                layout_detail.setVisibility(View.GONE);

                ListAdapter adapter = new SimpleAdapter(
                        PhoneAuth.this, contactList,
                        R.layout.list_item, new String[]{"name", "address",
                        "mobile"}, new int[]{R.id.name,
                        R.id.address, R.id.mobile});
                lv = (ListView) findViewById(R.id.list);
                lv.setAdapter(adapter);
                lv.setVisibility(View.VISIBLE);




            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void store(final String Name, final String Contact, final String Address, final String Gender, final String extractedUserId){
        database= FirebaseDatabase.getInstance();
        myRef = database.getReference();
        final String UserId=extractedUserId;
        try {

            Users user = new Users(UserId, Name, Contact, Address, Gender);
            // final Random random= new Random();
            // String UserId=String.valueOf(random.nextInt(10000));

            HashMap<String, String> contacts = new HashMap<>();
            contacts.put("name",Name);
            contacts.put("mobile", Contact);
            contacts.put("address",Address);
            contacts.put("gender", Gender);
            //contactList.add(contacts);

            assert UserId != null;
            myRef.child(UserId).setValue(contacts)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Write was successful!
                            // ...
                            Toast.makeText(PhoneAuth.this, "Successful", Toast.LENGTH_SHORT).show();





                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Write failed
                            Toast.makeText(PhoneAuth.this, "Failed", Toast.LENGTH_SHORT).show();
                            // ...
                        }
                    });
        }catch (Exception e){
            Toast.makeText(this, "Failed reference", Toast.LENGTH_SHORT).show();
        }


        database=FirebaseDatabase.getInstance();
        myRef=database.getReference();
        //Query query= myRef.getParent();
        //assert query != null;
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                contactList.clear();

                Long count=snapshot.getChildrenCount();
                totalCount=count.toString();
                for (DataSnapshot child: snapshot.getChildren()){
                    HashMap<String, String> candidate= (HashMap<String, String>) child.getValue();
                    candidate.put("UserId",child.getKey());
                    contactList.add(candidate);




                }

                layout_detail = findViewById(R.id.layout_detail);
                layout_detail.setVisibility(View.GONE);

                ListAdapter adapter = new SimpleAdapter(
                        PhoneAuth.this, contactList,
                        R.layout.list_item, new String[]{"name", "address",
                        "mobile"}, new int[]{R.id.name,
                        R.id.address, R.id.mobile});
                lv = (ListView) findViewById(R.id.list);
                lv.setAdapter(adapter);
                lv.setVisibility(View.VISIBLE);




            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

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

    /*

     */


// }
//////////////////////////////////
////....................ISD CODE END



    ///////common code....
    //..........................................common code for activity result

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == RESULT_OK && data != null) {
                Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                String phoneNumber = credential.getId();
                fieldPhoneNumber = findViewById(R.id.fieldPhoneNumber);
                fieldPhoneNumber.setText(credential.getId());
                // credential.getId(); <-- E.164 format phone number on 10.2.+ devices
                Log.d("verify", "onActivityResult: ");

            }
        }
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {

                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId() + ", " + place.getAddress());

                Toast.makeText(PhoneAuth.this, "ID: " + place.getId() + "address:" + place.getAddress() + "Name:" + place.getName() + " latlong: " + place.getLatLng(), Toast.LENGTH_LONG).show();

                String location = place.getAddress();
                EditText screen_address;

                screen_address=findViewById(R.id.l_address);
                screen_address.setText(location);




                // do query with address



            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {

                // TODO: Handle the error.

                assert data != null;
                Status status = Autocomplete.getStatusFromIntent(data);

                Toast.makeText(PhoneAuth.this, "Error: " + status.getStatusMessage(), Toast.LENGTH_LONG).show();

                assert status.getStatusMessage() != null;
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {

                // The user canceled the operation.

            }

        }

    }
    /////
    ////common code end............
}