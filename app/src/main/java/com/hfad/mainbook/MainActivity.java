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

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.contacts);

        SQLiteOpenHelper sqLiteDatabase = new MainBookDatabaseHelper(this);
        try{
            db = sqLiteDatabase.getReadableDatabase();

            cursor = db.query("COSTUMER",
                    new String[]{"_id","NAME"},
                    null,null,null,null,null);

            SimpleCursorAdapter listAdapeter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);

            listView.setAdapter(listAdapeter);

        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this,"Database Unvailable",Toast.LENGTH_SHORT);
            toast.show();
        }

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this,ShowAndUpdateActivity.class);
                intent.putExtra(ShowAndUpdateActivity.EXTRA_MONEY_ID,(int)id);
                startActivity(intent);
            }
        };

        listView.setOnItemClickListener(itemClickListener);

    }


    public void onClickCreateNew(View view) {
        Intent intent = new Intent(this,CreateContactActivity.class);
        startActivity(intent);
    }

    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
