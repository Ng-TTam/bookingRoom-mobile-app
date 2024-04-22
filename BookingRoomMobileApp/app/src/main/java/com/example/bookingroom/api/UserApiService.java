package com.example.bookingroom.api;

import com.example.bookingroom.model.UserDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserApiService {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    UserApiService userApiService = new Retrofit.Builder()
            .baseUrl("http://192.168.0.12:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UserApiService.class);

    @POST("/login")
    Call<ResponseBody> login(@Body Map<String, String> map);

    @GET("/user")
    Call<UserDTO> getUserInfo(@Header("Authorization") String token);

    @POST("/change-password")
    Call<ResponseBody> changePassword(@Header("Authorization") String token, @Body Map<String, String> map);

    @POST("/change-info")
    Call<ResponseBody> changeInfo(@Header("Authorization") String token, @Body UserDTO userDTO);

    @GET("/user/reward-point")
    Call<ResponseBody> getChangeRewardPointUser(@Header("Authorization") String token);
}
