package com.example.ernest.moveproject;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by Ernest on 2016/10/19.
 */
public class BasketData {

    private static final String TABLE_NAME = "mood";
    private static final String HAPPY = "happy";
    private static final String SAD = "sad";
    private static final String TAG = "BasketData" ;
    private static BasketData instance = null;

    private int persistDataToDB()
    {
        int id =0;
        Log.e(TAG, "Saving data to the database");
        SQLiteDatabase db = DatabaseAdapter.getInstance(ContextSingleton.getContext()).getWritableDatabase();
        try {
            //using mock values at the moment
            ContentValues values = new ContentValues();
            values.put(HAPPY, 1);
            values.put(SAD, 2);
            id = (int) db.insert(TABLE_NAME, null, values);
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }

        Log.e(TAG,"Data was successfully stored into the database");
        return id;
    }

    public static synchronized BasketData getInstance()
    {
        if(instance == null)
        {
            instance = new BasketData();
        }
        return instance;
    }
}
