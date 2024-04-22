package com.example.bookingroom.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingroom.R;
import com.example.bookingroom.api.DiscountApiService;
import com.example.bookingroom.adapter.DiscountGetAdapter;
import com.example.bookingroom.api.UserApiService;
import com.example.bookingroom.model.DiscountDTO;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExchangeRewardActivity extends AppCompatActivity {
    TextView noneNewDiscount;
    ImageButton buttonBack;
    TextView rewardPoint;
    private RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exchange_reward);

        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String point = sharedPreferences.getString("rewardPoint", "0");
        rewardPoint = findViewById(R.id.reward_point_user);
        rewardPoint.setText(point + " điểm");

        BackButton();
        AdapterAction();
    }

    private void BackButton(){
        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
                String token = sharedPreferences.getString("key", null);
                token = "Bearer " + token;

                UserApiService.userApiService.getChangeRewardPointUser(token).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            try{
                                String mess = response.body().string();
                                if (!mess.equals("") && mess != null) {
                                    SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("key", mess);
                                    editor.apply();
                                } else {
                                    finish();
                                }
                            } catch (IOException e) {
                            }
                        } else {
                            Toast.makeText(ExchangeRewardActivity.this, "Lấy dữ liệu không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(ExchangeRewardActivity.this, "Sevrer bị hỏng", Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent = new Intent(ExchangeRewardActivity.this, MainActivity.class);
                intent.putExtra("fragmentToShow", 3);  // Giả sử AccountFragment là 3
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void AdapterAction(){
        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("key", null);
        token = "Bearer " + token;

        DiscountApiService.discountApiService.getDiscountUnowned(token).enqueue(new Callback<List<DiscountDTO>>() {
            @Override
            public void onResponse(Call<List<DiscountDTO>> call, Response<List<DiscountDTO>> response) {
                if (response.isSuccessful()) {
                    List<DiscountDTO> discountDTOs = response.body();
                    if (discountDTOs != null) {
                        showListDiscount(discountDTOs);
                    }
                } else {
                    Toast.makeText(ExchangeRewardActivity.this, "Lấy thông tin thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DiscountDTO>> call, Throwable t) {
                Log.e("Retrofit", "onFailure", t);
                Toast.makeText(ExchangeRewardActivity.this, "Sevrer bị hỏng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showListDiscount(List<DiscountDTO> listDiscount){
        if (listDiscount.isEmpty()) {
            noneNewDiscount = findViewById(R.id.none_discount);
            noneNewDiscount.setVisibility(View.VISIBLE);
        }
        else {
            SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
            String token = sharedPreferences.getString("key", null);
            token = "Bearer " + token;
            sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);

            recyclerView = findViewById(R.id.list_new_discount);
            DiscountGetAdapter discountGetAdapter = new DiscountGetAdapter(listDiscount,
                    rewardPoint, sharedPreferences, token);
            recyclerView.setAdapter(discountGetAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}
