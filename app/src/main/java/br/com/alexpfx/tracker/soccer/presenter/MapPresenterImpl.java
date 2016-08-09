package br.com.alexpfx.tracker.soccer.presenter;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;

import br.com.alexpfx.tracker.soccer.interactor.ConnectGoogleApi;
import br.com.alexpfx.tracker.soccer.interactor.ConnectGoogleApiInteractor;
import br.com.alexpfx.tracker.soccer.interactor.RequestLocationUpdates;
import br.com.alexpfx.tracker.soccer.interactor.RequestLocationUpdatesInteractor;
import br.com.alexpfx.tracker.soccer.view.MapView;

/**
 * Created by alexandre on 07/08/2016.
 */
public class MapPresenterImpl extends BasePresenter<MapView> implements MapPresenter, ConnectGoogleApi.Callback, RequestLocationUpdates.Callback {

    private static final String TAG = "MapPresenterImpl";
    private ConnectGoogleApi connectGoogleApi;
    private Context context;

    public MapPresenterImpl(Context context) {
        this.context = context;
        this.connectGoogleApi = new ConnectGoogleApiInteractor(context);

    }


    @Override
    public void connectGoogleApi() {
        this.connectGoogleApi.connect(this);
    }

    @Override
    public void onConnected(GoogleApiClient apiClient) {
        new RequestLocationUpdatesInteractor(context, apiClient).startLocationUpdates(this);

        if (isViewAttached()) {
            view.showConnected();
            Log.d(TAG, "onConnected: " + apiClient);
        }
    }

    @Override
    public void onConnectionFailed() {
        if (!isViewAttached()) {
            return;
        }

        view.showConnectionFailed();
    }

    @Override
    public void onLocationChange(Location location) {
        view.showNewLocationOnMap(location);
    }
}
