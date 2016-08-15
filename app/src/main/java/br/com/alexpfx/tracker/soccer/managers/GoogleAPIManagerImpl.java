package br.com.alexpfx.tracker.soccer.managers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by alexandre on 14/08/2016.
 */
public final class GoogleAPIManagerImpl implements GoogleAPIManager {

    private GoogleApiClient api;


    public GoogleAPIManagerImpl(GoogleApiClient api) {
        this.api = api;
    }

    @Override
    public void connect(Callback callback) {
        if (api == null) {
            throw new IllegalStateException("inject the API");
        }

        api.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {
                callback.onConnected();
            }

            @Override
            public void onConnectionSuspended(int i) {
                callback.onConnectionSuspended(i);
            }
        });

        api.registerConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                callback.onConnectionFailed();
            }
        });

        api.connect();

    }

    @Override
    public void disconnect() {
        api.disconnect();
    }


}
