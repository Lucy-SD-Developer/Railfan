package com.lu.railfan;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.model.PolylineOptions;
import com.lu.railfan.databinding.ActivityTrainMapBinding;
import com.lu.railfan.model.train.BaseTrain;

import android.databinding.DataBindingUtil;
import android.view.View;

import static com.lu.railfan.TrainAdapter.*;

public class TrainMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double initLat = -34;
    private double initLong = 151;
    private ActivityTrainMapBinding binding;
    private BaseTrain train;
    private Marker marker;

    enum Direction {
        East,
        West,
        North,
        South
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_train_map);

        // Load the intent and rebuild the train object
        Intent intent = getIntent();
        String trainName = intent.getStringExtra(TRAIN_NAME);
        int numFuelTender = intent.getIntExtra(NUM_FUEL_TENDER, 0);
        int numBrakeTender = intent.getIntExtra(NUM_BRAKE_TENDER, 0);
        int numWaterTank = intent.getIntExtra(NUM_WATER_TANK, 0);

        train = BaseTrain.buildTrain(numFuelTender, numBrakeTender, numWaterTank);
        train.name = trainName;
        train.latitude = initLat;
        train.longitude = initLong;

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng trainPos = new LatLng(train.latitude, train.longitude);
        marker = mMap.addMarker(new MarkerOptions().position(trainPos).title("Current train position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(trainPos));

        // Set up button click handler
        binding.btnCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LatLng trainPos = new LatLng(train.latitude, train.longitude);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(trainPos));
            }
        });

        binding.btnWest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTrain(Direction.West);
            }
        });

        binding.btnEast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTrain(Direction.East);
            }
        });

        binding.btnNorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTrain(Direction.North);
            }
        });

        binding.btnSouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTrain(Direction.South);
            }
        });
    }

    private void moveTrain(Direction direction) {
        double oldLatitude = train.latitude;
        double oldLongitude = train.longitude;
        switch (direction) {
            case East:
                train.moveEast();
                break;
            case West:
                train.moveWest();
                break;
            case North:
                train.moveNorth();
                break;
            case South:
                train.moveSouth();
                break;
        }
        marker.remove();
        LatLng trainPos = new LatLng(train.latitude, train.longitude);
        marker = mMap.addMarker(new MarkerOptions().position(trainPos).title("Current train position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(trainPos));
        mMap.addPolyline(new PolylineOptions().add(new LatLng(oldLatitude, oldLongitude), new LatLng(train.latitude, train.longitude))
                .width(5).color(Color.RED));
    }
}
