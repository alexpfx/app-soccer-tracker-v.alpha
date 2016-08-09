package br.com.alexpfx.tracker.soccer.view;

import android.location.Location;

/**
 * Created by alexandre on 07/08/2016.
 */
public interface MapView {
    void showConnected ();
    void showConnectionFailed ();

    void showNewLocationOnMap (Location location);

}
