package com.camilobc.nerby_hospital;

import android.*;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa1Activity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // Add a marker in Sydney and move the camera

        LatLng leon13= new LatLng(6.266872, -75.565172);
        mMap.addMarker(new MarkerOptions().position(leon13).title("Clinica Leon 13").snippet("Clinica").icon(BitmapDescriptorFactory.fromResource(R.drawable.hosp1)));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(udea));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(leon13,15)); //entre mas pequeño el numero mas alto el mapa

        LatLng sanvicente= new LatLng(6.263865, -75.565188);
        mMap.addMarker(new MarkerOptions().position(sanvicente).title("Hospital San Vicente").snippet("Hospital").icon(BitmapDescriptorFactory.fromResource(R.drawable.hosp2)));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(udea));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sanvicente,15)); //entre mas pequeño el numero mas alto el mapa

        LatLng saludcoop= new LatLng(6.257647, -75.565424);
        mMap.addMarker(new MarkerOptions().position(saludcoop).title("Saludcoop Prado").snippet("Clinica").icon(BitmapDescriptorFactory.fromResource(R.drawable.hosp3)));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(udea));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(saludcoop,15)); //entre mas pequeño el numero mas alto el mapa
    }
}
