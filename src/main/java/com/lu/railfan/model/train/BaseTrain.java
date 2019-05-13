package com.lu.railfan.model.train;

import com.lu.railfan.model.move.*;

public abstract class BaseTrain {
    IMove moveTrain;
    public double longitude; //default value is int 0
    public double latitude; //default value is int 0
    public String name;
    public int numFuelTender;
    public int numBrakeTender;
    public int numWaterTank;


    public abstract int cost();

    public void moveNorth() {
        moveTrain = new MoveNorth();
        moveTrain.move(this);
    }

    public void moveSouth() {
        moveTrain = new MoveSouth();
        moveTrain.move(this);
    }

    public void moveWest() {
        moveTrain = new MoveWest();
        moveTrain.move(this);
    }

    public void moveEast() {
        moveTrain = new MoveEast();
        moveTrain.move(this);
    }

    public static BaseTrain buildTrain(int numFuelTender, int numBrakeTender, int numWaterTank) {
        BaseTrain newTrain = new BasicTrain();

        while (numFuelTender > 0) {
            newTrain = new FuelTender(newTrain);
            numFuelTender--;
        }

        while (numBrakeTender > 0) {
            newTrain = new BrakeTender(newTrain);
            numBrakeTender--;
        }

        while (numWaterTank > 0) {
            newTrain = new WaterTank(newTrain);
            numWaterTank--;
        }

        return newTrain;
    }

}
