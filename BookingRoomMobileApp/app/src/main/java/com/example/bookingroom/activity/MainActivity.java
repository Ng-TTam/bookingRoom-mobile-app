package com.example.bookingroom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingroom.api.NotificationApiService;
import com.example.bookingroom.fragment.AccountFragment;
import com.example.bookingroom.fragment.NotificationFragment;
import com.example.bookingroom.fragment.HomeFragment;
import com.example.bookingroom.fragment.ScheduleFragment;
import com.example.bookingroom.R;
import com.example.bookingroom.model.NotificationDTO;

import java.util.List;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    SmoothBottomBar smoothBottomBar;

    TextView badge;

    @Override
    protected void onResume() {
        super.onResume();
        getNumBadge();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smoothBottomBar = findViewById(R.id.bottomNavigationView);
        badge = findViewById(R.id.badge_num);

        smoothBottomBar.setItemIconTintActive(Color.parseColor("#4C4DDC"));

        smoothBottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                if(i == 0) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flFragment, new HomeFragment());
                    fragmentTransaction.commit();

                }

                if(i == 1) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flFragment, new ScheduleFragment());
                    fragmentTransaction.commit();
                }

                if(i == 2) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flFragment, new NotificationFragment());
                    fragmentTransaction.commit();
                }

                if(i == 3) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flFragment, new AccountFragment());
                    fragmentTransaction.commit();
                }
                return false;
            }
        });

        Intent intent = getIntent();
        if(intent != null){
            int receivedInt = intent.getIntExtra("fragmentToShow", 0);
            smoothBottomBar.setItemActiveIndex(receivedInt);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if(receivedInt == 0) {
                fragmentTransaction.replace(R.id.flFragment, new HomeFragment());
            }
            if(receivedInt == 1) {
                fragmentTransaction.replace(R.id.flFragment, new ScheduleFragment());
            }
            if(receivedInt == 2) {
                fragmentTransaction.replace(R.id.flFragment, new NotificationFragment());
            }
            if(receivedInt == 3){
                fragmentTransaction.replace(R.id.flFragment, new AccountFragment());
            }
            fragmentTransaction.commit();
        }else {
            smoothBottomBar.setItemActiveIndex(0);
        }
    }

    private void getNumBadge(){
        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("key", null);
        token = "Bearer " + token;

        NotificationApiService.notificationApiService.getAllNotification(token).enqueue(new Callback<List<NotificationDTO>>() {
            @Override
            public void onResponse(Call<List<NotificationDTO>> call, Response<List<NotificationDTO>> response) {
                if (response.isSuccessful()) {
                    List<NotificationDTO> notificationDTOs = response.body();
                    if (notificationDTOs != null) {
                        int count = 0;
                        for(NotificationDTO notificationDTO: notificationDTOs){
                            if(!notificationDTO.getSeen()) count++;
                        }
                        if(count > 0) {
                            badge.setText(String.valueOf(count));
                        }
                        else{
                            badge.setVisibility(View.GONE);
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Lấy thông tin thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<NotificationDTO>> call, Throwable t) {
                Log.e("Retrofit", "onFailure", t);
                Toast.makeText(MainActivity.this, "Sevrer bị hỏng", Toast.LENGTH_SHORT).show();
            }
        });
    }

}