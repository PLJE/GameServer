package com.example.gameserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/user/login")
    Call <Login> executeLoginkakao(@Body HashMap<String,String> map);

    @POST("/user/loginapp")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/user/signupapp")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

    @POST("/user/sendscore")
    Call <Void> executeSendScore (@Body HashMap<String, String> map);

    @GET("/user/rankrequest")
    Call <List<Rank>> executerank();
}