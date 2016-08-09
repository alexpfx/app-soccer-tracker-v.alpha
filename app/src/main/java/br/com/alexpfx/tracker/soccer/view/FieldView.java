package br.com.alexpfx.tracker.soccer.view;

import android.graphics.PointF;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by alexandre on 07/08/2016.
 */
public interface FieldView {
    void show ();
    void updateFieldSize(FieldSize fieldSize);
    void updateFieldPosition(LatLng p0, LatLng p1, LatLng p2, LatLng p3);
    void hide ();
}
