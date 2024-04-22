package com.example.bookingroom.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bookingroom.activity.ChangeInfoActivity;
import com.example.bookingroom.activity.ChangePasswordActivity;
import com.example.bookingroom.activity.LoginActivity;
import com.example.bookingroom.activity.RewardActivity;
import com.example.bookingroom.R;
import com.example.bookingroom.api.UserApiService;
import com.example.bookingroom.model.UserDTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import me.ibrahimsn.lib.SmoothBottomBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {
    LinearLayout btnviewAccountInfo;
    LinearLayout btnviewAccountPassword;
    LinearLayout btnviewReward;

    TextView nameLogin;
    TextView nameAccount;
    TextView email;
    TextView number;
    TextView birth;

    ImageButton logOutButton;

    public AccountFragment(){
    }

    @Override
    public void onResume() {
        super.onResume();
        getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        ActionButton(view);
        return view;
    }

    private void ActionButton(View view){
        LinearLayout accountInfo = view.findViewById(R.id.account_info);

        nameLogin = view.findViewById(R.id.namelog);
        nameAccount = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        number = view.findViewById(R.id.number);
        birth = view.findViewById(R.id.birth);

        btnviewAccountInfo = view.findViewById(R.id.btnviewAccountInfo);
        TextView textInfo = view.findViewById(R.id.text_info);
        btnviewAccountInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("Thay đổi".equals(textInfo.getText())) {
                    Intent intent = new Intent(getActivity(), ChangeInfoActivity.class);
                    startActivity(intent);
                }else{
                    textInfo.setText("Thay đổi");
                    getUser();

                    if (accountInfo.getVisibility() == View.GONE) {
                        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
                        fadeIn.setDuration(200); // Đặt thời gian của animation (200ms)
                        fadeIn.setFillAfter(true); // Giữ lại trạng thái cuối cùng của layout sau khi kết thúc animation
                        accountInfo.startAnimation(fadeIn);
                        accountInfo.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        btnviewAccountPassword = view.findViewById(R.id.btnviewAccountPassword);
        btnviewAccountPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        btnviewReward = view.findViewById(R.id.viewReward);
        btnviewReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUser();
                Intent intent = new Intent(getActivity(), RewardActivity.class);
                startActivity(intent);
            }
        });

        logOutButton = view.findViewById(R.id.button_logout);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences("my_prefs", MODE_PRIVATE);
                preferences.edit().clear().apply();

                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(loginIntent);
                getActivity().finish();
            }
        });
    }

    private void getUser(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_prefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("key", null);
        token = "Bearer " + token;
        UserApiService.userApiService.getUserInfo(token).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                UserDTO user = response.body();
                nameLogin.setText(user.getNameLogin());
                nameAccount.setText(user.getNameAccount());
                email.setText(user.getEmail());
                number.setText(user.getNumber());
                birth.setText(user.getBirth());

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nameAccount", user.getNameAccount());
                editor.putString("email", user.getEmail());
                editor.putString("number", user.getNumber());
                editor.putString("birth", user.getBirth());
                editor.putString("rewardPoint", String.valueOf(user.getRewardPoint()));
                Log.e("rewardPoint", String.valueOf(user.getRewardPoint()));
                editor.commit();
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Log.e("Retrofit", "onFailure", t);
                Toast.makeText(getActivity(), "Sevrer bị hỏng", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
