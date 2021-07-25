package com.example.assignment01;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;


public class ISD extends AppCompatActivity {

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

    public ArrayList<HashMap<String,String>> contactList;
    public String totalCount;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_s_d);

        contactList = new ArrayList<>();
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference();




        logButton= findViewById(R.id.log);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final Dialog dialog= new Dialog(ISD.this);
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

                if(saveDetail.getText().toString()=="Edit"){
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
                saveDetail.setText("Edit");
                lv.setVisibility(View.GONE);
                layout_detail.setVisibility(View.VISIBLE);





            }
        });

        ///////////








    }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {

                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId() + ", " + place.getAddress());

                Toast.makeText(ISD.this, "ID: " + place.getId() + "address:" + place.getAddress() + "Name:" + place.getName() + " latlong: " + place.getLatLng(), Toast.LENGTH_LONG).show();

                String location = place.getAddress();

                address=findViewById(R.id.l_address);
                address.setText(location);




                // do query with address



            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {

                // TODO: Handle the error.

                assert data != null;
                Status status = Autocomplete.getStatusFromIntent(data);

                Toast.makeText(ISD.this, "Error: " + status.getStatusMessage(), Toast.LENGTH_LONG).show();

                assert status.getStatusMessage() != null;
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {

                // The user canceled the operation.

            }

        }
    }

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
                            Toast.makeText(ISD.this, "Successful", Toast.LENGTH_SHORT).show();





                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Write failed
                            Toast.makeText(ISD.this, "Failed", Toast.LENGTH_SHORT).show();
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
                        ISD.this, contactList,
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
                            Toast.makeText(ISD.this, "Successful", Toast.LENGTH_SHORT).show();





                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Write failed
                            Toast.makeText(ISD.this, "Failed", Toast.LENGTH_SHORT).show();
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
                        ISD.this, contactList,
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
    protected void onStart() {
        super.onStart();
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


}


