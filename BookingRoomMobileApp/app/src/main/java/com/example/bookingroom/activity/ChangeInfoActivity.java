package com.example.bookingroom.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingroom.R;
import com.example.bookingroom.api.UserApiService;
import com.example.bookingroom.model.UserDTO;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeInfoActivity extends AppCompatActivity {
    TextView name;
    TextView email;
    TextView number;
    TextView birth;
    ImageButton changeInfoButton;
    ImageButton buttonBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info_account);

        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        number = findViewById(R.id.number);
        birth = findViewById(R.id.birth);
        changeInfoButton = findViewById(R.id.change_info_button);

        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        name.setText(sharedPreferences.getString("nameAccount", ""));
        email.setText(sharedPreferences.getString("email", ""));
        number.setText(sharedPreferences.getString("number", ""));
        birth.setText(sharedPreferences.getString("birth", ""));
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        changeInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDTO userDTO = new UserDTO();
                userDTO.setNameAccount(String.valueOf(name.getText()).trim());
                userDTO.setEmail(String.valueOf(email.getText()).trim());
                userDTO.setNumber(String.valueOf(number.getText()).trim());
                userDTO.setBirth(String.valueOf(birth.getText()).trim());

                SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
                String token = sharedPreferences.getString("key", null);
                token = "Bearer " + token;
                UserApiService.userApiService.changeInfo(token, userDTO).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try{
                                String mess = response.body().string();
                                if (!mess.equals("Change info successful")) {
                                    changeInfoButton.setEnabled(false);
                                    Toast toast = Toast.makeText(ChangeInfoActivity.this, "Thay đổi thông tin thành công", Toast.LENGTH_SHORT);
                                    toast.setDuration(Toast.LENGTH_SHORT);
                                    toast.show();

                                    SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("key", mess);
                                    editor.apply();
                                    // Use a Handler to delay the navigation for 2 seconds
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(ChangeInfoActivity.this, MainActivity.class);
                                            intent.putExtra("fragmentToShow", 3);  // Giả sử AccountFragment là 3
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                        }, 1000);
                                    } else {
                                        Toast.makeText(ChangeInfoActivity.this, "Sai thông tin", Toast.LENGTH_SHORT).show();
                                    }
                            } catch (IOException e) {
                                Log.e("KEY", "Error converting response body to string", e);
                            }
                        } else {
                            Toast.makeText(ChangeInfoActivity.this, "Sai thông tin", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("Retrofit", "onFailure", t);
                        Toast.makeText(ChangeInfoActivity.this, "Sevrer bị hỏng", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void showDatePickerDialog() {
        // Lấy ngày hiện tại làm mặc định
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Tạo mới một DatePickerDialog và hiển thị nó
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DatePickerTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date;
                        if(month < 9) date = year + "-0" + (month + 1) + "-" + dayOfMonth;
                        else date = year + "-" + (month + 1) + "-" + dayOfMonth;
                        birth.setText(date);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }
}
