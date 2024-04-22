package com.example.bookingroom.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingroom.R;
import com.example.bookingroom.api.UserApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        //check login?
//        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
//        String token = sharedPreferences.getString("key", null);
//        if (token != null) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        } else {
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    login(username, password);
                }
            }
        });
    }

    private void login(String username, String password){
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        UserApiService.userApiService.login(map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try{
                        String key = response.body().string();
                        if (key != null && !key.equals("")) {
                            SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("key", key);
                            editor.apply();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            TextView error = findViewById(R.id.error);
                            error.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Vui lòng nhập lại thông tin", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        Log.e("KEY", "Error converting response body to string", e);
                    }
                } else {
                    TextView error = findViewById(R.id.error);
                    error.setVisibility(View.VISIBLE);
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập lại thông tin", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Retrofit", "onFailure", t);
                Toast.makeText(LoginActivity.this, "Sevrer bị hỏng", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
