package com.example.ernest.moveproject;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by Ernest on 2016/10/18.
 */

public class Basket {

    private static final String TAG = "Basket" ;
    private int rCounter;
    private int gCounter;

    public Basket(){}

    public Basket(int rCounter, int gCounter)
    {
        this.rCounter = rCounter;
        this.gCounter = gCounter;
    }

    public void setRedCounter(int rCounter)
    {
        if(rCounter == 0)
        {
            Log.e(TAG,"mood has not been set yet");
        }
    }

    public int getRedCount()
    {
        return rCounter;
    }

    public void setGreenCounter(int gCounter)
    {
        if(gCounter == 0)
        {
            Log.e(TAG,"mood has not been set yet");
        }
    }

    public int getGreenCounter(int gCounter)
    {
        return gCounter;
    }

}
