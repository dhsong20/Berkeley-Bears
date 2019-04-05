package com.example.cs160_sp18.prog3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private String username;
    //UI elements
    EditText username_text;
    Button login_button;


    private static final String TAG = "MainACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page_layout);


        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreate: asking for permission");
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        } else {
            Log.d(TAG, "onCreate: permission granted already");
        }


        //hook up UI elements
        login_button = findViewById(R.id.login_button_view);
        username_text = findViewById(R.id.username_view);

        username_text.addTextChangedListener(login_textWatcher);
        checkFieldsForEmptyValues();

        login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent newIntent = new Intent(LoginActivity.this, BearsActivity.class);
                newIntent.putExtra("username_text", username_text.getText().toString());
                startActivity(newIntent);
            }
        });


    }

    private TextWatcher login_textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            checkFieldsForEmptyValues();
        }
    };

    private void checkFieldsForEmptyValues() {
        String et1 = username_text.getText().toString();
        if (et1.equals("")) {
            login_button.setEnabled(false);
        } else {
            login_button.setEnabled(true);
        }
    }


}
