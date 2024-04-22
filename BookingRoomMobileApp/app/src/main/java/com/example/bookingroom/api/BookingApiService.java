package com.example.bookingroom.api;

import com.example.bookingroom.model.BookingDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookingApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    BookingApiService bookingApiService = new Retrofit.Builder()
            .baseUrl("http://192.168.0.12:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(BookingApiService.class);

    @GET("/booking/all")
    Call<List<BookingDTO>> getAllBooking(@Header("Authorization") String token);

    @GET("/booking/detail/{id}")
    Call<BookingDTO> getBookingById(@Header("Authorization") String token, @Path("id") int id);

    @POST("/booking/update")
    Call<ResponseBody> updateBooking(@Header("Authorization") String token, @Body BookingDTO bookingDTO);
}
