package com.ezyplanet.thousandhands.driver.ui.listener;


import android.os.Bundle;

import com.ezyplanet.thousandhands.driver.data.network.request.Address;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;

public interface MapFragmentListener {
    void onConnected(Bundle arg0, GoogleApiClient googleApiClient);
    void onConnectionFailed(ConnectionResult arg0);

    void onMapReady(GoogleMap googleMap);
    void onDone(Address address);
    void onProcessing();
}
