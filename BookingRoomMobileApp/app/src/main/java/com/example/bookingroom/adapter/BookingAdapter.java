package com.example.bookingroom.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookingroom.activity.ChangeScheduleActivity;
import com.example.bookingroom.model.BookedRoomDTO;
import com.example.bookingroom.model.BookingDTO;
import com.example.bookingroom.R;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    private List<BookingDTO> listBooking;

    public BookingAdapter(List<BookingDTO> listBooking) {
        this.listBooking = listBooking;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_detail, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listBooking.size();
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        BookingDTO booking = listBooking.get(position);
        Glide.with(holder.itemView.getContext())
                .load("http://192.168.0.12:8080/image/hotels/" + booking.getHotelDTO().getImage())
                .into(holder.image);
        holder.name.setText(booking.getHotelDTO().getName());
        holder.address.setText(booking.getHotelDTO().getAddress());
        List<BookedRoomDTO> bookedRoomDTOs = booking.getBookedRoomDTOs();
        Collections.sort(bookedRoomDTOs, new Comparator<BookedRoomDTO>() {
            @Override
            public int compare(BookedRoomDTO o1, BookedRoomDTO o2) {
                String checkin1 = o1.getCheckIn();
                String checkin2 = o2.getCheckIn();
                String checkout1 =o1.getCheckOut();
                String checkout2 = o2.getCheckOut();
                if (!checkin1.equals(checkin2)) {
                    return checkin1.compareTo(checkin2);
                }
                // Nếu checkin giống nhau, sắp xếp theo checkout
                return checkout2.compareTo(checkout1);
            }
        });
        holder.checkInOut.setText(bookedRoomDTOs.get(0).getCheckIn()
                + " - " + bookedRoomDTOs.get(bookedRoomDTOs.size() - 1).getCheckOut());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String date;
        if(month < 9) date = year + "-0" + (month + 1) + "-" + day;
        else date = year + "-" + (month + 1) + "-" + day;
        if(date.compareTo(bookedRoomDTOs.get(0).getCheckIn()) < 0 ){
            holder.change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ChangeScheduleActivity.class);
                    intent.putExtra("BookingId", booking.getId());
                    v.getContext().startActivity(intent);
                }
            });
        }
        else{
            holder.change.setVisibility(View.GONE);
        }
    }

    class BookingViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, address, checkInOut;
        ImageButton change;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_hotel);
            name = itemView.findViewById(R.id.name_hotel);
            address = itemView.findViewById(R.id.address);
            checkInOut = itemView.findViewById(R.id.check_in_out);
            change = itemView.findViewById(R.id.btn_change);
        }
    }

}
