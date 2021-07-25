package com.example.one_tap.API;


import com.example.one_tap.Data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {


    @POST("/api/userAccount/getUserDetails")
    Call<User> VerifyNumber(  @Body String mobile);

    @FormUrlEncoded
    @POST("/api/userAccount/createUser")
    Call<User> CreateUser(@Field("mobile")  @Body String mobile);




}

