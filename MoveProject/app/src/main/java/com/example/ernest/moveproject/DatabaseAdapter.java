package com.example.ernest.moveproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ernest on 2016/10/13.
 */

public class DatabaseAdapter extends SQLiteOpenHelper {
    private static final int DATABSAE_VERSION =2 ;
    private static final String DATABASE_NAME = "CounterDB";
    private static final String TAG = "DatabaseAdapter";
    SQLiteDatabase database;

    DatabaseAdapter(Context context)
    {
        super(context,DATABASE_NAME,null,DATABSAE_VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.e(TAG,"in the DatabaseAdapter onCreate() method");

        try
        {
            createCountersTable(db);
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
            Log.e(TAG,"Database Error : " + e.getMessage().toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG,"in the Upgrade()");
        if(newVersion > oldVersion)
        {
            try {
                db.execSQL("ALTER TABLE COUNTER ADD COLUMN redCounter");
                db.execSQL("ALTER TABLE COUNTER ADD COLUMN greenCounter");
            }
            catch (SQLiteException e){
                e.printStackTrace();
                Log.e(TAG,"Database Error : " + e.getMessage().toString());
            }
        }
    }

    public void createMoodTable(int happy, int sad)
    {
        String query = "CREATE "
    }
    public void createCountersTable(SQLiteDatabase db)
    {
        //database = getWritableDatabase();

        String query = "CREATE TABLE " + Mood.TABLE_NAME
                       +" (id PRIMARY KEY AUTOINCREMENT,"
                       + Counter.RED_COUNTER + " INTEGER,"
                       + Counter.GREEN_COUNTER + " INTEGER);";
        try
        {

            db.execSQL(query);
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
            Log.e(TAG,"Database Error : " + e.getMessage().toString());
        }

    }



    public long addRedCounters(int redCounter)
   {

       SQLiteDatabase db = getWritableDatabase();

       ContentValues values = new ContentValues();
       values.put(Counter.RED_COUNTER,redCounter);

      return db.insert(Counter.TABLE_NAME,null,values);
   }

    public long addGreenCounters(int greenCounter)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Counter.GREEN_COUNTER,greenCounter);

        return db.insert(Counter.TABLE_NAME,null,values);

    }

    public long addAllCounters(int redCounter, int greenCounter)
    {
        SQLiteDatabase db = getWritableDatabase();

        long red = addRedCounters(redCounter);
        long green = addGreenCounters(greenCounter);

        ContentValues values = new ContentValues();
        values.put(Counter.RED_COUNTER,red);
        values.put(Counter.GREEN_COUNTER,green);

        return db.insert(Counter.TABLE_NAME,null,values);
    }

   /* public long getRedCountId( int id)
    {
        SQLiteDatabase db = getReadableDatabase();
        //Cursor cursor = db.rawQuery("SELECT id from " + Counter.TABLE_NAME,"WHERE" + id +" = ?",new String[]{getRedCountId()});
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
    public static void exportDatabase(Context context)
    {
        Log.d(TAG, "Exporting database");
        try {
            Log.d(TAG, "################################## DB BACKUP START #####################################");

            Log.d(TAG, "Getting SD external storage directory...");
            File sd = Environment.getExternalStorageDirectory();

            Log.d(TAG, "Getting data directory...");
            File data = Environment.getDataDirectory();


            Log.d(TAG, "Checking if we can write...");
            if (sd.canWrite()) {
                Log.d(TAG, "Setting DB path...");


                Log.d(TAG, "Creating currentDB file...");
               String currentDBPath = context.getDatabasePath(DatabaseAdapter.DATABASE_NAME).toString();
                File currentDB = new File(currentDBPath);
                SQLiteDatabase.openDatabase(currentDBPath,null,SQLiteDatabase.NO_LOCALIZED_COLLATORS);

                Log.d(TAG, "Creating backup file...");
                Date currentDate = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("_yyyy-MM-dd_HH_mm_ss");
                String backupDBPath = DatabaseAdapter.DATABASE_NAME + sdf.format(currentDate) + ".sqlite";
                Log.d(TAG, "Creating backup file: " + backupDBPath);
                File backupDB = new File(sd, backupDBPath);

                Log.d(TAG, "Check if DB exists 5...");
                if (currentDB.exists()) {
                    Log.d(TAG, "DB does exist...");
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();

                    Log.d(TAG, "Do transfer..");
                    dst.transferFrom(src, 0, src.size());

                    Log.d(TAG, "Close files...");
                    src.close();
                    dst.close();
                    Log.d(TAG, "Export Successful!");
                } else {
                    Log.d(TAG, "XXXXXXXXX [DBHelper]" + "Current DB doesn't exist... ");
                }
            } else {
                Log.d(TAG, "XXXXXXXXX [DBHelper]" + "Don't have permission to write to SD.");
            }
        } catch (Exception e) {
            Log.e(TAG, "XXXXXXXXXXXX [DBHelper]" + "Export failed: " + " - " + e.toString());
        }

        Log.d(TAG, "################################## DB BACKUP END #####################################");

        Log.d(TAG, "DB export complete");

    }

}
