package com.example.cs160_sp18.prog3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Bears_Page extends AppCompatActivity {

    TextView testingTV = (TextView) findViewById(R.id.testing);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Intent startIntent = getIntent();
        String username = startIntent.getStringExtra("username_text");


        testingTV.setText(username);
    }







}
