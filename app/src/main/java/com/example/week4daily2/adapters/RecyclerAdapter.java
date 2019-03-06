package com.example.week4daily2.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week4daily2.R;
import com.example.week4daily2.model.user.User;
import com.example.week4daily2.ui.DetailsActivity;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<User> arrayList;

    public RecyclerAdapter(ArrayList<User> users) {
        this.arrayList = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final User user = arrayList.get(i);
        viewHolder.userName.setText(user.getResults().get(0).getName().getFullName());
        viewHolder.userPhone.setText(user.getResults().get(0).getPhone());
        viewHolder.userEmail.setText(user.getResults().get(0).getEmail());

        Glide.with(viewHolder.itemView.getContext())
                .load(user.getResults().get(0).getPicture().getMedium())
                .centerCrop()
                .into(viewHolder.userImage);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                // For some reason put parcealable throws a NPE
                // bundle.putParcelable("user", arrayList.get(i));
                bundle.putString("name",user.getResults().get(0).getName().getFullName());
                bundle.putString("phone",user.getResults().get(0).getPhone());
                bundle.putString("email",user.getResults().get(0).getEmail());
                bundle.putString("time",user.getResults().get(0).getLocation().getTimezone().getAll());
                bundle.putString("address",user.getResults().get(0).getLocation().getFullAddress());
                bundle.putString("image",user.getResults().get(0).getPicture().getLarge());
                Log.d("Log.d", "Size: " + arrayList.size());
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, userEmail, userPhone;
        ImageView userImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            userEmail = itemView.findViewById(R.id.userEmail);
            userPhone = itemView.findViewById(R.id.userPhone);
            userImage = itemView.findViewById(R.id.userImage);
        }
    }
}
