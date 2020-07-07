package com.hfad.mainbook;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowAndUpdateActivity extends AppCompatActivity {

    public static final String EXTRA_MONEY_ID = "moneyId";
    private SQLiteDatabase db;
    private String money;
    int moneyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_and_update);

        int moneyId = (Integer)getIntent().getExtras().get(EXTRA_MONEY_ID);
        this.moneyId =moneyId;
        SQLiteOpenHelper sqLiteOpenHelper = new MainBookDatabseHelper(this);
        try{
            db = sqLiteOpenHelper.getWritableDatabase();
            Cursor cursor = db.query("MONEY",new String[]{"NAME","ADDRESS","PHONE","MONEY"},"_id = ?",new String[]{Integer.toString(moneyId)},null,null,null);
            if(cursor.moveToFirst()){
                String nameText = cursor.getString(0);
                String addressText = cursor.getString(1);
                String phoneText = cursor.getString(2);
                String moneyText = cursor.getString(3);

                money=moneyText;

                TextView currentAmount = (TextView) findViewById(R.id.current_amount);
                currentAmount.setText(moneyText);

                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);

                TextView address = (TextView)findViewById(R.id.address);
                address.setText(addressText);

                TextView phone = (TextView)findViewById(R.id.phone);
                phone.setText(phoneText);
            }
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this,"Database Unavialable",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onClickUpdateData() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MONEY",money);
        db.update("DRINK",contentValues,"_id",new String[]{String.valueOf(moneyId)});
    }

    public void onClickGiveMoney(View view) {
        int money = Integer.parseInt(this.money);
        EditText editText = (EditText)findViewById(R.id.give_amount);
        int enteredAmount = Integer.parseInt(editText.getText().toString());
        this.money = Integer.toString(money+enteredAmount);
        onClickUpdateData();
    }

    public void onClickTakeMoney(View view) {
        int money = Integer.parseInt(this.money);
        EditText editText = (EditText)findViewById(R.id.give_amount);
        int enteredAmount = Integer.parseInt(editText.getText().toString());
        this.money = Integer.toString(money+enteredAmount);
        onClickUpdateData();
    }
}
