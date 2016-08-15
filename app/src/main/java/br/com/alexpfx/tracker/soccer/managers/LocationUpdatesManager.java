package br.com.alexpfx.tracker.soccer.managers;

import android.location.Location;

import com.google.android.gms.location.LocationRequest;

/**
 * Created by alexandre on 14/08/2016.
 */
public interface LocationUpdatesManager {

    void requestLocationUpdates(Callback callback);

    void stopLocationUpdates();

    interface Callback {
        void onLocationReceived(double lat, double lng);
    }


}
