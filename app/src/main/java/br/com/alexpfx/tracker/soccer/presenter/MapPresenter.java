package br.com.alexpfx.tracker.soccer.presenter;

import br.com.alexpfx.tracker.soccer.view.MapView;

/**
 * Created by alexandre on 07/08/2016.
 */
public interface MapPresenter extends IPresenter <MapView>{
    void connectGoogleApi();
}
