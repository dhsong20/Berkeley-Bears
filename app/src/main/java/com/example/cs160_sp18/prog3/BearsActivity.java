package com.example.cs160_sp18.prog3;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BearsActivity extends AppCompatActivity {

    private ArrayList<bearObject> bearObjects = new ArrayList<>();
    public BearAdapter adapter;
    Toolbar myToolBar;
    private String username;

    //location variables
    private Location currLocation;
    private MyLocation myLocation = new MyLocation();
    private static final String TAG = "BearsActivity";
    private Context mContext = this;
    private boolean run = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bears_activity_layout);

        Bundle extras = getIntent().getExtras();
        this.username = extras.getString("username_text");

        myToolBar = (Toolbar) findViewById(R.id.bear_toolbar);
        setSupportActionBar(myToolBar);


        locationFinder();



    }

    public void updateLoc_action(View view) {

        bearObjects.clear();
        locationFinder();
        adapter.notifyDataSetChanged();


    }

    private void locationFinder() {
        MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {
            @Override
            public void gotLocation(Location location) {

                Log.d(TAG, "gotLocation: getting location");
                currLocation = location;
                initBears();

            }
        };
        myLocation.getLocation(this, locationResult);
    }


    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new BearAdapter(this, bearObjects, this.username);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initBears(){
        int bearCount = 0;
        while (bearCount < 8) {
            switch (bearCount) {
                case 0:
                    bearObject bear0 = new bearObject(R.drawable.bell_bears, "Great Bear Bell Bears", 37.872061599999995, -122.2578123,0);
                    bearObjects.add(bear0);
                    break;
                case 1:
                    bearObject bear1 = new bearObject(R.drawable.bench_bears, "Campanile Esplanade Bears", 37.87233810000001, -122.25792999999999, 1);
                    bearObjects.add(bear1);
                    break;
                case 2:
                    bearObject bear2 = new bearObject(R.drawable.les_bears, "Les Bears", 37.871707, -122.253602, 2);
                    bearObjects.add(bear2);
                    break;
                case 3:
                    bearObject bear3 = new bearObject(R.drawable.macchi_bears, "Macchi Bears", 37.874118, -122.258778, 3);
                    bearObjects.add(bear3);
                    break;
                case 4:
                    bearObject bear4 = new bearObject(R.drawable.mlk_bear, "Class of 1927 Bear", 37.869288, -122.260125, 4);
                    bearObjects.add(bear4);
                    break;
                case 5:
                    bearObject bear5 = new bearObject(R.drawable.outside_stadium, "Stadium Entrance Bear", 37.871305, -122.252516, 5);
                    bearObjects.add(bear5);
                    break;
                case 6:
                    bearObject bear6 = new bearObject(R.drawable.south_hall, "South Hall Little Bear", 37.871382, -122.258355, 6);
                    bearObjects.add(bear6);
                    break;
                case 7:
                    bearObject bear7 = new bearObject(R.drawable.strawberry_creek, "Strawberry Creek Topiary Bear", 37.869861, -122.261148, 7);
                    bearObjects.add(bear7);
                    break;
            }
            bearCount += 1;
        }
        initRecyclerView();
    }

    public class bearObject {

        int imageID;
        String imageTitle;
        Location bearLocation = new Location(LocationManager.GPS_PROVIDER);
        Location userLocation = currLocation;
        double imageLAT;
        double imageLNG;
        int position;

        public bearObject(int image_id, String image_name, double latitude, double longitude, int pos) {
            imageID = image_id;
            imageTitle = image_name;
            imageLNG = longitude;
            imageLAT = latitude;
            bearLocation.setLongitude(longitude);
            bearLocation.setLatitude(latitude);
            position = pos;
        }
    }
}

