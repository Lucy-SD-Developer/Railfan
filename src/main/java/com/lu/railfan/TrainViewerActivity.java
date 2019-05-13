package com.lu.railfan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lu.railfan.databinding.ActivityTrainViewerBinding;

import android.databinding.DataBindingUtil;
import android.view.View;

import static com.lu.railfan.TrainAdapter.*;

public class TrainViewerActivity extends AppCompatActivity {

    private ActivityTrainViewerBinding binding;
    private String trainName;
    private int numFuelTender;
    private int numBrakeTender;
    private int numWaterTank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_train_viewer);

        Intent intent = getIntent();
        trainName = intent.getStringExtra(TRAIN_NAME);
        numFuelTender = intent.getIntExtra(NUM_FUEL_TENDER, 0);
        numBrakeTender = intent.getIntExtra(NUM_BRAKE_TENDER, 0);
        numWaterTank = intent.getIntExtra(NUM_WATER_TANK, 0);

        binding.toolbar.setTitle(trainName);

        setSupportActionBar(binding.toolbar);

        binding.lblNumFuel.setText(Integer.toString(numFuelTender));
        binding.lblNumBrake.setText(Integer.toString(numBrakeTender));
        binding.lblNumTank.setText(Integer.toString(numWaterTank));

        binding.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TrainMapActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
