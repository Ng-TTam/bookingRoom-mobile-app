package com.example.bookingroom.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookingroom.R;
import com.example.bookingroom.activity.ExchangeRewardActivity;
import com.example.bookingroom.activity.RewardDetailActivity;
import com.example.bookingroom.api.DiscountApiService;
import com.example.bookingroom.model.BookingDTO;
import com.example.bookingroom.model.DiscountDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscountGetAdapter extends RecyclerView.Adapter<DiscountGetAdapter.DiscountGetViewHolder> {
    List<DiscountDTO> listDiscount;
    SharedPreferences sharedPreferences;
    String token;
    TextView rewardPoint;

    public DiscountGetAdapter(List<DiscountDTO> listDiscount, TextView rewardPoint,
                              SharedPreferences sharedPreferences, String token) {
        this.listDiscount = listDiscount;
        this.sharedPreferences = sharedPreferences;
        this.token = token;
        this.rewardPoint = rewardPoint;
    }

    @NonNull
    @Override
    public DiscountGetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exchange_reward_get,parent,false);
        return new DiscountGetViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listDiscount.size();
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountGetAdapter.DiscountGetViewHolder holder, int position) {
        DiscountDTO discount = listDiscount.get(position);
        Glide.with(holder.itemView.getContext())
                .load("http://192.168.0.12:8080/image/discounts/" + discount.getImage())
                .into(holder.image);
        holder.name.setText(discount.getHotelDTO().getName());
        holder.address.setText(discount.getHotelDTO().getAddress());
        holder.outOfDate.setText(discount.getOutOfDate().toString());
        holder.rewardPoint.setText(String.valueOf(discount.getRewardPoint()));
        if(discount.getType().equals("Percentage"))// reduce by percent or amount
            holder.reducePrice.setText("Giảm " + discount.getReducedPrice() + "%");
        else holder.reducePrice.setText("Giảm " + discount.getReducedPrice() + "K");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RewardDetailActivity.class);
                intent.putExtra("discountId", discount.getId());
                v.getContext().startActivity(intent);
            }
        });

        holder.get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiscountApiService.discountApiService.exchangeDiscount(token, discount).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Boolean isExchange = response.body();
                        if(isExchange){
                            holder.get.setVisibility(View.GONE);
                            int point = Integer.valueOf(sharedPreferences.getString("rewardPoint", "0"))
                                    - Integer.valueOf(String.valueOf(holder.rewardPoint.getText()));
                            rewardPoint.setText(String.valueOf(point) + " điểm");
                            sharedPreferences.edit().putString("rewardPoint", String.valueOf(point));
                            Toast.makeText(v.getContext(), "Đổi thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(v.getContext(), "Không đủ điểm thưởng", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(v.getContext(), "Lỗi server", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    class DiscountGetViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name,address,outOfDate,reducePrice, rewardPoint;
        ImageButton get;

        public DiscountGetViewHolder(@NonNull View itemView, ImageView image, TextView name, TextView address,
                                   TextView outOfDate, TextView reducePrice, TextView rewardPoint, ImageButton get) {
            super(itemView);
            this.image = image;
            this.name = name;
            this.address = address;
            this.outOfDate = outOfDate;
            this.reducePrice = reducePrice;
            this.rewardPoint = rewardPoint;
            this.get = get;
        }

        @SuppressLint("WrongViewCast")
        public DiscountGetViewHolder(@NonNull View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.imageHotel);
            name = itemView.findViewById(R.id.nameHotel);
            address = itemView.findViewById(R.id.addressHotel);
            outOfDate = itemView.findViewById(R.id.outOfDate);
            reducePrice = itemView.findViewById(R.id.reducePriceHotel);
            rewardPoint = itemView.findViewById(R.id.rewardPointHotel);
            get = itemView.findViewById(R.id.get_discount);
        }
    }
}
