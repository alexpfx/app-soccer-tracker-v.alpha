package br.com.alexpfx.tracker.soccer.ui.map;

import android.location.Location;

/**
 * Created by alexandre on 07/08/2016.
 */
public interface MapView {
    void showConnected ();
    void showConnectionFailed ();
    void showNewLocationOnMap (Location location);
    void showNewLocationCoordinates(String latLong);

}
