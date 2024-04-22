package com.example.bookingroom.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookingroom.R;
import com.example.bookingroom.adapter.BookedRoomAdapter;
import com.example.bookingroom.adapter.DiscountAdapter;
import com.example.bookingroom.api.BookingApiService;
import com.example.bookingroom.model.BookedRoomDTO;
import com.example.bookingroom.model.BookingDTO;
import com.example.bookingroom.model.DiscountDTO;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeScheduleActivity extends AppCompatActivity {
    Button btnChange;
    RecyclerView recyclerView;
    BookedRoomAdapter bookedRoomAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_schedule);

        TextView nameHotel = findViewById(R.id.name_hotel_booking);
        TextView address = findViewById(R.id.address_hotel_booking);

        ImageButton back = findViewById(R.id.buttonBack);
        back.setOnClickListener(v -> {
            finish();
        });

        Intent intent = getIntent();
        if (intent != null) {
            int bookingId = getIntent().getIntExtra("BookingId", -1);
            Log.e("ID", String.valueOf(bookingId));
            if (bookingId != -1) {
                SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
                String token = sharedPreferences.getString("key", null);
                token = "Bearer " + token;

                BookingApiService.bookingApiService.getBookingById(token, bookingId).enqueue(new Callback<BookingDTO>() {
                    @Override
                    public void onResponse(Call<BookingDTO> call, Response<BookingDTO> response) {
                        if(response.isSuccessful()){
                            BookingDTO booking = response.body();
                            nameHotel.setText(booking.getHotelDTO().getName());
                            address.setText(booking.getHotelDTO().getAddress());
                            showListBookedRoom(booking.getBookedRoomDTOs());

                            List<BookedRoomDTO> bookedRooms = bookedRoomAdapter.getbookedRooms();

                            // btn change action
                            btnChange = findViewById(R.id.button_change_schedule);
                            btnChange.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String content = "";
                                    for(BookedRoomDTO bookedRoomDTO: booking.getBookedRoomDTOs()){
                                        content += "Phòng " + bookedRoomDTO.getRoomDTO().getName()
                                                + ": " + bookedRoomDTO.getCheckIn() + " - " + bookedRoomDTO.getCheckOut() + "\n";
                                    }
                                    Log.e("CONTEN", content);
                                    if(validateInput(bookedRooms)){
                                        booking.setBookedRoomDTOs(bookedRooms);
                                        changeSchedule(booking);
                                        btnChange.setEnabled(false);
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<BookingDTO> call, Throwable t) {
                        Log.e("Retrofit", "onFailure", t);
                        Toast.makeText(ChangeScheduleActivity.this, "Sevrer bị hỏng", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else {
            Toast.makeText(ChangeScheduleActivity.this, "Intent không giá trị", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInput(List<BookedRoomDTO> bookedRooms) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String today = year + "-" + (month + 1) + "-" + day;

        for(int i = 0; i < bookedRooms.size(); i++) {
            if (bookedRooms.get(i).getCheckIn().compareTo(bookedRooms.get(i).getCheckOut()) >= 0) {
                Toast.makeText(ChangeScheduleActivity.this, "Ngày check-in phòng " + (i + 1) + " phải nhỏ hơn ngày check-out", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (bookedRooms.get(i).getCheckIn().compareTo(today) <= 0) {
                Toast.makeText(ChangeScheduleActivity.this, "Ngày check-in phòng " + (i + 1) + " phải lớn hơn ngày hiện tại", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void showListBookedRoom(List<BookedRoomDTO> bookedRooms){
        recyclerView = findViewById(R.id.list_bookedRoom);
        bookedRoomAdapter = new BookedRoomAdapter(bookedRooms);
        recyclerView.setAdapter(bookedRoomAdapter);
        recyclerView.setHorizontalScrollBarEnabled(true);
        if(bookedRooms.size() == 1) recyclerView.setScrollBarFadeDuration(1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);

    }

    private void changeSchedule(BookingDTO booking) {
        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("key", null);
        token = "Bearer " + token;

        BookingApiService.bookingApiService.updateBooking(token, booking).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String mess = response.body().string();
                        if(mess.equals("update success")){
                            Toast.makeText(ChangeScheduleActivity.this, mess, Toast.LENGTH_SHORT).show();
                            //?
                            sendNotification(booking);

                            Intent intent = new Intent(ChangeScheduleActivity.this, MainActivity.class);
                            intent.putExtra("fragmentToShow", 1);  // Giả sử AccountFragment là 3
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    Toast.makeText(ChangeScheduleActivity.this, "Không thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Retrofit", "onFailure", t);
                Toast.makeText(ChangeScheduleActivity.this, "Sevrer bị hỏng", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendNotification(BookingDTO booking) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        String CHANNEL_ID = "room_booking_channel";

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence channelName = "Room Booking Notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            notificationChannel.setDescription("Notifications for room booking changes");
            notificationManager.createNotificationChannel(notificationChannel);
        }

        String content = "";
        for(BookedRoomDTO bookedRoomDTO: booking.getBookedRoomDTOs()){
            content += "Phòng " + bookedRoomDTO.getRoomDTO().getName()
                    + ": " + bookedRoomDTO.getCheckIn() + " - " + bookedRoomDTO.getCheckOut() + "\n";
        }
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle("Thay đổi lịch trình thành công")
                    .setContentText(content)
                    .setSmallIcon(R.drawable.icon_app)
                    .setLargeIcon(bitmap)
                    .setAutoCancel(true)
                    .build();
        }
        // Gửi thông báo
        notificationManager.notify(0, notification);
    }

}
