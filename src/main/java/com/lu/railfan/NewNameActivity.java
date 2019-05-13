package com.lu.railfan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lu.railfan.database.DBManager;

import com.lu.railfan.databinding.ActivityNewNameBinding;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.Toast;

import static com.lu.railfan.TrainAdapter.*;

public class NewNameActivity extends AppCompatActivity {

    private ActivityNewNameBinding binding;
    private int numFuelTender;
    private int numBrakeTender;
    private int numWaterTank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_name);

        Intent intent = getIntent();
        numFuelTender = intent.getIntExtra(NUM_FUEL_TENDER, 0);
        numBrakeTender = intent.getIntExtra(NUM_BRAKE_TENDER, 0);
        numWaterTank = intent.getIntExtra(NUM_WATER_TANK, 0);

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String trainName = binding.txtName.getText().toString();
                // Validate
                if (trainName.trim().length() == 0) {
                    Toast.makeText(view.getContext(), "Your train must have a name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBManager dbManager = new DBManager(NewNameActivity.this);
                dbManager.open();

                dbManager.createTrain(trainName, numFuelTender, numBrakeTender, numWaterTank);
                dbManager.close();

                // Go back to main
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
