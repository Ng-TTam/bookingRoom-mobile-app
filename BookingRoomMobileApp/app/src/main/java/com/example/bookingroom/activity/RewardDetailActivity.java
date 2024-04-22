package com.example.bookingroom.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bookingroom.R;
import com.example.bookingroom.api.DiscountApiService;
import com.example.bookingroom.model.DiscountDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RewardDetailActivity extends AppCompatActivity {
    ImageView imgReward;
    TextView address;
    TextView date;
    TextView term;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reward_detail);

        imgReward = findViewById(R.id.imgReward);
        date = findViewById(R.id.date);
        address = findViewById(R.id.address);
        term = findViewById(R.id.term);

        ImageButton back = findViewById(R.id.buttonBack);
        back.setOnClickListener(v -> {
            finish();
        });

        Intent intent = getIntent();
        if (intent != null) {
            int discountId = intent.getIntExtra("discountId", -1); // -1 là giá trị mặc định nếu không tìm thấy 'discountId'
            if (discountId != -1) {
                SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
                String token = sharedPreferences.getString("key", null);
                token = "Bearer " + token;

                DiscountApiService.discountApiService.getDiscountById(token, discountId).enqueue(new Callback<DiscountDTO>() {
                    @Override
                    public void onResponse(Call<DiscountDTO> call, Response<DiscountDTO> response) {
                        if(response.isSuccessful()){
                            DiscountDTO discount = response.body();
                            Glide.with(RewardDetailActivity.this)
                                    .load("http://192.168.0.12:8080/image/discounts/" + discount.getImage())
                                    .into(imgReward);
                            date.setText(discount.getOutOfDate());
                            address.setText(discount.getHotelDTO().getAddress());
                            term.setText(discount.getTerm());
                        }
                    }

                    @Override
                    public void onFailure(Call<DiscountDTO> call, Throwable t) {
                        Log.e("Retrofit", "onFailure", t);
                        Toast.makeText(RewardDetailActivity.this, "Sevrer bị hỏng", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else {
            Toast.makeText(RewardDetailActivity.this, "Intent không giá trị", Toast.LENGTH_SHORT).show();
        }
    }
}
