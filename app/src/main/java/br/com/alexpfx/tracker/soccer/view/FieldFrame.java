package br.com.alexpfx.tracker.soccer.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import br.com.alexpfx.tracker.soccer.R;

/**
 * Created by alexandre on 08/08/2016.
 */
public class FieldFrame extends FrameLayout {
    public static final int offset = 40    ;
    private List<Rect> points = new ArrayList<>();
    private Paint paint;
    private GoogleMap map;


    public FieldFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void setMap(GoogleMap map) {
        this.map = map;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Rect r : points) {
            canvas.drawRect(r, paint);
        }
    }

    long lastEvent = 0;

    @Override
    public boolean onDragEvent(DragEvent event) {
        return super.onDragEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getPointerCount() > 1){
            map.clear();
            points.clear();
            return true;
        }
        if (event.getActionMasked() != MotionEvent.ACTION_UP){
            return true;
        }
        long eventTime = event.getEventTime();
        if (eventTime - lastEvent < 100){
            return true;
        }

        lastEvent = eventTime;

        if (points.size() == 4){
            return false;
        }

        Projection projection = map.getProjection();
        Point point = new Point((int) event.getX(), (int) event.getY());
        map.addMarker(new MarkerOptions()
                .position(projection.fromScreenLocation(point)).draggable(true));
        points.add(new Rect(point.x - offset, point.y - offset, point.x + offset, point.y + offset));
        invalidate();
        requestLayout();
        return true;
    }
}
