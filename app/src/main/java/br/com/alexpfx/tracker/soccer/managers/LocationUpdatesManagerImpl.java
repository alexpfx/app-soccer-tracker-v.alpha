package br.com.alexpfx.tracker.soccer.managers;

import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by alexandre on 14/08/2016.
 */
public class LocationUpdatesManagerImpl implements LocationUpdatesManager {

    private GoogleApiClient api;
    private LocationRequest locationRequest;

    public LocationUpdatesManagerImpl(GoogleApiClient api, LocationRequest locationRequest) {
        this.api = api;
        this.locationRequest = locationRequest;
    }


    @Override
    public void requestLocationUpdates(Callback callback){
        if (!api.isConnected()){
            return;
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(api, locationRequest, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                callback.onLocationReceived(location.getLatitude(), location.getLongitude());
            }
        });
    }


    @Override
    public void stopLocationUpdates(){
        LocationServices.FusedLocationApi.removeLocationUpdates(api, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }
        });
    }




}


