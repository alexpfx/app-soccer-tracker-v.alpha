package br.com.alexpfx.tracker.soccer.domain.location_updates;

import android.app.Activity;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.concurrent.Executor;

import br.com.alexpfx.tracker.soccer.PermissionHelper;
import br.com.alexpfx.tracker.soccer.domain.base.BaseInteractor;
import br.com.alexpfx.tracker.soccer.domain.base.Interactor;
import br.com.alexpfx.tracker.soccer.domain.base.MainThread;

/**
 * Created by alexandre on 13/08/2016.
 */
public class RequestLocationUpdatesImpl extends BaseInteractor<RequestLocationUpdatesCallback> implements LocationListener {

    public static final int EXPIRATION_DURATION = 1000;
    public static final int FASTEST_INTERVAL = 500;
    public static final int INTERVAL = 500;
    public static final int PRIORITY_HIGH_ACCURACY = LocationRequest.PRIORITY_HIGH_ACCURACY;

    private GoogleApiClient googleApiClient;

    public RequestLocationUpdatesImpl(Executor threadExecutor, MainThread mainThread, Context context, GoogleApiClient apiClient) {
        super(threadExecutor, mainThread, context);
        googleApiClient = apiClient;
    }

    @Override
    public void run() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setExpirationDuration(EXPIRATION_DURATION);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);
        locationRequest.setInterval(INTERVAL);
        locationRequest.setPriority(PRIORITY_HIGH_ACCURACY);

        if (!PermissionHelper.checkForLocationPermission(context)) {
            PermissionHelper.requestLocationPermission((Activity) context, 1);
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);

    }

    @Override
    public void onLocationChanged(final Location location) {
        mainThread.post(() -> callback.onLocationChanged(location));
    }
}
