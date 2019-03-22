package com.example.cs160_sp18.prog3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Bears_Page extends AppCompatActivity {

    private String username;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private int[] bear_pics = {R.drawable.bell_bears,
                                R.drawable.bench_bears,
                                R.drawable.in_stadium,
                                R.drawable.les_bears,
                                R.drawable.macchi_bears,
                                R.drawable.mlk_bear,
                                R.drawable.outside_stadium,
                                R.drawable.south_hall,
                                R.drawable.strawberry_creek};

    private RecyclerAdapater adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bears_page_layout);

        Intent startIntent = getIntent();
        username = startIntent.getStringExtra("username_text");

        recyclerView = findViewById(R.id.bears_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapater(bear_pics);
        recyclerView.setAdapter(adapter);
    }







}
