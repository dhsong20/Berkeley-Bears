package com.example.cs160_sp18.prog3;

import android.content.Context;
import android.view.View;

public class custom_OnClickListener implements View.OnClickListener {

    int distanceBTW;
    Context context;
    String username;
    String landmark_name;

    public custom_OnClickListener(int distance, Context context, String username, String landmark_name){
        this.distanceBTW = distance;
        this.context = context;
        this.username = username;
        this.landmark_name = landmark_name;
    }

    @Override
    public void onClick(View v) {
        return;
    }
}
