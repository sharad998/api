package com.example.assignment01;

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
import java.util.Iterator;

public class ViewData extends AppCompatActivity {

    private static String url= "https://assignment01-398ca.firebaseio.com/";
    private String TAG = ViewData.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    ArrayList<HashMap<String,String>> contactList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_view_data);
        contactList = new ArrayList<>();

        lv= (ListView) findViewById(R.id.list);

        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(ViewData.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }


        @Override
        protected Void doInBackground(Void... voids) {
            Data sh = new Data();
            String jsonStr=sh.makeServiceCall(url);

            Log.e(TAG,"Response from URL:" +jsonStr);

            if(jsonStr!=null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONObject cotacts = jsonObj.getJSONObject("assignment01-398ca");
                    JSONObject contacts = cotacts.getJSONObject("Users");

                    Iterator<?> keys = contacts.keys();
                    while (keys.hasNext()) {
                        String key = (String) keys.next();
                        if (contacts.get(key) instanceof JSONObject) {
                            JSONObject xx = new JSONObject(((JSONObject) contacts.get(key)).toString());
                            Iterator<?> k = xx.keys();
                            while(k.hasNext()){
                                String ky=(String) k.next();
                                if(xx.get(key) instanceof JSONObject){
                                    String first_name = xx.getString("address");
                                    String last_name = xx.getString("contact");
                                    String email = xx.getString("gender");
                                    String imgUrl = xx.getString("name");
                                    HashMap<String, String> contact = new HashMap<>();

                                    // adding each child node to HashMap key => value
                                    // contact.put("id", id);`
                                    contact.put("name", first_name + last_name);
                                    contact.put("email", email);
                                    //contact.put("mobile", mobile);


                                    // adding contact to contact list
                                    contactList.add(contact);

                                }
                            }


                        }

                    }
////////////////////////////////
 /*

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        //String id = c.getString("id");
                        String first_name = c.getString("address");
                        String last_name = c.getString("contact");
                        String email = c.getString("gender");
                        String imgUrl=c.getString("name");

//////////////////////////////////////

                        // Phone node is JSON Object
                        JSONObject phone = jsonObj.getJSONObject("ad");
                        String mobile = phone.getString("company");
                        // String home = phone.getString("Weekly");
                        String office = phone.getString("text");
                        String url=phone.getString("url");



                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                       // contact.put("id", id);
                        contact.put("name", first_name+last_name);
                        contact.put("email", email);
                        //contact.put("mobile", mobile);



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

  */
                } catch (final JSONException e) {
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
                    ViewData.this, contactList,
                    R.layout.list_item, new String[]{"name", "email",
                    "mobile"}, new int[]{R.id.name,
                    R.id.address, R.id.mobile});

            lv.setAdapter(adapter);
        }


    }
}