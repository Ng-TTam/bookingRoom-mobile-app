package com.example.bookingroom.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingroom.R;
import com.example.bookingroom.api.UserApiService;
import com.example.bookingroom.fragment.AccountFragment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText oldPass;
    EditText newPass;
    EditText verifyNewPass;
    ImageButton changePasswordButton;
    ImageButton buttonBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_password_account);

        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        changePasswordButton = findViewById(R.id.change_password_button);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPass = findViewById(R.id.old_pass);
                newPass = findViewById(R.id.new_pass);
                verifyNewPass = findViewById(R.id.verify_new_pass);

                String currentPassword = String.valueOf(oldPass.getText()).trim();
                String newPassword = String.valueOf(newPass.getText()).trim();
                String verifyPassword = String.valueOf(verifyNewPass.getText()).trim();
                if(currentPassword == null || newPassword == null || verifyPassword == null){
                    Toast.makeText(ChangePasswordActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }

                if(!newPassword.equals(verifyPassword)){
                    Toast.makeText(ChangePasswordActivity.this, "Mật khẩu nhập lại bị sai", Toast.LENGTH_SHORT).show();
                }else {
                    Map<String, String> map = new HashMap<>();
                    map.put("newPassword", newPassword);
                    map.put("currentPassword", currentPassword);

                    SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
                    String token = sharedPreferences.getString("key", null);
                    token = "Bearer " + token;
                    UserApiService.userApiService.changePassword(token, map).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                try{
                                    String mess = response.body().string();
                                    if (mess.equals("Change password successful")) {
                                        changePasswordButton.setEnabled(false);
                                        Toast toast = Toast.makeText(ChangePasswordActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT);

                                        toast.setDuration(Toast.LENGTH_SHORT);
                                        toast.show();
                                        // Use a Handler to delay the navigation for 2 seconds
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(ChangePasswordActivity.this, AccountFragment.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }, 3000);

                                    } else {
                                        Toast.makeText(ChangePasswordActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (IOException e) {
                                    Log.e("KEY", "Error converting response body to string", e);
                                }
                            } else {
                                Toast.makeText(ChangePasswordActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("Retrofit", "onFailure", t);
                            Toast.makeText(ChangePasswordActivity.this, "Sevrer bị hỏng", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }
}
