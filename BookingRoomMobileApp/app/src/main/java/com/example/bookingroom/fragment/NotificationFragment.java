package com.example.bookingroom.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingroom.R;
import com.example.bookingroom.activity.ChangeScheduleActivity;
import com.example.bookingroom.activity.MainActivity;
import com.example.bookingroom.adapter.NotificationAdapter;
import com.example.bookingroom.api.NotificationApiService;
import com.example.bookingroom.model.NotificationDTO;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends Fragment {
    
    private RecyclerView recyclerView;
    TextView no_notification;
    ImageButton buttonSeenAll;

    public NotificationFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        
        recyclerView = view.findViewById(R.id.list_notification);
        no_notification = view.findViewById(R.id.no_notification);
        AdapterAction();

        buttonSeenAll = view.findViewById(R.id.see_all);
        buttonSeenAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markNotification();
            }
        });

        return view;
    }

    private void AdapterAction(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("key", null);
        token = "Bearer " + token;

        NotificationApiService.notificationApiService.getAllNotification(token).enqueue(new Callback<List<NotificationDTO>>() {
            @Override
            public void onResponse(Call<List<NotificationDTO>> call, Response<List<NotificationDTO>> response) {
                if (response.isSuccessful()) {
                    List<NotificationDTO> NotificationDTOs = response.body();
                    if (NotificationDTOs != null) {
                        showListNotification(NotificationDTOs);
                    }
                } else {
                    Toast.makeText(getActivity(), "Lấy thông tin thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<NotificationDTO>> call, Throwable t) {
                Log.e("Retrofit", "onFailure", t);
                Toast.makeText(getActivity(), "Sevrer bị hỏng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void markNotification(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("key", null);
        token = "Bearer " + token;

        NotificationApiService.notificationApiService.seenAllNotification(token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String mess = response.body().string();
                        if(mess.equals("success")){
                            AdapterAction();
                            Toast.makeText(getActivity(), "Đã xem tất cả thông báo", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.putExtra("fragmentToShow", 2);  // Giả sử AccountFragment là 3
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            getActivity().getSupportFragmentManager().popBackStack();
                        }
                    } catch (IOException e) {
                        Toast.makeText(getActivity(), "Đánh dấu thất bại", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "Đánh dấu thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Retrofit", "onFailure", t);
                Toast.makeText(getActivity(), "Sevrer bị hỏng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showListNotification(List<NotificationDTO> listNotification){
        if (listNotification.isEmpty()) {
            no_notification.setVisibility(View.VISIBLE);
        }
        else {
            Collections.sort(listNotification, new Comparator<NotificationDTO>() {
                @Override
                public int compare(NotificationDTO o1, NotificationDTO o2) {
                    return o2.getId() - o1.getId();
                }
            });

            NotificationAdapter NotificationAdapter = new NotificationAdapter(listNotification);
            recyclerView.setAdapter(NotificationAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }
}
