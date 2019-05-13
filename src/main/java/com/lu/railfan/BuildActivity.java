package com.lu.railfan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.lu.railfan.databinding.ActivityBuildBinding;

import android.databinding.DataBindingUtil;
import android.view.View;

import static com.lu.railfan.TrainAdapter.*;

public class BuildActivity extends AppCompatActivity {

    private ActivityBuildBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_build);

        setSupportActionBar(binding.toolbar);

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewNameActivity.class);
                intent.putExtra(NUM_FUEL_TENDER, getNumFuel());
                intent.putExtra(NUM_BRAKE_TENDER, getNumBrake());
                intent.putExtra(NUM_WATER_TANK, getNumTank());
                startActivity(intent);
            }
        });

        binding.btnMinusFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newNum = getNumFuel() - 1;
                newNum = newNum < 0 ? 0 : newNum;
                binding.txtFuel.setText(Integer.toString(newNum));
            }
        });

        binding.btnPlusFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newNum = getNumFuel() + 1;
                binding.txtFuel.setText(Integer.toString(newNum));
            }
        });

        binding.btnMinusBrake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newNum = getNumBrake() - 1;
                newNum = newNum < 0 ? 0 : newNum;
                binding.txtBrake.setText(Integer.toString(newNum));
            }
        });

        binding.btnPlusBrake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newNum = getNumBrake() + 1;
                binding.txtBrake.setText(Integer.toString(newNum));
            }
        });

        binding.btnMinusTank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newNum = getNumTank() - 1;
                newNum = newNum < 0 ? 0 : newNum;
                binding.txtTank.setText(Integer.toString(newNum));
            }
        });

        binding.btnPlusTank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newNum = getNumTank() + 1;
                binding.txtTank.setText(Integer.toString(newNum));
            }
        });
    }

    private int getNumFuel() {
        if (binding.txtFuel.getText().length() == 0) {
            return 0;
        }
        return Integer.parseInt(binding.txtFuel.getText().toString());
    }

    private int getNumBrake() {
        if (binding.txtBrake.getText().length() == 0) {
            return 0;
        }
        return Integer.parseInt(binding.txtBrake.getText().toString());
    }

    private int getNumTank() {
        if (binding.txtTank.getText().length() == 0) {
            return 0;
        }
        return Integer.parseInt(binding.txtTank.getText().toString());
    }
}
