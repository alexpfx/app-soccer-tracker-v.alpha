package br.com.alexpfx.tracker.soccer.interactor;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by alexandre on 07/08/2016.
 */
public interface ConnectGoogleApi {

    void connect(Callback callback);

    interface Callback {
        void onConnected(GoogleApiClient apiClient);
        void onConnectionFailed();
    }
}
