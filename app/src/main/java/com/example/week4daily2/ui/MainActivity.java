package com.example.week4daily2.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.week4daily2.R;
import com.example.week4daily2.adapters.RecyclerAdapter;
import com.example.week4daily2.model.VolleyHelper;
import com.example.week4daily2.model.user.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements VolleyHelper.VolleyCallback{
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ArrayList<User> users;
    VolleyHelper volleyHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new ArrayList<>();
        recyclerView = findViewById(R.id.mainRecycler);
        recyclerAdapter = new RecyclerAdapter(users);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        volleyHelper = new VolleyHelper(getApplicationContext());
        getListOfUsersFromApi();
    }

    public ArrayList<User> getListOfUsersFromApi() {
        final ArrayList<User> list = new ArrayList<>();
        int count = 0;
        while (count < 10) {
            count++;
            volleyHelper.getRandomUserFromApi(this);
        }

        return list;
    }

    @Override
    public void onSuccess(User user) {
        users.add(user);
        recyclerAdapter.notifyDataSetChanged();
        Log.d("Log.d", String.valueOf(recyclerAdapter.getItemCount()));
    }
}
