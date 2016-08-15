package br.com.alexpfx.tracker.soccer.ui.map;

import br.com.alexpfx.tracker.soccer.managers.GoogleAPIManager;
import br.com.alexpfx.tracker.soccer.managers.LocationUpdatesManager;
import br.com.alexpfx.tracker.soccer.presenter.BasePresenter;

/**
 * Created by alexandre on 07/08/2016.
 */
public class MapPresenterImpl extends BasePresenter<MapView> implements MapPresenter, GoogleAPIManager.Callback, LocationUpdatesManager.Callback {

    private static final String TAG = "MapPresenterImpl";
    private GoogleAPIManager googleAPIManager;
    private LocationUpdatesManager locationUpdatesManager;


    public MapPresenterImpl(GoogleAPIManager googleAPIManager, LocationUpdatesManager locationUpdatesManager) {
        this.googleAPIManager = googleAPIManager;
        this.locationUpdatesManager = locationUpdatesManager;
    }


    @Override
    public void startGoogleApiConnection() {
        googleAPIManager.connect(this);
    }

    @Override
    public void startRequestLocationUpdates() {
        locationUpdatesManager.requestLocationUpdates(this);
    }

    @Override
    public void stopLocationUpdates() {
        locationUpdatesManager.stopLocationUpdates();
    }

    @Override
    public void onConnected() {
        view.showConnected();
    }

    @Override
    public void onConnectionFailed() {
        view.showConnectionFailed();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onLocationReceived(double lat, double lng) {
        view.showNewLocationCoordinates("Lat: " + String.valueOf(lat) + " Long: " + String.valueOf(lng));
    }
}