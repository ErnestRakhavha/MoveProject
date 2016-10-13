package com.example.ernest.moveproject;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MoveActivity extends AppCompatActivity {

        private static final String TAG = "MainActivity" ;
        private ImageView imageView1, imageView2, imageView3;
        private float mLastTouchX, mLastTouchY;
        private float posX;
        private float posY;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_move);

            imageView1 =(ImageView) findViewById(R.id.image1);
            imageView2 = (ImageView) findViewById(R.id.image2);
            imageView3 = (ImageView) findViewById(R.id.image3);

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