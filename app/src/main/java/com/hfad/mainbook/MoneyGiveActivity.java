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
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MoneyGiveActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_give);
        ListView listView = (ListView)findViewById(R.id.list_of_costumer);
        try{
            SQLiteOpenHelper sqLiteOpenHelper = new MainBookDatabseHelper(this);
            db = sqLiteOpenHelper.getReadableDatabase();
            cursor = db.query("MONEY",new String[]{"NAME"},"MONEY <0",null,null,null,null);
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(MoneyGiveActivity.this,android.R.layout.simple_expandable_list_item_1,cursor,new String[]{"NAME"},new int[]{android.R.id.text1},0);
            listView.setAdapter(cursorAdapter);
        }catch (SQLiteException e){
            Toast toast =Toast.makeText(this,"Database Unvailable",Toast.LENGTH_SHORT);
            toast.show();
        }
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MoneyGiveActivity.this,ShowAndUpdateActivity.class);
                intent.putExtra(ShowAndUpdateActivity.EXTRA_MONEY_ID,(int)id);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(itemClickListener);

    }
}
