package com.lu.railfan.model.train;

public class FuelTender extends BaseTrain {
    BaseTrain myTrain;

    public FuelTender(BaseTrain myTrain) {
        this.myTrain = myTrain;
        this.numFuelTender = myTrain.numFuelTender + 1;
        this.numBrakeTender = myTrain.numBrakeTender;
        this.numWaterTank = myTrain.numWaterTank;
    }

    public int cost() {
        return 80 + myTrain.cost();
    }
}
