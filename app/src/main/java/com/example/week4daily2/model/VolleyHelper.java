package com.example.week4daily2.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.week4daily2.model.user.User;
import com.google.gson.Gson;

import org.json.JSONObject;

public class VolleyHelper {
    public static final String URL = "https://randomuser.me/api/?results=1";

    private Context context;
    private RequestQueue requestQueue;

    public VolleyHelper(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public void getRandomUserFromApi(final VolleyCallback volleyCallback) {
        final User[] userFromServer = {new User()};

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // If there was an error in the response
                if (response == null) {
                    Toast.makeText(context, "There was an error with the response", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    String json = response.toString();
                    userFromServer[0] = new Gson().fromJson(json, User.class);
                    volleyCallback.onSuccess(userFromServer[0]);
                } catch (Exception e) {
                    Log.d("Log.d", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "There was an error with the response", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(objectRequest);
    }

    // This interface helps to pass the User Object back from onResponse
    public interface VolleyCallback{
        void onSuccess(User user);
    }
}
