package com.example.bookingroom.api;

import com.example.bookingroom.model.BookingDTO;
import com.example.bookingroom.model.NotificationDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NotificationApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    NotificationApiService notificationApiService = new Retrofit.Builder()
            .baseUrl("http://192.168.0.12:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(NotificationApiService.class);

    @GET("/user/notification")
    Call<List<NotificationDTO>> getAllNotification(@Header("Authorization") String token);

    @POST("/booking/notification")
    Call<ResponseBody> addBookingNotification(@Header("Authorization") String token, @Body NotificationDTO notificationDTO);

    @POST("/notification/seen/{id}")
    Call<ResponseBody> seenNotification(@Path("id") int id);

    @POST("/notification/seen/all")
    Call<ResponseBody> seenAllNotification(@Header("Authorization") String token);

}
