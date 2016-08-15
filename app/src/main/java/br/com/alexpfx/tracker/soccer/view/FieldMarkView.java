package br.com.alexpfx.tracker.soccer.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

import br.com.alexpfx.tracker.soccer.R;

/**
 * Created by alexandre on 13/08/2016.
 */
public class FieldMarkView extends ViewGroup implements View.OnTouchListener, View.OnDragListener {
    private ImageView mark;
    private static final String TAG = "FieldMarkView";
    private Random random = new Random();

    public FieldMarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FieldMarkView);
        int nr = a.getInt(R.styleable.FieldMarkView_numberOfMarks, 4);
        Log.d(TAG, "FieldMarkView: " + nr);
        for (int i = 0; i < nr; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.drawable.flag);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            imageView.setX(getRandomInt(500));
            imageView.setY(getRandomInt(500));
            imageView.setScaleX(0.2f);
            imageView.setScaleY(0.2f);
            imageView.setOnTouchListener(this);
            imageView.setOnDragListener(this);


            addView(imageView, params);

        }

        a.recycle();

    }

    private int getRandomInt(int max) {
        int i = random.nextInt(max);
        Log.d(TAG, "getRandomInt: "+i);
        return i;
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(left, top, right, bottom);


        }
        invalidate();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void init() {

    }

    float initialX = 0;
    float initialY = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                initialX = v.getX();
                initialY = v.getY();
                Log.d(TAG, "down: ");
                break;
            case MotionEvent.ACTION_MOVE:
                v.setX(initialX + event.getRawX());
                v.setY(initialY + event.getRawY());
                Log.d(TAG, "move: ");
                break;
        }
        updateViewLayout(v, v.getLayoutParams());
        return false;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        Log.d(TAG, "onDrag: ");
        return false;
    }
}
