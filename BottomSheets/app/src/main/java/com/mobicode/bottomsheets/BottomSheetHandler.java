package com.mobicode.bottomsheets;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class BottomSheetHandler implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LinearLayout bottomSheet;
    private boolean isBottomSheetExpanded = false;

    public BottomSheetHandler(Context context) {
        AppCompatActivity activity = (AppCompatActivity) context;
        bottomSheet = activity.findViewById(R.id.bottomSheet);

        bottomSheet.setOnClickListener(v -> toggleBottomSheet());
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
