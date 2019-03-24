package com.example.cs160_sp18.prog3;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class custom_OnClickListener implements View.OnClickListener {

    double variable;
    Context context;

    public custom_OnClickListener(double variable, Context context) {
        this.variable = variable;
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        return;

    }
}
