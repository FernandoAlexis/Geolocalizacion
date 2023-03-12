package com.android.geolocalizacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    EditText txtLatitud, txtLongitud;
    GoogleMap mMap;
    Button compartir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);
        compartir = findViewById(R.id.btnCompartir);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "https://maps.google.com/?q="+txtLatitud.getText()+","+txtLongitud.getText());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);

        LatLng objeto = new LatLng(13.6986087,-89.2289306);
        mMap.addMarker(new MarkerOptions().position(objeto).title("El salvador"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(objeto));

    }

    @Override
    public void onMapClick(LatLng latLng) {
        txtLatitud.setText("" + latLng.latitude);
        txtLongitud.setText("" + latLng.longitude);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        txtLatitud.setText("" + latLng.latitude);
        txtLongitud.setText("" + latLng.longitude);
    }
}