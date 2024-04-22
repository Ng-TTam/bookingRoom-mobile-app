package com.example.bookingroom.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookingroom.R;
import com.example.bookingroom.activity.ChangeScheduleActivity;
import com.example.bookingroom.activity.RewardDetailActivity;
import com.example.bookingroom.api.NotificationApiService;
import com.example.bookingroom.model.NotificationDTO;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    List<NotificationDTO> listNotification;

    public NotificationAdapter(List<NotificationDTO> listNotification){
        this.listNotification = listNotification;
    }

    @NonNull
    @Override
    public NotificationAdapter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_tag, parent, false);
        return new NotificationAdapter.NotificationViewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.NotificationViewHolder holder, int position) {
        NotificationDTO notification = listNotification.get(position);

        holder.type.setText(notification.getType());
        holder.dateCreate.setText(notification.getDateCreate());
        holder.title.setText(notification.getTitle());
        holder.content.setText(notification.getContent());
        if(notification.getType().equals("Mã giảm")){
            Glide.with(holder.itemView.getContext())
                    .load("http://192.168.0.12:8080/image/discounts/" + notification.getImage())
                    .into(holder.image);
        }else {
            Glide.with(holder.itemView.getContext())
                    .load("http://192.168.0.12:8080/image/hotels/" + notification.getImage())
                    .into(holder.image);
        }

        if(!notification.getSeen()){
            holder.itemView.setBackgroundResource(R.drawable.highlight_notification);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notification.getType().equals("Mã giảm")){
                    markSeenNotification(notification.getId());
                    Intent intent = new Intent(v.getContext(), RewardDetailActivity.class);
                    intent.putExtra("BookingId", notification.getObjectId());
                    v.getContext().startActivity(intent);
                }else{

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNotification.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView type,dateCreate,title,content;

        public NotificationViewHolder(@NonNull View itemView, ImageView image, TextView type,
                               TextView dateCreate, TextView title, TextView content) {
            super(itemView);
            this.image = image;
            this.type = type;
            this.dateCreate = dateCreate;
            this.title = title;
            this.content = content;
        }

        @SuppressLint("WrongViewCast")
        public NotificationViewHolder(@NonNull View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.image_notification);
            type = itemView.findViewById(R.id.type);
            dateCreate = itemView.findViewById(R.id.date_create);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }
    }

    private void markSeenNotification(int id){
        NotificationApiService.notificationApiService.seenNotification(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        String mess = response.body().string();
                        if(mess.equals("seen")){
                            Log.e("Mark Notification", "Success");
                        }
                    } catch (IOException e) {
                        Log.e("Mark Notification", "Some things wrong");
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error", String.valueOf(t));
            }
        });
    }
}
