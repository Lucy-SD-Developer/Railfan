package com.lu.railfan.model.collection;

import com.lu.railfan.model.train.BaseTrain;
import java.util.ArrayList;

public class TrainIterator implements IIterator {
    private final ArrayList<BaseTrain> _trainList;
    private int _next;
    public TrainIterator(ArrayList<BaseTrain> trainList) {
        this._trainList = trainList;
        this._next = 0;
    }

    @Override
    public boolean hasNext() {
        return this._trainList != null &&
                this._next < this._trainList.size();
    }

    @Override
    public BaseTrain next() {
        return this._trainList.get(this._next++);
    }
}
