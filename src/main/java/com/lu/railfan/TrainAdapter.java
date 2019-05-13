package com.lu.railfan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lu.railfan.model.train.BaseTrain;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder> {

    public static final String TRAIN_NAME = "TRAIN_NAME";
    public static final String NUM_FUEL_TENDER = "NUM_FUEL_TENDER";
    public static final String NUM_BRAKE_TENDER = "NUM_BRAKE_TENDER";
    public static final String NUM_WATER_TANK = "NUM_WATER_TANK";

    private ArrayList<BaseTrain> trainCollection;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemView;
        public ViewHolder(@NonNull TextView itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    public TrainAdapter(ArrayList<BaseTrain> trainCollection) {
        this.trainCollection = trainCollection;
    }

    @NonNull
    @Override
    public TrainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_train_item, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = ((RecyclerView)view.getParent()).getChildLayoutPosition(view);
                BaseTrain selectedTrain = trainCollection.get(position);
                // Intent
                Intent intent = new Intent(view.getContext(), TrainViewerActivity.class);
                intent.putExtra(TRAIN_NAME, selectedTrain.name);
                intent.putExtra(NUM_FUEL_TENDER, selectedTrain.numFuelTender);
                intent.putExtra(NUM_BRAKE_TENDER, selectedTrain.numBrakeTender);
                intent.putExtra(NUM_WATER_TANK, selectedTrain.numWaterTank);
                view.getContext().startActivity(intent);
            }
        });
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        BaseTrain train = trainCollection.get(position);
        viewHolder.itemView.setText("\""+ train.name + "\". Cost: " + train.cost());
    }


    @Override
    public int getItemCount() {
        return trainCollection.size();
    }
}
