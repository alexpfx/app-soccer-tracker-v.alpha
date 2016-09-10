package br.com.alexpfx.tracker.soccer.managers;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by alexandre on 22/08/2016.
 */
public interface MapManager {


    void moveCamera(LatLng latLng, float zoom);

    void drawFieldAroundLocation(LatLng latLng);
}
