package com.example.gameserver;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/user/login")
    Call <Login> executeLoginkakao(@Body HashMap<String,String> map);

    @POST("/user/loginapp")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/user/signupapp")
    Call<Void> executeSignup (@Body HashMap<String, String> map);
}