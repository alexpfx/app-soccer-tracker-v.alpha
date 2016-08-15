package br.com.alexpfx.tracker.soccer.managers;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Observable;

/**
 * Created by alexandre on 14/08/2016.
 */
public interface GoogleAPIManager  {
    void connect (Callback callback);
    void disconnect();

    interface Callback {
        void onConnected();
        void onConnectionFailed();
        void onConnectionSuspended(int i);
    }
}
