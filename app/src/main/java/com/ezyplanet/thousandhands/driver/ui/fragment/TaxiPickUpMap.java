package com.ezyplanet.thousandhands.driver.ui.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




import com.ezyplanet.thousandhands.driver.data.network.googlemaps.AddressItem;
import com.ezyplanet.thousandhands.driver.data.network.googlemaps.GoogleMapsService;
import com.ezyplanet.thousandhands.driver.data.network.googlemaps.ResultWrapper;
import com.ezyplanet.thousandhands.driver.data.network.request.Address;
import com.ezyplanet.thousandhands.driver.ui.listener.IAsyncCallback;
import com.ezyplanet.thousandhands.driver.ui.listener.MapFragmentListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import static com.ezyplanet.thousandhands.driver.util.AppConstants.GOOGLE_MAPS_API_GEOCODE;
import static com.ezyplanet.thousandhands.driver.util.AppConstants.ZOOM_MAP_DEFAULT;
import static com.ezyplanet.thousandhands.driver.util.AppConstants.ZOOM_MAP_LEVEL_17;


public class TaxiPickUpMap extends SupportMapFragment implements

        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnCameraIdleListener,
        GoogleMap.OnCameraMoveStartedListener {

    private static final int REQUEST_ACCESS_FINE_LOCATION_PERMISSION = 1111;
    private GoogleMap map;
    private GoogleApiClient mGoogleApiClient;
    Address currentLocation;
    //private String myPos;

    TaxiMapListener listener;
    private Marker lastOpenedMarker = null;
    Address startMapPosition;
    Address endMapPosition;
    boolean isFirstLaunch;
    MarkerOptions centerMarker;
    List<Marker> makers;
    Marker makerCurrentPosition;

    boolean isDragMap;

    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }

    public void setGoogleApiClient(GoogleApiClient mGoogleApiClient) {
        this.mGoogleApiClient = mGoogleApiClient;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity = (MainActivity) getActivity();
        startMapPosition = new Address();
        endMapPosition = new Address();
        makers = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (map == null) {
            getMapAsync(this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if (view != null &&
//                view.findViewById(Integer.parseInt("1")) != null) {
//            // Get the button view
//            View locationButton = ((View) view.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
//            // and next place it, on bottom right (as Google Maps app)
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
//                    locationButton.getLayoutParams();
//            // position on right bottom
//
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
//                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
//
//            layoutParams.setMargins(0, 0, 30, 30);
//            locationButton.setLayoutParams(layoutParams);
//        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("onMapReade", "ready");

        map = googleMap;
        if (map != null) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_ACCESS_FINE_LOCATION_PERMISSION);
                return;
            }
            map.setMyLocationEnabled(false);
            map.setOnCameraMoveStartedListener(this);
            map.setOnCameraIdleListener(this);
            map.getUiSettings().setMyLocationButtonEnabled(true);
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(ZOOM_MAP_LEVEL_17 + 1);
            map.animateCamera(zoom);
        }
        if (listener != null) {
            listener.onMapReady(googleMap);


        }


    }

    @Override
    public void onResume() {
        super.onResume();
        connectLocationClient();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    private void connectLocationClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    /**
     * Class for retrieving customer location
     */


    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient != null)
            mGoogleApiClient.disconnect();
    }


    @Override
    public void onConnected(Bundle arg0) {
        getCurrentLocation();


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onCameraMoveStarted(int reason) {
        if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
            Log.d("callCameraStartTouchXY", map.getCameraPosition().target.latitude + "," + map.getCameraPosition().target.longitude);
            //  updatePosition(startMapPosition, map.getCameraPosition().target.latitude,map.getCameraPosition().target.longitude);
            isDragMap = true;
        } else if (reason == GoogleMap.OnCameraMoveStartedListener
                .REASON_API_ANIMATION) {
//            Toast.makeText(getActivity(), "The user tapped something on the map.",
//                    Toast.LENGTH_SHORT).show();
            isDragMap = false;
        } else if (reason == GoogleMap.OnCameraMoveStartedListener
                .REASON_DEVELOPER_ANIMATION) {
//            Log.d("callCameraStartAppXY",map.getCameraPosition().target.latitude+","+map.getCameraPosition().target.longitude);

            //  updatePosition(startMapPosition, map.getCameraPosition().target.latitude,map.getCameraPosition().target.longitude);
//            Toast.makeText(getActivity(), "The app moved the camera.",
//                    Toast.LENGTH_SHORT).show();
            isDragMap = false;
        }

    }

    @Override
    public void onCameraIdle() {
        if (!isFirstLaunch) {
            isFirstLaunch = true;
        } else {
            if(isDragMap) {
                updatePosition(startMapPosition, map.getCameraPosition().target.latitude, map.getCameraPosition().target.longitude);

            }
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // updatePosition(endMapPosition, map.getCameraPosition().target.latitude,map.getCameraPosition().target.longitude);
    }


    void updatePosition(Address address, double latitude, double longitude) {

        address.setLatitude(latitude);
        address.setLongitude(longitude);
        getAddressFromLatLong(address);

    }

    void centerCurrentLocONMap(LatLng latLng) {
        if (currentLocation == null) {
            currentLocation = new Address();
        }
        currentLocation.setLatitude(latLng.latitude);
        currentLocation.setLongitude(latLng.longitude);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(getCurrentLatLng(), ZOOM_MAP_DEFAULT+3));
        getAddressFromLatLong(currentLocation);


    }

    void centerCurrentLocONMap(Address address) {
        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
        currentLocation = address;
        isFirstLaunch = false;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_MAP_LEVEL_17 + 1));


    }

    LatLng getCurrentLatLng() {
        return new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
    }


    public TaxiMapListener getListener() {
        return listener;
    }

    public void setListener(TaxiMapListener listener) {
        this.listener = listener;
    }


    public interface TaxiMapListener extends MapFragmentListener {


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        listener.onConnectionFailed(connectionResult);

    }

    void getCurrentLocation() {
        try {
            if (mGoogleApiClient != null) {
                Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                LatLng latLng=null;


                if (location != null) {
                    latLng = new LatLng(location.getLatitude(), location.getLongitude());

                } else {
//                    if (UserManager.getInstance().getAddress() == null)
//                        return;
//                    latLng = new LatLng(UserManager.getInstance().getAddress().getLatitude(), UserManager.getInstance().getAddress().getLongitude());

                }
                if (!isFirstLaunch) {
                    centerCurrentLocONMap(latLng);
                }


            }
        } catch (SecurityException error) {
            error.printStackTrace();
        }
    }

    private void getAddressFromLatLong(Address address) {
        if (address == null)
            return;

        String latlng = address.getLatitude() + "," + address.getLongitude();

        GoogleMapsService service = new GoogleMapsService(new IAsyncCallback<String>() {

            @Override
            public void handlePreExecute() {
                //   listener.onProcessing();
            }

            @Override
            public void handlePostExecute(String result) {

                AddressItem item;

                if (result != null && !result.contains("ZERO_RESULTS")) {
                    Gson gson = new Gson();
                    ResultWrapper wrapper = gson.fromJson(result, ResultWrapper.class);
                    if (wrapper.getStatus().equals("OK") && wrapper.getResults() != null) {
                        if (wrapper.getResults().size() > 0) {
                            item = wrapper.getResults().get(0);

                            address.setAddressFrom(item);
                            // listener.onDone(address);


                        }
                    } else {


//                        showShortMessage(R.string.can_not_get_detail);

                        // listener.onDone(null);
                    }

                } else {
//                    showShortMessage(R.string.can_not_get_detail);
                    listener.onDone(null);


                }
            }
        }
                , latlng);
        service.execute(GOOGLE_MAPS_API_GEOCODE);

    }









}