package com.mobicode.locationapp;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView txtLatitud, txtLongitud, txtProveedor, txtAccuracy, txtTIFF, txtChProvider;
    private Button btnShow, btnProvider;
    private String lat, lon;
    private long uptimeToResume;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);
        txtProveedor = findViewById(R.id.txtProveedor);
        txtAccuracy = findViewById(R.id.txtAccuracy);
        txtTIFF = findViewById(R.id.txtTTFF);
        txtChProvider = findViewById(R.id.txtChProveedor);

        btnProvider = findViewById(R.id.btnProvider);
        btnShow = findViewById(R.id.btnShow);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri  uri = Uri.parse("Geo: "+ lat + ", " + lon);
                startActivity(new Intent( Intent.ACTION_VIEW,uri));
            }
        });

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Locale locale = Locale.getDefault();
        lat = String.format(locale,"%.5f",location.getLatitude());
        lon = String.format(locale,"%.5f",location.getLongitude());

        txtLatitud.setText(lat);
        txtLongitud.setText(lon);
        txtProveedor.setText(String.valueOf(location.getProvider()));
        txtAccuracy.setText(String.valueOf(location.getAccuracy() + " " + getResources().getString(R.string.metersUnit)));

        long timeToFix = SystemClock.uptimeMillis() - uptimeToResume;
        txtTIFF.setText(String.valueOf(timeToFix));
        findViewById(R.id.txtTTFF).setVisibility(View.VISIBLE);
        findViewById(R.id.txtAccuracy).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List <String> enabledProviders;
        StringBuffer stringBuffer = new StringBuffer();
        Criteria criteria = new Criteria();

        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        enabledProviders = locationManager.getProviders(criteria,true);
        if (enabledProviders==null){
            txtProveedor.setText("");
        }else{
            for (String enabledProvider :
                 enabledProviders) {
                stringBuffer.append(enabledProvider).append(", ");
                try {
                    locationManager.requestSingleUpdate(enabledProvider,this, null);
                }catch (SecurityException ex){
                    //ex.getMessage();
                    ex.printStackTrace();
                }
            }
            txtProveedor.setText(stringBuffer);
        }
        uptimeToResume = SystemClock.uptimeMillis();
        txtLatitud.setText(" ");
        txtLongitud.setText(" ");
        txtAccuracy.setText(" ");
        txtProveedor.setText(" ");
        txtTIFF.setText(" ");
        txtTIFF.setText(R.string.secondsUnit);

        findViewById(R.id.txtAccuracy).setVisibility(View.GONE);
        findViewById(R.id.txtTTFF).setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            locationManager.removeUpdates(this);
        }catch (SecurityException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}