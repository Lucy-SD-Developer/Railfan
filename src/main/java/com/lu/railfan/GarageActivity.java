package com.lu.railfan;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lu.railfan.database.DBManager;
import com.lu.railfan.databinding.ActivityGarageBinding;
import com.lu.railfan.model.train.BaseTrain;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class GarageActivity extends AppCompatActivity {

    private ActivityGarageBinding binding;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_garage);
        setSupportActionBar(binding.toolbar);

        binding.rvTrain.setHasFixedSize(true);

        DBManager dbManager = new DBManager(GarageActivity.this);
        dbManager.open();

        ArrayList<BaseTrain> trainList = dbManager.getAllTrains();

        dbManager.close();

        layoutManager = new LinearLayoutManager(this);
        binding.rvTrain.setLayoutManager(layoutManager);

        adapter = new TrainAdapter(trainList);
        binding.rvTrain.setAdapter(adapter);
    }
}
