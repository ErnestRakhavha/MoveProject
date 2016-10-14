package com.example.ernest.moveproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Ernest on 2016/10/13.
 */

public class DatabaseAdapter extends SQLiteOpenHelper {
    private static final int DATABSAE_VERSION =1 ;
    private static final String DATABASE_NAME = "countDb";
    private static final String CREATE_TABLE = "CREATE TABLE COUNTER (ID INTEGER AUTOINCREMENT," +
                                                "redCounter INTEGER,greenCounter INTEGER,PRIMARY KEY(ID));";
    private static final String TAG = "DatabaseAdapter";
    SQLiteDatabase database;


    DatabaseAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABSAE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL(CREATE_TABLE);
        }catch (SQLiteException e)
        {
            e.printStackTrace();
            Log.e(TAG,"SQLiteMessage: " + e.getMessage().toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(newVersion > oldVersion)
        {
            db.execSQL("ALTER TABLE COUNTER ADD COLUMN redCounter");
            db.execSQL("ALTER TABLE COUNTER ADD COLUMN greenCounter");
        }
    }

   /* public bool addCounters(int redCounter, int greenCounter)
   {
       ContentValues values = new ContentValues();
       values.put(Counter.RED_COUNTER,redCounter);
       values.put(Counter.GREEN_COUNTER,redCounter);
       database.insert(Counter.TABLE_NAME,null,values);
       return true;

   }


   */
    public ArrayList<String> getAllDropCounts()
    {
        ArrayList<String> count_list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from " + Counter.TABLE_NAME,null);
        cursor.moveToNext();

        if (cursor != null)
        {
            count_list.add(cursor.getString(cursor.getColumnIndex(Counter.RED_COUNTER)));
        }
        return count_list;
    }
}
