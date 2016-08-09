package br.com.alexpfx.tracker.soccer;


import android.graphics.Rect;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import br.com.alexpfx.tracker.soccer.presenter.MapPresenter;
import br.com.alexpfx.tracker.soccer.presenter.MapPresenterImpl;
import br.com.alexpfx.tracker.soccer.view.FieldFrame;
import br.com.alexpfx.tracker.soccer.view.MapView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements MapView, OnMapReadyCallback {
    private static final String TAG = "MapFragment";

    private MapPresenter mapPresenter;
    private GoogleMap map;

    @BindView(R.id.fieldFrame)
    FieldFrame fieldFrame;


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

        mapPresenter = new MapPresenterImpl(getContext());
        mapPresenter.attachView(this);
        mapPresenter.connectGoogleApi();
        return v;
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
        fieldFrame.setVisibility(toggleVisibility(fieldFrame.getVisibility()));
        return false;
    }

    private int toggleVisibility(int visibility) {
        return visibility == View.VISIBLE ? View.GONE : View.VISIBLE;
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
    public void showNewLocationOnMap(Location location) {
        if (map == null) {
            return;
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LocationHelper.latLng(location), 18));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        fieldFrame.setMap(googleMap);


    }
}
