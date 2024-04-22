package com.example.bookingroom.adapter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookingroom.R;
import com.example.bookingroom.model.BookedRoomDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookedRoomAdapter extends RecyclerView.Adapter<BookedRoomAdapter.BookedRoomViewHolder> {
    private List<BookedRoomDTO> bookedRooms;
    private List<BookedRoomDTO> updateBookedRooms;

    public BookedRoomAdapter(List<BookedRoomDTO> bookedRooms){
        this.bookedRooms = bookedRooms;
        this.updateBookedRooms = new ArrayList<>(bookedRooms);
    }

    public List<BookedRoomDTO> getbookedRooms() {
        return updateBookedRooms;
    }

    @NonNull
    @Override
    public BookedRoomAdapter.BookedRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_change_view, parent, false);
        return new BookedRoomAdapter.BookedRoomViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return bookedRooms.size();
    }

    @Override
    public void onBindViewHolder(@NonNull BookedRoomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BookedRoomDTO bookedRoom = bookedRooms.get(position);
        Glide.with(holder.itemView.getContext())
                .load("http://192.168.0.12:8080/image/rooms/" + bookedRoom.getRoomDTO().getImage())
                .into(holder.image);
        holder.nameRoom.setText("Phòng: " + bookedRoom.getRoomDTO().getName());
        holder.checkIn.setText(bookedRoom.getCheckIn());
        holder.checkOut.setText(bookedRoom.getCheckOut());
        holder.checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(holder.checkIn, v, position, "checkin");
            }
        });

        holder.checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(holder.checkOut, v, position, "checkout");
            }
        });
    }

    class BookedRoomViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView nameRoom;
        EditText checkIn, checkOut;

        public BookedRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_hotel_booking);
            checkIn = itemView.findViewById(R.id.date_check_in);
            checkOut = itemView.findViewById(R.id.date_check_out);
            nameRoom = itemView.findViewById(R.id.name_room_booking);
        }
    }

    private void showDatePickerDialog(TextView editText, View v, int position, String type) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), R.style.DatePickerTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date;
                        if(month < 9) {
                            date = year + "-0" + (month + 1) + "-" + dayOfMonth;
                        }else{
                            date = year + "-" + (month + 1) + "-" + dayOfMonth;
                        }
                        editText.setText(date);
                        if(type.equals("checkin")){
                            updateBookedRooms.get(position).setCheckIn(date);
                        }
                        else updateBookedRooms.get(position).setCheckOut(date);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }
}
