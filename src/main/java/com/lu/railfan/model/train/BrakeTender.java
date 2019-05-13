package com.lu.railfan.model.train;

public class BrakeTender extends BaseTrain {
    BaseTrain myTrain;

    public BrakeTender(BaseTrain myTrain) {
        this.myTrain = myTrain;
        this.numBrakeTender = myTrain.numBrakeTender + 1;
        this.numFuelTender = myTrain.numFuelTender;
        this.numWaterTank = myTrain.numWaterTank;
    }

    public int cost() {
        return 70 + myTrain.cost();
    }
}
