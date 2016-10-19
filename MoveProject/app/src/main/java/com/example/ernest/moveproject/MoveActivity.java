package com.example.ernest.moveproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.support.v4.*;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.examples.ernest.DropTargetView;

import java.util.ArrayList;

public class MoveActivity extends AppCompatActivity {

        private static final String TAG = "MainActivity" ;
        private ImageView imageView1, imageView2, imageView3;
        private float mLastTouchX, mLastTouchY;
        private float posX;
        private float posY;
        private DatabaseAdapter dbAdapter;
        private SQLiteDatabase db;
        private Animation animation;
        private DropTargetView targetView;
        private ImageView starImageView;
        private ArrayList<Integer> baskets;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            Log.e(TAG,"In the onCreate() method of the MoveActivity");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_move);

            imageView1 =(ImageView) findViewById(R.id.image1);
            imageView2 = (ImageView) findViewById(R.id.image2);
            imageView3 = (ImageView) findViewById(R.id.image3);

            dbAdapter = new DatabaseAdapter(getApplicationContext());
            dbAdapter.exportDatabase(getApplicationContext());

            dbAdapter.getWritableDatabase();
            //dbAdapter.insertData("tested ",1,0);
            //dbAdapter.insertData();

            baskets = new ArrayList<>();
            baskets.add(1);
            if(!baskets.isEmpty())
            {
                Toast.makeText(getApplicationContext(),"How are you today ?",Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Please rate your mood ", Toast.LENGTH_LONG).show();
            }

            long count;

            // public void animateStar(ImageView starImageView)

            animation = AnimationUtils.loadAnimation(this, R.anim.star);

            starImageView = (ImageView) findViewById(R.id.star);
            starImageView.clearAnimation();
            starImageView.setImageResource(R.drawable.star);
            starImageView.startAnimation(animation);

            //DBAdapter.addGreenCounters(77);
            imageView1.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.e(TAG, "ImageView1: OnLongClickListener() method");
                    View.DragShadowBuilder shadowBuilder =
                            new View.DragShadowBuilder(v);

                    v.startDrag(null, shadowBuilder,
                            ((ImageView) v).getDrawable(), 0);

                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(100);
                    //imageView1.setVisibility(View.INVISIBLE);
                    return true;
                }
            });


            imageView2.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.e(TAG, "Imageview2: OnLongClickListener()");
                    View.DragShadowBuilder shadowBuilder =
                            new View.DragShadowBuilder(v);

                    v.startDrag(null, shadowBuilder,
                            ((ImageView) v).getDrawable(), 0);

                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(100);
                    //imageView2.setVisibility(View.INVISIBLE);

                    return true;
                }
            });


            imageView3.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    Log.e(TAG, "ImageView3: OnLongClickListener() ");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

                    imageView3.startDrag(null, shadowBuilder, ((ImageView) v).getDrawable(), 0);

                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(100);
                    //imageView3.setVisibility(View.INVISIBLE);
                    return true;
                }
            });


       /* imageView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                float x = 0;
                float y = 0;

                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        //get x and values
                        x = event.getX();
                        y = event.getY();
                        break;

                    case MotionEvent.ACTION_MOVE:

                        posX = mLastTouchX - x;
                        posY = mLastTouchY - y;
                        break;

                    case MotionEvent.ACTION_UP:
                        break;

                    default:
                        return false;


                }
                return true;
            }
        });
     */
        }

   /* public ImageView switchImageView(ImageView v)
    {

        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Log.e(TAG, "ImageView3: OnLongClickListener() ");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

                imageView3.startDrag(null, shadowBuilder, ((ImageView) v).getDrawable(), 0);

                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(100);
                return true;
            }
        });

        return v;
    }
    */

    }