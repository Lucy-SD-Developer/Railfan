package com.lu.railfan.model.collection;

import com.lu.railfan.model.train.BaseTrain;
import java.util.ArrayList;

public class TrainCollection implements IContainer{

    private final ArrayList<BaseTrain> _trainList;

    public TrainCollection() {
        this._trainList = new ArrayList<>();
    }

    @Override
    public TrainIterator createIterator() {
        TrainIterator iter = new TrainIterator(this._trainList);
        return iter;
    }

    public void addTrain(BaseTrain train) {
        this._trainList.add(train);
    }

    public ArrayList getList() { return this._trainList; }
}
