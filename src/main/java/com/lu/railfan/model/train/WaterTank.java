package com.lu.railfan.model.train;

public class WaterTank extends BaseTrain {
    BaseTrain myTrain;

    public WaterTank(BaseTrain myTrain) {
        this.myTrain = myTrain;
        this.numWaterTank = myTrain.numWaterTank + 1;
        this.numFuelTender = myTrain.numFuelTender;
        this.numBrakeTender = myTrain.numBrakeTender;
    }

    public int cost() {
        return 50 + myTrain.cost();
    }
}
