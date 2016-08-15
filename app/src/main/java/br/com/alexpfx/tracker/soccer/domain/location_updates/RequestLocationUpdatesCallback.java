package br.com.alexpfx.tracker.soccer.domain.location_updates;

import android.location.Location;

import br.com.alexpfx.tracker.soccer.domain.base.InteractorCallback;

/**
 * Created by alexandre on 14/08/2016.
 */
public interface RequestLocationUpdatesCallback extends InteractorCallback{
    void onLocationChanged(Location location);
}
