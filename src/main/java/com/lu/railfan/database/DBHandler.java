package com.lu.railfan.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "railfan_db";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TRAIN_TABLE =
            "CREATE TABLE IF NOT EXISTS train(" +
            "_id integer PRIMARY KEY AUTOINCREMENT, " +
            "train_name text NOT NULL, " +
            "num_fuel_tender integer NOT NULL, " +
            "num_brake_tender integer NOT NULL, " +
            "num_water_tank integer NOT NULL)";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TRAIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS train");
        onCreate(sqLiteDatabase);
    }
}
