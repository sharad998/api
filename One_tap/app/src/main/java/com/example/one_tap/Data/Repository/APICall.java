package com.example.one_tap.Data.Repository;

import android.util.Log;

import com.example.one_tap.API.APIClient;
import com.example.one_tap.API.APIInterface;
import com.example.one_tap.Data.Data;
import com.example.one_tap.Data.User;

import java.security.PublicKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APICall {
    public APIInterface apiInterface;
    public String message;
    public Boolean success;
    public User userDetails = new User();
    public Data data;

    public APICall(APIInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public User verify(String number){
        apiInterface= APIClient.getClient().create(APIInterface.class);

        Call<User> call= apiInterface.VerifyNumber(number);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
               User userDetail =response.body();
                userDetails.setData(userDetail.getData());
               // userDetails.setMessage(userDetail.getMessage());
               userDetails.setSuccess(userDetail.getSuccess());

                Log.e("Madhu",userDetails.getSuccess().toString());


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("more","failed");





            }
        });



       return  userDetails ;
    }
}
