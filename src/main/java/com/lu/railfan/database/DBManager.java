package com.lu.railfan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lu.railfan.model.train.BaseTrain;

import java.util.ArrayList;

public class DBManager {
    private Context context;
    private SQLiteDatabase database;
    private DBHandler dbHandler;

    public DBManager(Context context) {
        this.context = context;
    }

    public void open() throws SQLException {
        dbHandler = new DBHandler(context);
        database = dbHandler.getWritableDatabase();
    }

    public void close() {
        dbHandler.close();
    }

    private ContentValues createTrainValues(String name, int numFuelTender, int numBrakeTender,
                                            int numWaterTank) {
        ContentValues values = new ContentValues();
        values.put("train_name", name);
        values.put("num_fuel_tender", numFuelTender);
        values.put("num_brake_tender", numBrakeTender);
        values.put("num_water_tank", numWaterTank);
        return values;
    }

    public long createTrain(String name, int numFuelTender, int numBrakeTender,
                            int numWaterTank) {
        ContentValues initialValues = createTrainValues(name, numFuelTender, numBrakeTender,
                numWaterTank);
        return database.insert("train", null, initialValues);
    }

    public ArrayList<BaseTrain> getAllTrains() {
        Cursor cursor = database.rawQuery("SELECT * FROM train", null);
        ArrayList<BaseTrain> trainCollection = new ArrayList<>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String trainName = cursor.getString(1);
            int numFuelTender = cursor.getInt(2);
            int numBrakeTender = cursor.getInt(3);
            int numWaterTank = cursor.getInt(4);

            BaseTrain train = BaseTrain.buildTrain(numFuelTender, numBrakeTender, numWaterTank);
            train.name = trainName;
            trainCollection.add(train);
        }
        return trainCollection;
    }
}
