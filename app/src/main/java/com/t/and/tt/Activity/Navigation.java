package com.t.and.tt.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import com.t.and.tt.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Navigation extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    Bitmap marker;
    TextView txtTitle;
    FrameLayout framLoading;
    ImageView imgBack, imgLoader, imgNavigation;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    String longittude, lattittude;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.map);
        imgBack = findViewById(R.id.img_back);
        imgLoader = findViewById(R.id.img_loader);
        txtTitle = findViewById(R.id.txt_title);
        imgNavigation = findViewById(R.id.img_navigation);
        txtTitle.setText(getResources().getString(R.string.area_select));

        // Get the transferred data from source activity.
        Intent intent = getIntent();
        lattittude = intent.getStringExtra("lattittude");
        longittude = intent.getStringExtra("longittude");


        marker = BitmapFactory.decodeResource(getResources(), R.drawable.location);
        imgNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
//                startActivity(intent);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=-33.852, 151.211"));
                startActivity(intent);

            }
        });
//        apiInterface = APIClient.getClient().create(API.class);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(
//                R.layout.check_out_fragment, container, false);

        Hawk.init(getApplicationContext())
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSqliteStorage(getApplicationContext()))
                .setLogLevel(LogLevel.FULL)
                .build();


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


//        return rootView;

    }


//    private void configureCameraIdle() {
//        onCameraIdleListener = new GoogleMap.OnCameraIdleListener() {
//            @Override
//            public void onCameraIdle() {
//
//                LatLng latLng = mMap.getCameraPosition().target;
//                Geocoder geocoder = new Geocoder(CheckOutActivity.this);
//
//                try {
//                    List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
//                    if (addressList != null && addressList.size() > 0) {
//                        String locality = addressList.get(0).getAddressLine(0);
//                        String country = addressList.get(0).getCountryName();
//                        String city = addressList.get(0).getLocality();
//                        try {
//                            if (!locality.isEmpty() && !country.isEmpty())
//                                txt_address.setText(profile_dt.getName()+"\n"+locality + "\n" + city+"\n"+country+"\n"+profile_dt.getPhone()+"\n"+profile_dt.getEmail());
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
////                        Toast.makeText(getApplicationContext(),locality + "  " + country,Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        };
//    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        mMap.setOnCameraIdleListener(onCameraIdleListener);

        mMap = googleMap;
        mMap.setOnCameraIdleListener(onCameraIdleListener);
//        enableMyLocationIfPermitted();

        LatLng sydney = new LatLng(Double.parseDouble(lattittude), Double.parseDouble(longittude));


        googleMap.addMarker(new MarkerOptions().position(sydney)
                .icon(BitmapDescriptorFactory.fromBitmap(marker)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(lattittude), Double.parseDouble(longittude)), 10.0f));



//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(47.6739881, -122.121512), 13));
//
//            CameraPosition cameraPosition = new CameraPosition.Builder()
//                    .target(new LatLng(47.6739881, -122.121512))      // Sets the center of the map to location user
//                    .zoom(17)                   // Sets the zoom
//                    .bearing(90)                // Sets the orientation of the camera to east
//                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
//                    .build();                   // Creates a CameraPosition from the builder
//            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    private void enableMyLocationIfPermitted() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else if (mMap != null) {

            mMap.setMyLocationEnabled(true);
            View locationButton = ((View) mapFragment.getView().findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
            rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            rlp.setMargins(0, 0, 30, 30);
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng((mMap.getMyLocation().),mMap.getMyLocation().getLongitude()), 16));

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocationIfPermitted();
                } else {
                    showDefaultLocation();
                }
                return;
            }

        }
    }

    private void showDefaultLocation() {
        Toast.makeText(this, "Location permission not granted, " +
                        "showing default location",
                Toast.LENGTH_SHORT).show();
        LatLng redmond = new LatLng(47.6739881, -122.121512);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(redmond));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng((47.6739881),47.6739881), 10.0f));
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}
