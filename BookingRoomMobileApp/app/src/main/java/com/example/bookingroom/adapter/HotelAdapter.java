package com.example.bookingroom.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookingroom.model.HotelDTO;
import com.example.bookingroom.R;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {
    List<HotelDTO> listHotel;

    public HotelAdapter(List<HotelDTO> listHotel){
        this.listHotel = listHotel;
    }

    @NonNull
    @Override
    public HotelAdapter.HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull HotelAdapter.HotelViewHolder holder, int position) {
        HotelDTO hotel = listHotel.get(position);
        Glide.with(holder.itemView.getContext())
                .load(hotel.getImage())
                .into(holder.image);
        holder.name.setText(hotel.getName());
        holder.address.setText(hotel.getAddress());
    }

    @Override
    public int getItemCount() {
        return listHotel.size();
    }

    class HotelViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name,address,checkOut;

        public HotelViewHolder(@NonNull View itemView, ImageView image, TextView name,
                                  TextView address, TextView checkOut) {
            super(itemView);
            this.image = image;
            this.name = name;
            this.address = address;
            this.checkOut = checkOut;
        }

        @SuppressLint("WrongViewCast")
        public HotelViewHolder(@NonNull View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.img_hotel);
            name = itemView.findViewById(R.id.name_hotel);
            address = itemView.findViewById(R.id.address);
            checkOut = itemView.findViewById(R.id.check_in_out);
        }
    }

}
