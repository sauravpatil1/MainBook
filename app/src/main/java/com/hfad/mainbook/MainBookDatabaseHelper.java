package com.hfad.mainbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainBookDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mainbook";
    private static final int DB_VERSION = 2;

    MainBookDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db,0,DB_VERSION);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db,oldVersion,newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db,int oldVersion,int newVersion){
        if(oldVersion<1){
            db.execSQL("CREATE TABLE COSTUMER (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME TEXT,"
                    + "ADDRESS TEXT,"
                    + "PHONE TEXT,"
                    + "MONEY INTEGER);");

        }
        if(oldVersion<2){
            // do this
        }
    }
}
