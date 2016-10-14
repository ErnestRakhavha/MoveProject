package com.examples.ernest;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.ernest.moveproject.DatabaseAdapter;

/**
 * Created by Ernest on 2016/10/13.
 */
public class DropTargetView extends ImageView implements View.OnDragListener {

    private boolean mDropped;
    private DatabaseAdapter DBAdapter;

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

    @Override
    public boolean onDrag(View v, DragEvent event) {
        PropertyValuesHolder pvhX, pvhY;
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                //React to a new drag by shrinking the view
                pvhX = PropertyValuesHolder.ofFloat("scaleX", 0.5f);   //enums/lookup
                pvhY = PropertyValuesHolder.ofFloat("scaleY", 0.5f);
                ObjectAnimator.ofPropertyValuesHolder(this,
                        pvhX, pvhY).start();
                //Clear the current drop image on a new event
                setImageDrawable(null);
                mDropped = false;
                break;

            case DragEvent.ACTION_DRAG_ENDED:
                // React to a drag ending by resetting the view size
                // if we weren't the drop target.
                if (!mDropped) {
                    pvhX = PropertyValuesHolder.ofFloat("scaleX", 1f);
                    pvhY = PropertyValuesHolder.ofFloat("scaleY", 1f);
                    ObjectAnimator.ofPropertyValuesHolder(this,
                            pvhX, pvhY).start();
                    mDropped = false;
                    break;
                }

            case DragEvent.ACTION_DRAG_ENTERED:
                //React to a drag entering this view by growing slightly

                pvhX = PropertyValuesHolder.ofFloat("scaleX", 0.75f);
                pvhY = PropertyValuesHolder.ofFloat("scaleY", 0.75f);
                ObjectAnimator.ofPropertyValuesHolder(this,
                        pvhX, pvhY).start();
                break;

            case DragEvent.ACTION_DRAG_EXITED:
                //React to a drag leaving by returning to previous size
                pvhX = PropertyValuesHolder.ofFloat("scaleX", 0.5f);
                pvhY = PropertyValuesHolder.ofFloat("scaleY", 0.5f);
                ObjectAnimator.ofPropertyValuesHolder(this,
                        pvhX, pvhY).start();
                break;

            case DragEvent.ACTION_DROP:

                //This animation shrinks the view briefly down to nothing
                // and then back.
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

                //cast
                mDropped = true;
                break;
            default:
                //Ignore events we aren't interested in
                return false;
        }

        return true;
    }

}