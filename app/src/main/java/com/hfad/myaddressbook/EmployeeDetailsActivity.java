package com.hfad.myaddressbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hfad.myaddressbook.model.Employees;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class EmployeeDetailsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private ArrayList<Employees> employee;
    TextView name, city, phone, member, email;
    String empName, empLoc, empCity, empPhone, empMember, empEmail;
    boolean isPermissionGranted;
    GoogleMap googleMaps;
    FloatingActionButton fab;
    private FusedLocationProviderClient mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        name = findViewById(R.id.empName);
        fab = findViewById(R.id.fab);
        checkPermission();
        initMap();

        mLocation = new FusedLocationProviderClient(this);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getCurrLoc();
            }
        });
        city = findViewById(R.id.empCity);
        phone = findViewById(R.id.empPhone);
        member = findViewById(R.id.empMem);
        email = findViewById(R.id.empEmail);

        getData();
        setData();
    }

    @SuppressLint("MissingPermission")
    private void getCurrLoc() {
        mLocation.getLastLocation().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Location location = task.getResult();
                gotoLocation(location.getLatitude(), location.getLongitude());
            }
        });
    }

    private void gotoLocation(double latitude, double longitude) {
        LatLng latlng = new LatLng(latitude, longitude);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlng, 18);
        googleMaps.moveCamera(cameraUpdate);
        googleMaps.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    private void initMap() {
        if (isPermissionGranted) {
            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.mapFragment);
            supportMapFragment.getMapAsync(this);
        }
    }

    private void checkPermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Toast.makeText(EmployeeDetailsActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                        isPermissionGranted = true;
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("packages", getPackageName(), "");
                        intent.setData(uri);
                        startActivity(intent);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    private void getData() {
        if(getIntent().hasExtra("empName") && getIntent().hasExtra("mapFragment")
        && getIntent().hasExtra("empCity") && getIntent().hasExtra("empPhone")
        && getIntent().hasExtra("empMember") && getIntent().hasExtra("empEmail")){
            empName = getIntent().getStringExtra("empName");
            empLoc = getIntent().getStringExtra("mapFragment");
            empCity = getIntent().getStringExtra("empCity");
            empPhone = getIntent().getStringExtra("empPhone");
            empMember = getIntent().getStringExtra("empMember");
            empEmail = getIntent().getStringExtra("empEmail");

        } else{
            Toast.makeText(this, "No Data...", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        name.setText(empName);
        city.setText(empCity);
        phone.setText(empPhone);
        member.setText(empMember);
        email.setText(empEmail);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMaps = googleMap;
//        googleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}