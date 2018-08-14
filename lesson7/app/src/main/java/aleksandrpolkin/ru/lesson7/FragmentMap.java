package aleksandrpolkin.ru.lesson7;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import aleksandrpolkin.ru.lesson7.data.ObjectsData;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMap extends Fragment {

    private static final String ARGUMENT_FRAGMENT_MAP = "arg_frag_map";
    static final String FRAGMENT_MAP_TAG = "frag_map_tag";
    private SupportMapFragment mapFragment;
    private float anchorMarker = (float) 0.5;
    private List<ObjectsData> objectsData;
    final int BEGIN_ZOOM = 10;
    private CheckTime checkTime = new CheckTime();
    private OnMyGetTextForActivity onMyGetTextForActivity;
    FusedLocationProviderClient fusedLocationProviderClient;
    int i;
    private static final int MY_PERMISSION_FINE_LOCATION = 1;
    GoogleMap googleMaps;
    Location lastLocation;
    Marker marker;

    public static FragmentMap createInstance(List<ObjectsData> objectsData) {
        FragmentMap fragment = new FragmentMap();
        Bundle arg = new Bundle();
        arg.putParcelableArrayList(ARGUMENT_FRAGMENT_MAP, (ArrayList<? extends Parcelable>) objectsData);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        View cardOnMap = view.findViewById(R.id.card);
        TextView textViewName = view.findViewById(R.id.textView_name);
        TextView textViewTime = view.findViewById(R.id.textView_time);
        ImageView imageViewBig = view.findViewById(R.id.imageView);

        if (getArguments() != null) {
            this.objectsData = getArguments().getParcelableArrayList(ARGUMENT_FRAGMENT_MAP);
            getArguments().remove(ARGUMENT_FRAGMENT_MAP);
        }
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(googleMap -> {
                googleMaps = googleMap;
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(view.getContext());
                if (ContextCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSION_FINE_LOCATION);
                }
                googleMaps.getUiSettings().setZoomControlsEnabled(true);
                        if(objectsData!= null) {
                            for (int i = 0; i < objectsData.size(); i++) {
                                LatLng latLng = new LatLng(objectsData.get(i).getLat(), objectsData.get(i).getLng());
                                googleMap.addMarker(new MarkerOptions()
                                        .position(latLng)
                                        .title(objectsData.get(i).getName())
                                        .anchor(anchorMarker, anchorMarker)
                                        .icon(BitmapDescriptorFactory.fromResource(checkTime.checkTimeBitmap(objectsData.get(i).getDivorces()))))
                                        .setTag(i);

                                if (i == objectsData.size() - 1) {
                                    CameraUpdate zoom;
                                    zoom = CameraUpdateFactory.newLatLngZoom(new LatLng(objectsData.get(i).getLat(), objectsData.get(i).getLng()), BEGIN_ZOOM);
                                    googleMap.animateCamera(zoom);
                                }
                            }
                        }
                googleMaps.setOnMarkerClickListener(marker -> {
                    i = (int) marker.getTag();
                    cardOnMap.setVisibility(View.VISIBLE);
                    textViewName.setText(objectsData.get(i).getName());
                    textViewTime.setText(checkTime.getOpenBridgeTime(objectsData.get(i).getDivorces()));
                    imageViewBig.setImageResource(checkTime.checkTimeDrawable(objectsData.get(i).getDivorces()));
                    return true;
                });
                googleMaps.setOnMapClickListener(latLng -> cardOnMap.setVisibility(View.GONE));
            });
            onMyGetTextForActivity = (OnMyGetTextForActivity) view.getContext();
            cardOnMap.setOnClickListener(v ->
                    onMyGetTextForActivity.setTextForActivity(objectsData.get(i), textViewTime.getText().toString(),
                            checkTime.checkTimeDrawable(objectsData.get(i).getDivorces()),OnMyGetTextForActivity.map));
        }

        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
        return view;
    }
    private LocationRequest createLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();

        locationRequest.setInterval(10000);

        locationRequest.setFastestInterval(5000);

        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        return locationRequest;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSION_FINE_LOCATION){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                googleMaps.getUiSettings().setMyLocationButtonEnabled(true);
                googleMaps.setMyLocationEnabled(true);
//                fusedLocationProviderClient.requestLocationUpdates(createLocationRequest(),
//                        new LocationCallback() {
//                            @Override
//                            public void onLocationResult(LocationResult locationResult) {
//                                if (locationResult == null) {
//                                    return;
//                                }
//                                for (Location location : locationResult.getLocations()) {
//                                        Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());
//                                        lastLocation = location;
//                                        if (marker != null) {
//                                            marker.remove();
//                                        }
//
//                                        //Place current location marker
//                                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                                        MarkerOptions markerOptions = new MarkerOptions();
//                                        markerOptions.position(latLng);
//                                        markerOptions.title("Current Position");
//                                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//                                        marker = googleMaps.addMarker(markerOptions);
//                                }
//                }}, null);
            }
        }
    }

}
