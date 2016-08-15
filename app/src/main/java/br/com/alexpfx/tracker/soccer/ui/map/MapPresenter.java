package br.com.alexpfx.tracker.soccer.ui.map;

import br.com.alexpfx.tracker.soccer.presenter.IPresenter;
import br.com.alexpfx.tracker.soccer.ui.map.MapView;

/**
 * Created by alexandre on 07/08/2016.
 */
public interface MapPresenter extends IPresenter<MapView> {
    void startGoogleApiConnection();

    void startRequestLocationUpdates ();
    void stopLocationUpdates();
}
