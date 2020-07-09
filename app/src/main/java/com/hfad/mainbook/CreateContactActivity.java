package com.hfad.mainbook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);
    }

    public void onClickUpdate(View view) {
        SQLiteOpenHelper sqLiteOpenHelper = new MainBookDatabaseHelper(this);
        EditText name = (EditText)findViewById(R.id.entered_name);
        String nameText = name.getText().toString();

        EditText address =(EditText)findViewById(R.id.entered_address);
        String addressText = address.getText().toString();

        EditText phone = (EditText)findViewById(R.id.entered_mobile_number);
        String phoneText = phone.getText().toString();
        try{
            SQLiteDatabase db =  sqLiteOpenHelper.getWritableDatabase();
            insertContact(db,nameText,addressText,phoneText,0);
            db.close();
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this,"Database Unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private static void insertContact(SQLiteDatabase db , String name,String address,String phone,int money){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("ADDRESS",address);
        contentValues.put("PHONE",phone);
        contentValues.put("MONEY",money);
        db.insert("COSTUMER",null,contentValues);
    }

}
