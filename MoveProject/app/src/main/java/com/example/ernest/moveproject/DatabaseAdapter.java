package com.example.ernest.moveproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ernest on 2016/10/13.
 */

public class DatabaseAdapter extends SQLiteOpenHelper {
    private static final int DATABSAE_VERSION =1 ;
    private static final String DATABASE_NAME = "countDb";
    private static final String CREATE_TABLE = "CREATE TABLE COUNTER (ID INTEGER AUTOINCREMENT," +
                                                "redCounter INTEGER,greenCounter INTEGER,PRIMARY KEY(ID));";
    private static final String TAG = "DatabaseAdapter";


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

   /* public Cursor addCounters(int redCounter, int greenCounter)
   {
       ContentValues values = new ContentValues();
       values.put(Counter.RED_COUNTER,redCounter);
       values.put(Counter.GREEN_COUNTER,redCounter);
       db.insert(Counter.TABLE_NAME,null,values);

   }
   */
}
