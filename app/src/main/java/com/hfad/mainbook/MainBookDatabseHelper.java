package com.hfad.mainbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainBookDatabseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "MainBook";
    private static final int DB_VERSION = 2;
    private static SQLiteDatabase db =null;
    MainBookDatabseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db,0,DB_VERSION);
        this.db = db;
    }

    private static void insertContact(String name,String address,String phone,int money){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("ADDRESS",address);
        contentValues.put("PHONE",phone);
        contentValues.put("MONEY",money);
        db.insert("COSTUMER",null,contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db,oldVersion,newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db,int oldVersion,int newVersion){
        if(oldVersion<1){
            db.execSQL("CREATE TABLE COSTUMER (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NAME TEXT," +
                    "ADDRESS TEXT," +
                    "PHONE TEXT," +
                    "MONEY INTEGER);");
            insertContact("MY","AT SHEVAGE BK BODWAD","9503740861",0);
        }
        if(oldVersion<2){
            // do this
        }
    }

    public void updateMoney(String name, int money){
        ContentValues moneyValue =new ContentValues();
        moneyValue.put("MONEY",money);
        db.update("COSTUMER",moneyValue,"NAME = ?",new String[]{name});
    }
}