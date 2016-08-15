package br.com.alexpfx.tracker.soccer;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by alexandre on 14/08/2016.
 */
public class LocationRequestHelper {


    public static LocationRequest createLocationRequest(Context context, long expirationDuration, long fastestInterval, long interval, int priorityHighAccuracy){
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setExpirationDuration(expirationDuration);
        locationRequest.setFastestInterval(fastestInterval);
        locationRequest.setInterval(interval);
        locationRequest.setPriority(priorityHighAccuracy);

        if (!PermissionHelper.checkForLocationPermission(context)) {
            PermissionHelper.requestLocationPermission((Activity) context, 1);
        }
        return locationRequest;
    }


}
