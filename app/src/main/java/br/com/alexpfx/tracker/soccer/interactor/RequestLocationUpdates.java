package br.com.alexpfx.tracker.soccer.interactor;

import android.location.Location;

/**
 * Created by alexandre on 07/08/2016.
 */
public interface RequestLocationUpdates {

    void startLocationUpdates (Callback callback);


    interface Callback {
        void onLocationChange (Location location);

    }
}
