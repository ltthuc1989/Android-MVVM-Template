package com.ezyplanet.thousandhands.driver.util.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.core.app.ActivityCompat;


/**
 * @author Quentin Klein <klein.quentin@gmail.com>, Yasir.Ali <ali.yasir0@gmail.com>
 * <p>
 * Helper that tracks customer location
 * </p>
 */
public abstract class LocationTracker implements LocationListener {

    /**
     * Tag used for logs
     */
    private static final String TAG = "LocationTracker";
    /**
     * The customer location
     * This value is static because, wherever you call a LocationTracker, customer location is the same
     */
    public Location sLocation;
    private Location prevLocation;

    /**
     * The manager used to track the sLocation
     */
    private LocationManager mLocationManagerService;
    /**
     * Indicates if Tracker is listening for updates or not
     */
    private boolean mIsListening = false;
    /**
     * Indicates if Tracker has found the location or not
     */
    private boolean mIsLocationFound = false;

    /**
     * Any context useful
     */
    private Context mContext;

    /**
     * Settings for the tracker
     */
    private TrackerSettings mTrackerSettings;

    /**
     * Default LocationTracker, uses default values for settings
     *
     * @param context Android context, uiContext is not mandatory.
     */
//    @RequiresPermission(anyOf = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
//    public LocationTracker(@NonNull Context context) {
//        this(context, DEFAULT);
//    }

    /**
     * Customized LocationTracker, uses the specified services and starts listening for a location.
     *
     * @param context         Android context, uiContext is not mandatory.
     * @param trackerSettings {@link TrackerSettings}, the tracker settings
     */
    @RequiresPermission(anyOf = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    public LocationTracker(@NonNull Context context, @NonNull TrackerSettings trackerSettings) {
        this.mContext = context;
        this.mTrackerSettings = trackerSettings;

        // Get the location manager
        this.mLocationManagerService = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // default
        if (sLocation == null && trackerSettings.shouldUseGPS()) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the customer grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            sLocation = mLocationManagerService.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if (sLocation == null && trackerSettings.shouldUseNetwork()) {
            sLocation = mLocationManagerService.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (sLocation == null && trackerSettings.shouldUsePassive()) {
            sLocation = mLocationManagerService.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }
    }

    /**
     * Deprecated since 3.1
     * Used to make the tracker listening for location updates
     *
     * @see #startListening()
     */
    @Deprecated
    public final void startListen() {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the customer grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startListening();
    }

    /**
     * Make the tracker listening for location updates
     */
    @RequiresPermission(anyOf = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    public final void startListening() {
        if (!mIsListening) {
            Log.i(TAG, "LocationTracked is now listening for location updates");
            // Listen for GPS Updates
            if (mTrackerSettings.shouldUseGPS()) {
                if (LocationUtils.isGpsProviderEnabled(mContext)) {
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    mLocationManagerService.requestLocationUpdates(LocationManager.GPS_PROVIDER, mTrackerSettings.getTimeBetweenUpdates(), mTrackerSettings.getMetersBetweenUpdates(), this);
                } else {
                    onProviderError(new ProviderError(LocationManager.GPS_PROVIDER, "Provider is not enabled"));
                }

            }
            // Listen for Network Updates
            if (mTrackerSettings.shouldUseNetwork()) {
                if (LocationUtils.isNetworkProviderEnabled(mContext)) {
                    mLocationManagerService.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, mTrackerSettings.getTimeBetweenUpdates(), mTrackerSettings.getMetersBetweenUpdates(), this);
                } else {
                    onProviderError(new ProviderError(LocationManager.NETWORK_PROVIDER, "Provider is not enabled"));
                }


            }
            // Listen for Passive Updates
            if (mTrackerSettings.shouldUsePassive()) {
                if (LocationUtils.isPassiveProviderEnabled(mContext)) {
                    mLocationManagerService.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, mTrackerSettings.getTimeBetweenUpdates(), this.mTrackerSettings.getMetersBetweenUpdates(), this);
                } else {
                    onProviderError(new ProviderError(LocationManager.PASSIVE_PROVIDER, "Provider is not enabled"));
                }

            }
            mIsListening = true;

            // If customer has set a timeout
            if (mTrackerSettings.getTimeout() != -1) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!mIsLocationFound && mIsListening) {
                            Log.i(TAG, "No location found in the meantime");
                            stopListening();
                            onTimeout();
                        }
                    }
                }, mTrackerSettings.getTimeout());
            }
        } else {
            Log.i(TAG, "Relax, LocationTracked is already listening for location updates");
        }
    }

    /**
     * Deprecated since 3.1
     * Used to make the tracker stops listening for location updates
     *
     * @see #stopListening()
     */
    @Deprecated
    public final void stopListen() {
        stopListening();
    }

    /**
     * Make the tracker stops listening for location updates
     */
    public final void stopListening() {
        if (mIsListening) {
            Log.i(TAG, "LocationTracked has stopped listening for location updates");
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the customer grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mLocationManagerService.removeUpdates(this);
            mIsListening = false;
        } else {
            Log.i(TAG, "LocationTracked wasn't listening for location updates anyway");
        }
    }

    /**
     * Best effort, it calls {@link #onLocationChanged(Location)} with static field named {@link #sLocation} if it is not null
     */
    public final void quickFix() {
        if (sLocation != null) {
            onLocationChanged(sLocation);
        }
    }

    public Location getLastKnownLoction() {
        if ( mTrackerSettings.shouldUseGPS()) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the customer grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            sLocation = mLocationManagerService.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if (mTrackerSettings.shouldUseNetwork()) {
            sLocation = mLocationManagerService.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (mTrackerSettings.shouldUsePassive()) {
            sLocation = mLocationManagerService.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }
        return sLocation;
    }

    /**
     * Getter used to know if the Tracker is listening at this time.
     *
     * @return true if the tracker is listening, false otherwise
     */
    public final boolean isListening() {
        return mIsListening;
    }

    /**
     * Called when the tracker had found a location
     *
     * @see LocationListener#onLocationChanged(Location)
     */
    @Override
    public final void onLocationChanged(@NonNull Location location) {
        Log.i(TAG, "Location has changed, new location is " + location);
        Log.i(TAG, location.getProvider());
        sLocation = new Location(location);
        mIsLocationFound = true;
        if (isMove()) {
            onLocationFound(location);
        }
    }

    /**
     * Called when the tracker had found a location
     *
     * @param location the found location
     */
    public abstract void onLocationFound(@NonNull Location location);

    /**
     * Called when the tracker had not found any location and the timeout just happened
     */
    public abstract void onTimeout();

    /**
     * Called when a provider has been disabled.
     * By default, this method do nothing but a Log on i
     *
     * @see LocationListener#onProviderDisabled(String)
     */
    @Override
    public void onProviderDisabled(@NonNull String provider) {
        // By default do nothing but log
        Log.i(TAG, "Provider (" + provider + ") has been disabled");
    }

    /**
     * Called when a provider has been enabled.
     * By default, this method do nothing but a Log on i
     *
     * @see LocationListener#onProviderEnabled(String)
     */
    @Override
    public void onProviderEnabled(@NonNull String provider) {
        // By default do nothing but log
        Log.i(TAG, "Provider (" + provider + ") has been enabled");
    }

    /**
     * Called when status has changed.
     * By default, this method do nothing but a Log on i
     *
     * @see LocationListener#onStatusChanged(String, int, Bundle)
     */
    @Override
    public void onStatusChanged(@NonNull String provider, int status, Bundle extras) {
        // By default do nothing but log
        Log.i(TAG, "Provider (" + provider + ") status has changed, new status is " + status);
    }

    /**
     * Triggered when there was an error with a provider, most of the time, when this one is not enabled
     *
     * @param providerError the provider error
     */
    public void onProviderError(@NonNull ProviderError providerError) {
        // By default do nothing but log
        Log.w(TAG, "Provider (" + providerError.getProvider() + ")", providerError);
    }

    boolean isMove() {
        if (prevLocation == null) {
            prevLocation = sLocation;
            return true;
        }
        if (prevLocation.distanceTo(sLocation) > 0) {
            return true;
        }
        return false;
    }

}