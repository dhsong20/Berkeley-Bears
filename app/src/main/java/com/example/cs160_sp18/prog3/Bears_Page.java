package com.example.cs160_sp18.prog3;


import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;




public class Bears_Page extends AppCompatActivity {

    private String username;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Toolbar myToolBar;


    private int[] bear_pics = {R.drawable.bell_bears,
            R.drawable.bench_bears,
            R.drawable.in_stadium,
            R.drawable.les_bears,
            R.drawable.macchi_bears,
            R.drawable.mlk_bear,
            R.drawable.outside_stadium,
            R.drawable.south_hall,
            R.drawable.strawberry_creek};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bears_page_layout);
        myToolBar = (Toolbar) findViewById(R.id.bear_toolbar);
        setSupportActionBar(myToolBar);
        this.username = getIntent().getStringExtra("username_text");

        recyclerView = findViewById(R.id.bears_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerAdapter(bear_pics, this);
        recyclerView.setAdapter(mAdapter);

    }
}








