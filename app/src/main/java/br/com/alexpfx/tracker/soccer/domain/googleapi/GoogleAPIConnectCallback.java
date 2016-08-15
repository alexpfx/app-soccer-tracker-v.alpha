package br.com.alexpfx.tracker.soccer.domain.googleapi;

import com.google.android.gms.common.api.GoogleApiClient;

import br.com.alexpfx.tracker.soccer.domain.base.Interactor;
import br.com.alexpfx.tracker.soccer.domain.base.InteractorCallback;

/**
 * Created by alexandre on 13/08/2016.
 */
public interface GoogleAPIConnectCallback extends InteractorCallback{

    void onConnected(GoogleApiClient apiClient);

    void onConnectionFailed();


}
