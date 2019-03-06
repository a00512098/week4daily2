package com.example.week4daily2.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week4daily2.R;
import com.example.week4daily2.model.user.User;

public class DetailsActivity extends AppCompatActivity {
    ImageView userImage;
    TextView userName, userPhone, userEmail, userTimeZone, userAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initViews();

        Intent intent = getIntent();
        Bundle bundle;
//        User user = new User();

        // Sorry for this but the parcealable threw a NPE
        // I tried to solve it for hours and came to the conclusion that
        // http://www.jsonschema2pojo.org/ is the one to blame.
        if (intent != null) {
            bundle = intent.getExtras();
            String name = bundle.getString("name");
            String phone = bundle.getString("phone");
            String email = bundle.getString("email");
            String time = bundle.getString("time");
            String address = bundle.getString("address");
            String image = bundle.getString("image");

            userName.setText(name);
            userPhone.setText(String.format(getResources().getString(R.string.phone_s), phone));
            userEmail.setText(String.format(getResources().getString(R.string.email_s), email));
            userTimeZone.setText(String.format(getResources().getString(R.string.time_zone_s), time));
            userAddress.setText(String.format(getResources().getString(R.string.address_s), address));

            Glide.with(this)
                    .load(image)
                    .centerCrop()
                    .into(userImage);
        }

//        populateViews(user);
    }
    // ---------------
    // I leave this here to show my previous work
    // ---------------
//    private void populateViews(User user) {
//        userName.setText(user.getResults().get(0).getName().getFullName());
//        userPhone.setText(user.getResults().get(0).getPhone());
//        userEmail.setText(user.getResults().get(0).getEmail());
//        userTimeZone.setText(user.getResults().get(0).getLocation().getTimezone().getAll());
//        userAddress.setText(user.getResults().get(0).getLocation().getFullAddress());
//
//        Glide.with(this)
//                .load(user.getResults().get(0).getPicture().getLarge())
//                .centerCrop()
//                .into(userImage);
//    }

    private void initViews() {
        userImage = findViewById(R.id.userImage);
        userName = findViewById(R.id.userName);
        userPhone = findViewById(R.id.userPhone);
        userEmail = findViewById(R.id.userEmail);
        userTimeZone = findViewById(R.id.userTimeZone);
        userAddress = findViewById(R.id.userAddress);
    }
}
