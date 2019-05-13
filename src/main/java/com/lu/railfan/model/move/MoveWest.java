package com.lu.railfan.model.move;

import com.lu.railfan.model.train.BaseTrain;

public class MoveWest implements IMove {

    @Override
    public void move(BaseTrain train){
        train.longitude -= 10.0;
    }
}
