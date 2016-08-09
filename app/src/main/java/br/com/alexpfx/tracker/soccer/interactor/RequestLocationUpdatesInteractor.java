package br.com.alexpfx.tracker.soccer.interactor;

import android.app.Activity;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import br.com.alexpfx.tracker.soccer.PermissionHelper;

/**
 * Created by alexandre on 08/08/2016.
 */
public class RequestLocationUpdatesInteractor implements RequestLocationUpdates {


    private Context context;
    private GoogleApiClient googleApiClient;

    public RequestLocationUpdatesInteractor(Context context, GoogleApiClient googleApiClient) {
        this.context = context;
        this.googleApiClient = googleApiClient;
    }

    @Override
    public void startLocationUpdates(final Callback callback) {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setExpirationDuration(1000);
        locationRequest.setFastestInterval(500);
        locationRequest.setInterval(500);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (!PermissionHelper.checkForLocationPermission(context)) {
            PermissionHelper.requestLocationPermission((Activity) context, 1);
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                callback.onLocationChange(location);
            }
        });
    }
}
