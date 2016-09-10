package br.com.alexpfx.tracker.soccer.managers;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alexandre on 22/08/2016.
 */
public class MapManagerImpl implements MapManager {
    private List<Marker> markers = new ArrayList<>();
    private final double OFFSET = (0.0003);
    private final GoogleMap map;

    private MarkerOptions markerOptions = new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));


    public MapManagerImpl(GoogleMap googleMap) {
        this.map = googleMap;
    }

    @Override
    public void moveCamera(LatLng latLng, float zoom) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    @Override
    public void drawFieldAroundLocation(LatLng newLatLong) {
        map.clear();
        LatLng l0 = new LatLng(newLatLong.latitude + OFFSET, newLatLong.longitude - OFFSET);
        LatLng l1 = new LatLng(newLatLong.latitude + OFFSET, newLatLong.longitude + OFFSET);
        LatLng l2 = new LatLng(newLatLong.latitude - OFFSET, newLatLong.longitude - OFFSET);
        LatLng l3 = new LatLng(newLatLong.latitude - OFFSET, newLatLong.longitude + OFFSET);

        List<LatLng> latLngs = Arrays.asList(l0, l1, l3, l2);
        for (LatLng latLng : latLngs) {
            Marker marker = map.addMarker(markerOptions);
            marker.setPosition(latLng);
            markers.add(marker);
        }
    }


}
