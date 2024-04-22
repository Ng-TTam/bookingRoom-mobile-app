package com.example.bookingroom.api;

import com.example.bookingroom.model.DiscountDTO;
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

public interface DiscountApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    DiscountApiService discountApiService = new Retrofit.Builder()
            .baseUrl("http://192.168.0.12:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(DiscountApiService.class);

    @GET("/user/discount-owned")
    Call<List<DiscountDTO>> getDiscountOwned(@Header("Authorization") String token);

    @GET("/user/discount-unowned")
    Call<List<DiscountDTO>> getDiscountUnowned(@Header("Authorization") String token);

    @GET("/user/discount/{id}")
    Call<DiscountDTO> getDiscountById(@Header("Authorization") String token, @Path("id") int id);

    @POST("user/exchange-discount")
    Call<Boolean> exchangeDiscount(@Header("Authorization") String token, @Body DiscountDTO discountDTO);
}
