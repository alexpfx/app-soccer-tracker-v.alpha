package br.com.alexpfx.tracker.soccer.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;

/**
 * Created by alexandre on 09/08/2016.
 */
public class Sprite {

    public static final int WHITE = Color.WHITE;
    public static final int OFFSET = 15;
    private Bitmap bitmap;

    private float x;
    private float y;

    private Paint paintSelected;

    private RectF rect;
    private boolean selected = true;

    public void onDraw(Canvas canvas) {

        canvas.drawBitmap(bitmap, x, y, paintSelected);
        if (isSelected()){
            canvas.drawRect(rect, paintSelected);
        }


    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        rect.set(x - OFFSET, y - OFFSET, x + bitmap.getWidth(), y + OFFSET + bitmap.getHeight());

    }

    public Sprite(Bitmap bitmap, float x, float y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;

        paintSelected = basePaint();

        rect = new RectF();
        update();
    }



    private Paint basePaint() {
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setColorFilter(new PorterDuffColorFilter(WHITE, PorterDuff.Mode.SRC_IN));
        return p;
    }


    public boolean isCollision(float x, float y) {
        return rect.contains(x, y);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
