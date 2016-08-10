package br.com.alexpfx.tracker.soccer.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

import br.com.alexpfx.tracker.soccer.R;

/**
 * Created by alexandre on 09/08/2016.
 */
public class SoccerBoard extends SurfaceView implements SurfaceHolder.Callback {

    private List<Sprite> sprites = new ArrayList<>();
    private SurfaceHolder holder;


    public SoccerBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder = getHolder();
        holder.addCallback(this);

        setZOrderOnTop(true);
        reset();

    }

    private void reset (){
        holder.setFormat(PixelFormat.TRANSPARENT);

    }




    @Override
    protected void onDraw(Canvas canvas) {
        reset();
        canvas.drawColor(Color.BLACK);
        for (Sprite s : sprites) {
            s.update();
            s.onDraw(canvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flag);

        Sprite s1 = new Sprite(scaleDown(bitmap, 60, true), 50, 100);
        Sprite s2 = new Sprite(scaleDown(bitmap, 60, true), 50, 600);
        Sprite s3 = new Sprite(scaleDown(bitmap, 60, true), 400, 100);
        Sprite s4 = new Sprite(scaleDown(bitmap, 60, true), 400, 600);
        sprites.add(s1);
        sprites.add(s2);
        sprites.add(s3);
        sprites.add(s4);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        toggleSelect ();

        return super.onTouchEvent(event);
    }

    private void toggleSelect() {
        for (Sprite s:sprites){
            s.setSelected(!s.isSelected());
        }
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        return super.onDragEvent(event);
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
