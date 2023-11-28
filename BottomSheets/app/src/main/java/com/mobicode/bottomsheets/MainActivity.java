package com.mobicode.bottomsheets;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LinearLayout bottomSheet;
    private boolean isBottomSheetExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomSheet = findViewById(R.id.bottomSheet);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        bottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleBottomSheet();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in a location of your choice
        LatLng markerLocation = new LatLng(37.7749, -122.4194);
        mMap.addMarker(new MarkerOptions().position(markerLocation).title("Marker in San Francisco"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerLocation, 12));
    }

    private void toggleBottomSheet() {
        if (isBottomSheetExpanded) {
            // Collapse the bottom sheet
            bottomSheet.setVisibility(View.GONE);
            isBottomSheetExpanded = false;
        } else {
            // Expand the bottom sheet
            bottomSheet.setVisibility(View.VISIBLE);
            isBottomSheetExpanded = true;
        }
    }
}
