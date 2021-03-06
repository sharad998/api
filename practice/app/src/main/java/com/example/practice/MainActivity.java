package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;

    Button callButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {

//If the app doesn’t have the CALL_PHONE permission, request it//

                requestPermission();
            }
        }

    }

    public boolean checkPermission() {

        int CallPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE);

        return CallPermissionResult == PackageManager.PERMISSION_GRANTED;

    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {
                        Manifest.permission.CALL_PHONE
                }, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case PERMISSION_REQUEST_CODE:
                callButton = (Button)findViewById(R.id.button);

                if (grantResults.length > 0) {

                    boolean CallPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (CallPermission ) {

                        Toast.makeText(MainActivity.this,
                                "Permission accepted", Toast.LENGTH_LONG).show();

//If the permission is denied….//

                    } else {
                        Toast.makeText(MainActivity.this,

//...display the following toast...//

                                "Permission denied", Toast.LENGTH_LONG).show();

//...and disable the call button.//

                        callButton.setEnabled(false);

                    }
                    break;
                }
        }
    }

    public void call(View view)
    {
        final EditText phoneNumber = (EditText) findViewById(R.id.number);
        String phoneNum = phoneNumber.getText().toString();
        if(!TextUtils.isEmpty(phoneNum)) {
            String dial = "tel:" + phoneNum;

//Make an Intent object of type intent.ACTION_CALL//

            startActivity(new Intent(Intent.ACTION_CALL,

//Extract the telephone number from the URI//

                    Uri.parse(dial)));
        }else {
            Toast.makeText(MainActivity.this, "Please enter a valid telephone number", Toast.LENGTH_SHORT).show();
        }

    }

}