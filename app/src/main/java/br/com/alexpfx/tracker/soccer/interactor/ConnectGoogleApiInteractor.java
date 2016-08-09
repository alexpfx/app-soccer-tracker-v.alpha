package br.com.alexpfx.tracker.soccer.interactor;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by alexandre on 07/08/2016.
 */
public class ConnectGoogleApiInteractor implements ConnectGoogleApi {
    private Context context;

    private GoogleApiClient googleApiClient;


    public ConnectGoogleApiInteractor(Context context) {
        this.context = context;
    }

    @Override
    public void connect(final Callback callback) {


        googleApiClient = new GoogleApiClient.Builder(context).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                callback.onConnectionFailed();
            }
        }).addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                callback.onConnected(googleApiClient);
            }

            @Override
            public void onConnectionSuspended(int i) {

            }
        }).addApi(LocationServices.API).build();

        googleApiClient.connect();


    }


}
