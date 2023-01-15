package com.example.login.Remote;

import com.example.login.User;
import com.example.login.UserRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {
    @PUT("/addUser")
    Call<UserRes> addUser(@Body User responseRegisterClass);

    @POST("/getUser")
    Call<UserRes> getUser(@Body User responseRegisterClass);
}
