package br.com.alexpfx.tracker.soccer;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by alexandre on 08/08/2016.
 */
public class LocationHelper {

    public static LatLng latLng (Location location){
        return new LatLng(location.getLatitude(), location.getLongitude());
    }
}
