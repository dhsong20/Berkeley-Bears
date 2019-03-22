package com.example.cs160_sp18.prog3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login_Page extends AppCompatActivity {

    EditText username_text;
    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page_layout);

        login_button = findViewById(R.id.login_button_view);

        username_text = (EditText) findViewById(R.id.username_view);

        username_text.addTextChangedListener(login_textWatcher);
        checkFieldsForEmptyValues();




//        // intent for bear page
//        Intent bear_Intent = new Intent(this, Bears_Page.class);
//        bear_Intent.putExtra("username_text", username_text.getText().toString());
//
//        login_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(bear_Intent);
//            }
//        });

    }

    void checkFieldsForEmptyValues() {


        String et1 = username_text.getText().toString();

        if (et1.equals("")) {
            login_button.setEnabled(false);
        } else {
            login_button.setEnabled(true);
        }
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

    public void login(View view){
        Intent newIntent = new Intent(this, Bears_Page.class);
        newIntent.putExtra("username_text", username_text.getText().toString());
        startActivity(newIntent);
    }




}
