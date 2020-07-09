package com.hfad.mainbook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MoneyTakeFrom extends AppCompatActivity {
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_take_from);

        ListView listView = (ListView)findViewById(R.id.list_take_money);

        SQLiteOpenHelper sqLiteOpenHelper = new MainBookDatabaseHelper(this);
        try{
            db = sqLiteOpenHelper.getReadableDatabase();
            cursor = db.query("COSTUMER",
                    new String[]{"_id","NAME"},
                    "MONEY > ?",new String[]{String.valueOf(0)},
                    null,null,
                    "MONEY ASC");

            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);

            listView.setAdapter(simpleCursorAdapter);
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this,"Database Unvialable",Toast.LENGTH_SHORT);
            toast.show();
        }
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MoneyTakeFrom.this,ShowAndUpdateActivity.class);
                intent.putExtra(ShowAndUpdateActivity.EXTRA_MONEY_ID,(int)id);
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(onItemClickListener);
    }
    public void onDestroy() {
        super.onDestroy();
        db.close();
        cursor.close();
    }
}
