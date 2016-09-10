package br.com.alexpfx.tracker.soccer;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alexpfx.tracker.soccer.managers.GoogleAPIManager;
import br.com.alexpfx.tracker.soccer.managers.GoogleAPIManagerImpl;
import br.com.alexpfx.tracker.soccer.managers.LocationUpdatesManager;
import br.com.alexpfx.tracker.soccer.managers.LocationUpdatesManagerImpl;
import br.com.alexpfx.tracker.soccer.managers.MapManager;
import br.com.alexpfx.tracker.soccer.managers.MapManagerImpl;
import br.com.alexpfx.tracker.soccer.ui.map.MapPresenter;
import br.com.alexpfx.tracker.soccer.ui.map.MapPresenterImpl;
import br.com.alexpfx.tracker.soccer.ui.map.MapView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements MapView, OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {
    private static final String TAG = "MapFragment";
    boolean show_flag = true;
    private final double OFFSET = (0.0003);

    private MapPresenter mapPresenter;

    private Bitmap selectedFlag;
    private Bitmap flag;
    private List<Marker> markers = new ArrayList<>();
    private List<Polygon> polygons = new ArrayList<>();

    private GoogleAPIManager googleAPIManager;
    private LocationUpdatesManager locationUpdatesManager;
    private MapManager mapManager;


    public MapFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, v);
        FragmentManager supportFragmentManager = getChildFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) supportFragmentManager.findFragmentById(R.id.map);
        setHasOptionsMenu(true);

        mapFragment.getMapAsync(this);

        GoogleApiClient apiClient = new GoogleApiClient.Builder(getContext()).addApi(LocationServices.API).build();

        googleAPIManager = new GoogleAPIManagerImpl(apiClient);

        LocationRequest locationRequest = LocationRequestHelper.createLocationRequest(getContext(), 1000L, 1000L, 1000L, LocationRequest.PRIORITY_HIGH_ACCURACY);
        mapPresenter = new MapPresenterImpl(googleAPIManager, new LocationUpdatesManagerImpl(apiClient, locationRequest));
        mapPresenter.attachView(this);
        mapPresenter.startGoogleApiConnection();

        createBitmaps();

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    private void createBitmaps() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flag);
        flag = Bitmap.createScaledBitmap(bitmap, 100, 100, true);

        selectedFlag = Bitmap.createBitmap(flag);
        changeBitmapColor(selectedFlag, Color.RED);
    }

    private void changeBitmapColor(Bitmap sourceBitmap, int color) {

        Paint paint = new Paint();
        ColorFilter filter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN);
        paint.setColorFilter(filter);

        Canvas canvas = new Canvas(sourceBitmap);
        canvas.drawBitmap(sourceBitmap, 0, 0, paint);

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.map_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_draw_field:
                return prepareDrawField();
        }

        return super.onOptionsItemSelected(item);
    }


    private boolean prepareDrawField() {
        return false;
    }


    @Override
    public void showConnected() {
        Toast.makeText(getContext(), "Connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showConnectionFailed() {
        Toast.makeText(getContext(), "Connection failed", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showNewLocationOnMap(double lat, double lng) {
        mapManager.drawFieldAroundLocation(new LatLng(lat, lng));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapManager = new MapManagerImpl(googleMap);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if (theMarker != null && theMarker.equals(marker)) {
            return true;
        }
        if (theMarker != null) {
            theMarker.setIcon(BitmapDescriptorFactory.fromBitmap(flag));
        }

        theMarker = marker;
        theMarker.setIcon(BitmapDescriptorFactory.fromBitmap(selectedFlag));

        theMarker = marker;
        theMarker.showInfoWindow();

        return true;
    }

    private Marker theMarker;

    @Override
    public void onMapClick(LatLng latLng) {


    }

    public void drawPolygons() {
        removePolygons();
        PolygonOptions polygonOptions = new PolygonOptions().strokeWidth(2f).geodesic(true).fillColor(Color.argb(100, 0, 200, 100));
        for (Marker m : markers) {
            polygonOptions.add(m.getPosition());
        }
//        polygons.add(map.addPolygon(polygonOptions));
    }


    private void removePolygons() {
        for (Polygon p : polygons) {
            p.remove();
        }
    }


}
