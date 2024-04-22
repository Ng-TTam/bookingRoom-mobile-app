package com.example.bookingroom.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingroom.api.DiscountApiService;
import com.example.bookingroom.adapter.DiscountAdapter;
import com.example.bookingroom.model.DiscountDTO;
import com.example.bookingroom.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RewardActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    LinearLayout btnExchangeReward;
    LinearLayout btnViewAllRewards;
    TextView noneRecycleView;
    ImageView btnBack;
    TextView rewardPoint;
    private RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward);

        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String point = sharedPreferences.getString("rewardPoint", "0");
        rewardPoint = findViewById(R.id.reward_point);
        rewardPoint.setText(point + " điểm");

        ButtonAction();
        AdapterAction();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
            String point = sharedPreferences.getString("rewardPoint", "0");
            rewardPoint = findViewById(R.id.reward_point);
            rewardPoint.setText(point + " điểm");
    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
            String point = sharedPreferences.getString("rewardPoint", "0");
            rewardPoint = findViewById(R.id.reward_point);
            rewardPoint.setText(point + " điểm");
        }
    }

    private void ButtonAction(){
        btnExchangeReward = findViewById(R.id.exchangeReward);
        btnExchangeReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RewardActivity.this, ExchangeRewardActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        btnViewAllRewards = findViewById(R.id.viewAllRewards);

        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void AdapterAction(){
        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("key", null);
        token = "Bearer " + token;

        DiscountApiService.discountApiService.getDiscountOwned(token).enqueue(new Callback<List<DiscountDTO>>() {
            @Override
            public void onResponse(Call<List<DiscountDTO>> call, Response<List<DiscountDTO>> response) {
                if (response.isSuccessful()) {
                    List<DiscountDTO> discountDTOs = response.body();
                    if (discountDTOs != null) {
                        showListDiscount(discountDTOs);
                    }
                } else {
                    Toast.makeText(RewardActivity.this, "Lấy thông tin thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DiscountDTO>> call, Throwable t) {
                Log.e("Retrofit", "onFailure", t);
                Toast.makeText(RewardActivity.this, "Sevrer bị hỏng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showListDiscount(List<DiscountDTO> listDiscount){
        if (listDiscount.isEmpty()) {
            noneRecycleView = findViewById(R.id.none_recycleview);
            noneRecycleView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView = findViewById(R.id.list_discount);
            DiscountAdapter discountAdapter = new DiscountAdapter(listDiscount);
            recyclerView.setAdapter(discountAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}
