package com.example.bookingroom.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookingroom.activity.RewardDetailActivity;
import com.example.bookingroom.model.DiscountDTO;
import com.example.bookingroom.R;

import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder> {
    private List<DiscountDTO> listDiscount;

    public DiscountAdapter(List<DiscountDTO> listDiscount) {
        this.listDiscount = listDiscount;
    }

    @NonNull
    @Override
    public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reward_view, parent, false);
        return new DiscountViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listDiscount.size();
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {
        DiscountDTO discount = listDiscount.get(position);
        Glide.with(holder.itemView.getContext())
                .load("http://192.168.0.12:8080/image/discounts/" + discount.getImage())
                .into(holder.image);
        holder.name.setText(discount.getHotelDTO().getName());
        holder.address.setText(discount.getHotelDTO().getAddress());
        holder.outOfDate.setText(discount.getOutOfDate().toString());
        if (discount.getType().equals("Percentage")) {
            holder.reducePrice.setText("Giảm " + discount.getReducedPrice() + "%");
        } else {
            holder.reducePrice.setText("Giảm " + discount.getReducedPrice() + "K");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RewardDetailActivity.class);
                // Truyền dữ liệu tới RewardActivity, ví dụ:
                intent.putExtra("discountId", discount.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    class DiscountViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, address, outOfDate, reducePrice;

        public DiscountViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            outOfDate = itemView.findViewById(R.id.out_of_date);
            reducePrice = itemView.findViewById(R.id.reduce_price);
        }
    }
}
