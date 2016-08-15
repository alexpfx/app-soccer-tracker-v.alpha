package br.com.alexpfx.tracker.soccer.domain.googleapi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.concurrent.Executor;

import br.com.alexpfx.tracker.soccer.domain.base.BaseInteractor;
import br.com.alexpfx.tracker.soccer.domain.base.InteractorCallback;
import br.com.alexpfx.tracker.soccer.domain.base.MainThread;

/**
 * Created by alexandre on 13/08/2016.
 */
public class GoogleAPIConnectImpl extends BaseInteractor<GoogleAPIConnectCallback> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;
    private static final String TAG = "GoogleAPIConnectImpl";

    public GoogleAPIConnectImpl(Executor threadExecutor, MainThread mainThread, Context context) {
        super(threadExecutor, mainThread, context);
    }

    @Override
    public void run() {
        googleApiClient = new GoogleApiClient.Builder(context).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        googleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mainThread.post(() -> callback.onConnected(googleApiClient));
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended: " + i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mainThread.post(() -> callback.onConnectionFailed());
    }


}
