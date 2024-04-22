package com.example.bookingroom.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingroom.R;
import com.example.bookingroom.api.BookingApiService;
import com.example.bookingroom.adapter.BookingAdapter;
import com.example.bookingroom.model.BookingDTO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleFragment extends Fragment {

    private RecyclerView recyclerView;
    TextView noneSchedule;

    public ScheduleFragment(){
        // require a empty public constructor
    }

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        noneSchedule = view.findViewById(R.id.none_schedule);
        recyclerView = view.findViewById(R.id.list_schedule);
        AdapterAction();
        return view;
    }

    private void AdapterAction(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("key", null);
        token = "Bearer " + token;

        BookingApiService.bookingApiService.getAllBooking(token).enqueue(new Callback<List<BookingDTO>>() {
            @Override
            public void onResponse(Call<List<BookingDTO>> call, Response<List<BookingDTO>> response) {
                if (response.isSuccessful()) {
                    List<BookingDTO> BookingDTOs = response.body();
                    if (BookingDTOs != null) {
                        showListBooking(BookingDTOs);
                    }
                } else {
                    Toast.makeText(getActivity(), "Lấy thông tin thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BookingDTO>> call, Throwable t) {
                Log.e("Retrofit", "onFailure", t);
                Toast.makeText(getActivity(), "Sevrer bị hỏng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showListBooking(List<BookingDTO> listBooking){
        if (listBooking.isEmpty()) {
            noneSchedule.setVisibility(View.VISIBLE);
        }
        else {
            Collections.sort(listBooking, new Comparator<BookingDTO>() {
                @Override
                public int compare(BookingDTO o1, BookingDTO o2) {
                        return o2.getBookedRoomDTOs().get(0).getCheckIn()
                                .compareTo(o1.getBookedRoomDTOs().get(0).getCheckIn());
                }
            });

            BookingAdapter BookingAdapter = new BookingAdapter(listBooking);
            recyclerView.setAdapter(BookingAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }
}
