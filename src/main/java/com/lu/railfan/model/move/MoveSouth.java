package com.lu.railfan.model.move;

import com.lu.railfan.model.train.BaseTrain;

public class MoveSouth implements IMove {

    @Override
    public void move(BaseTrain train){
        train.latitude -= 10.0;
    }

}
