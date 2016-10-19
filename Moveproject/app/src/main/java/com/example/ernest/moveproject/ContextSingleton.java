package com.example.ernest.moveproject;

import android.content.Context;

/**
 * Created by Ernest on 2016/10/19.
 */
public class ContextSingleton {
    private Context context;

    //The protected private instance:
    private static ContextSingleton instance = null;

    private ContextSingleton() {
        //Prevents instationation of the singleton, have to use the
        //getInstance method.
    }

    //The singleton getInstance method:
    public static ContextSingleton getInstance() {
        if (instance == null) {
            instance = new ContextSingleton ();
        }
        return instance;
    }

    public static Context getContext() {
        return getInstance().context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}

