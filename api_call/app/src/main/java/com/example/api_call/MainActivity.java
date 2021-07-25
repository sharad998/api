package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    private static String url = "https://reqres.in/api/users?page=1";
    ArrayList<HashMap<String,String>> contactList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = new ArrayList<>();

        lv= (ListView) findViewById(R.id.list);

        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }


        @Override
        protected Void doInBackground(Void... voids) {
            Data sh = new Data();
            String jsonStr=sh.makeServiceCall(url);

            Log.e(TAG,"Response from URL:" +jsonStr);

            if(jsonStr!=null){
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray contacts = jsonObj.getJSONArray("data");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String first_name = c.getString("first_name");
                        String last_name = c.getString("last_name");
                        String email = c.getString("email");
                        String imgUrl=c.getString("avatar");


                        // Phone node is JSON Object
                       JSONObject phone = jsonObj.getJSONObject("ad");
                       String mobile = phone.getString("company");
                      // String home = phone.getString("Weekly");
                       String office = phone.getString("text");
                       String url=phone.getString("url");

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("id", id);
                        contact.put("name", first_name+last_name);
                        contact.put("email", email);
                        contact.put("mobile", mobile);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                }
                catch (final JSONException e){
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });


                }
            }
            else{
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }


            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, contactList,
                    R.layout.list_item, new String[]{"name", "email",
                    "mobile"}, new int[]{R.id.name,
                    R.id.email, R.id.mobile});

            lv.setAdapter(adapter);
        }


    }
}

/*
 list.setOnClickListener(new LinearLayout.OnClickListener()   {
            @Override
            public void onClick(View v) {
                LinearLayout layout_detail= findViewById(R.id.layout_detail);
                ListView list=findViewById(R.id.list);
                address=findViewById(R.id.address);
                mobile=findViewById(R.id.mobile);
                name=findViewById(R.id.name);
                autoCompleteGender=findViewById(R.id.gender);
                TextView listAddress=findViewById(R.id.listAddress);
                TextView listMobile=findViewById(R.id.listMobile);
                TextView listName=findViewById(R.id.listName);
                TextView listGender=findViewById(R.id.listGender);


                address.setText(listAddress.getText().toString());
                mobile.setText(listMobile.getText().toString());
                name.setText(listName.getText().toString());

                list.setVisibility(View.GONE);
                layout_detail.setVisibility(View.VISIBLE);



            }
        });
 */