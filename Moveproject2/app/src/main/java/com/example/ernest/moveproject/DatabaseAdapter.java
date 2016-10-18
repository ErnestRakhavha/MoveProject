package com.example.ernest.moveproject;

import android.content.ContentValues;
import android.content.Context;
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
import java.util.Date;

/**
 * Created by Ernest on 2016/10/18.
 */
public class DatabaseAdapter extends SQLiteOpenHelper {

        private static final int DATABSAE_VERSION =2 ;
        private static final String DATABASE_NAME = "CounterDB.sqlite";
        private static final String TAG = "DatabaseAdapter";
        private static DatabaseAdapter instance;
        private static final String TEST_STRING = "test";
        private Context context;

        public DatabaseAdapter(Context context)
        {
            super(context,DATABASE_NAME,null,DATABSAE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            Log.e(TAG, "In the DatabaseAdapter onCreate() method");

            try
            {
                createMoodTable(db);
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

            if(newVersion <= oldVersion) {
                try {
                    db.execSQL("ALTER TABLE " + Mood.TABLE_NAME + " ADD COLUMN " + Mood.BASKET_LABEL);
                    db.execSQL("ALTER TABLE " + Mood.TABLE_NAME + " ADD COLUMN " + Mood.HAPPY);
                    db.execSQL("ALTER TABLE " + Mood.TABLE_NAME + " ADD COLUMN greenCounter");
                } catch (SQLiteException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Database Error : " + e.getMessage().toString());
                }
            }

        }

        public void createMoodTable(SQLiteDatabase db)
        {
            Log.e(TAG,"Creating a Mood table");
            // db = this.getWritableDatabase();

            String query = "CREATE TABLE "   + Mood.TABLE_NAME +
                    " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + Mood.BASKET_LABEL
                    + " TEXT, " + Mood.HAPPY + " INTEGER, "
                    + Mood.SAD + " INTEGER );";
            try
            {
                db.execSQL(query);
            }
            catch (SQLiteException e)
            {
                e.printStackTrace();
                Log.e(TAG,"Error : " + e.getMessage().toString());
            }
            Log.e(TAG,"Moods table successfully created");


        }

        public static synchronized DatabaseAdapter getInstance(Context context)
        {
            if (instance == null)
            {
                instance = new DatabaseAdapter(context);
            }
            return instance;
        }

        public void insertData()//(String basketLabel,int happy,int sad)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Mood.BASKET_LABEL, "Default value");
            values.put(Mood.HAPPY,1);
            values.put(Mood.SAD,2);
            // db.insert(Mood.TABLE_NAME,null,values);

            //return true;
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
                    //SQLiteDatabase.openDatabase(currentDBPath,null,SQLiteDatabase.NO_LOCALIZED_COLLATORS);

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


