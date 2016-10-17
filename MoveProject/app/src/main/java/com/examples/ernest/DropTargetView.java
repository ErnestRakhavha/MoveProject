package com.examples.ernest;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ernest.moveproject.DatabaseAdapter;
import com.example.ernest.moveproject.R;

/**
 * Created by Ernest on 2016/10/13.
 */
public class DropTargetView extends ImageView implements View.OnDragListener {

    private static final String TAG = "DropTargetView";
    private boolean mDropped;
    private DatabaseAdapter DBAdapter;
    private int redDropCount;
    private int greenDropCount;
    private int red = 0;
    private int green = 0;
    private int mDragViewId;

    public DropTargetView(Context context) {
        super(context);
        init();
    }

    public DropTargetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DropTargetView(Context context, AttributeSet attrs,
                          int defaultStyle) {
        super(context, attrs, defaultStyle);
        init();
    }

    private void init() {
        // set a valid listener to receive DragEvents
        setOnDragListener(this);
    }

    public void onCircleDragStarted(DragEvent event)
    {
        PropertyValuesHolder pvhX, pvhY;

        pvhX = PropertyValuesHolder.ofFloat("scaleX",0.5f);
        pvhY = PropertyValuesHolder.ofFloat("scaleY",0.5f);
        ObjectAnimator.ofPropertyValuesHolder(this, pvhX, pvhY).start();

        setImageDrawable(null);
        mDropped = false;

    }

    public void onCircleDragEnded(DragEvent event)
    {
       PropertyValuesHolder pvhX, pvhY;

        if (!mDropped) {
            Log.e(TAG, "drag ended here");

            pvhX = PropertyValuesHolder.ofFloat("scaleX", 1f);
            pvhY = PropertyValuesHolder.ofFloat("scaleY", 1f);
            ObjectAnimator.ofPropertyValuesHolder(this,
                    pvhX, pvhY).start();
            mDropped = false;
        }

    }

    public void onCircleDragEntered(DragEvent event)
    {
        PropertyValuesHolder pvhX, pvhY;

        Log.e(TAG,"drag entered here");
        pvhX = PropertyValuesHolder.ofFloat("scaleX", 0.75f);
        pvhY = PropertyValuesHolder.ofFloat("scaleY", 0.75f);
        ObjectAnimator.ofPropertyValuesHolder(this,
                pvhX, pvhY).start();
    }

    public void onCircleDragExited(DragEvent event)
    {
        PropertyValuesHolder pvhX, pvhY;

        Log.e(TAG,"dropped here");
        Keyframe frame0 = Keyframe.ofFloat(0f, 0.75f);
        Keyframe frame1 = Keyframe.ofFloat(0.5f, 0f);
        Keyframe frame2 = Keyframe.ofFloat(1f, 0.5f);
        pvhX = PropertyValuesHolder.ofKeyframe("scaleX",
                frame0, frame1, frame2);
        pvhY = PropertyValuesHolder.ofKeyframe("scaleY",
                frame0, frame1, frame2);
        ObjectAnimator.ofPropertyValuesHolder(this,
                pvhX, pvhY).start();
        //Set our image from the Object passed with the DragEvent
        setImageDrawable((Drawable) event.getLocalState());

    }

    public void onCirleDropped(DragEvent event)
    {

        PropertyValuesHolder pvhX, pvhY;
        Log.e(TAG,"dropped here");
        Keyframe frame0 = Keyframe.ofFloat(0f, 0.75f);
        Keyframe frame1 = Keyframe.ofFloat(0.5f, 0f);
        Keyframe frame2 = Keyframe.ofFloat(1f, 0.5f);
        pvhX = PropertyValuesHolder.ofKeyframe("scaleX",
                frame0, frame1, frame2);
        pvhY = PropertyValuesHolder.ofKeyframe("scaleY",
                frame0, frame1, frame2);
        ObjectAnimator.ofPropertyValuesHolder(this,
                pvhX, pvhY).start();
        //Set our image from the Object passed with the DragEvent
        setImageDrawable((Drawable) event.getLocalState());

    }

   @Override
    public boolean onDrag(View v, DragEvent event) {

        Log.e(TAG,"in the onDrag()");
        //BitmapDrawable dragView = (BitmapDrawable)event.getLocalState();
        //ImageView dragView = (ImageView)event.getLocalState();

        PropertyValuesHolder pvhX, pvhY;
        switch (event.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                onCircleDragStarted(event);

                break;

            case DragEvent.ACTION_DRAG_ENTERED:
                // React to a drag ending by resetting the view size
                // if we weren't the drop target.
                onCircleDragEntered(event);
                    break;


            case DragEvent.ACTION_DRAG_ENDED:
                //React to a drag entering this view by growing slightly
                onCircleDragEnded(event);

                break;

            case DragEvent.ACTION_DRAG_EXITED:
                //React to a drag leaving by returning to previous size
                onCircleDragExited(event);
                break;

            case DragEvent.ACTION_DROP:

                //This animation shrinks the view briefly down to nothing
                // and then back.
                onCirleDropped(event);
                /*
                if (!mDropped)
                {
                    redDropCount++;
                    greenDropCount++;

                    DBAdapter.addRedCounters(redDropCount);
                    DBAdapter.addGreenCounters(greenDropCount);
                    Toast.makeText(getContext(),"Red   : " + redDropCount,Toast.LENGTH_LONG);
                    Toast.makeText(getContext(),"Green : " + greenDropCount,Toast.LENGTH_LONG);

                }
                */

                //mDropped = true;
                break;
            default:
                //Ignore events we aren't interested in
                return false;
        }

        return true;
    }

}