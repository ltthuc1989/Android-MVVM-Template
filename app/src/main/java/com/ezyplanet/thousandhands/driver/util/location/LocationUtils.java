package com.ezyplanet.thousandhands.driver.util.location;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;


import com.ezyplanet.thousandhands.driver.R;
import com.ezyplanet.thousandhands.driver.ui.activity.home.TaxiHomeActivity;
import com.ezyplanet.thousandhands.driver.util.AppConstants;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.annotation.UiThread;
import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * @author Quentin Klein <klein.quentin@gmail.com>
 *         <p>
 *         Helper for providers and locations
 *         </p>
 */
public class LocationUtils {

    /**
     * Check if the gps provider is enabled or not
     *
     * @param context any context
     * @return true if gps provider is enabled, false otherwise
     */
//    public static AlertDialog askGPSDialog;
    public static boolean isGpsProviderEnabled(@NonNull Context context) {
        return isProviderEnabled(context, LocationManager.GPS_PROVIDER);

    }

    /**
     * Check if the network provider is enabled or not
     *
     * @param context any context
     * @return true if the network provider is enabled, false otherwise
     */
    public static boolean isNetworkProviderEnabled(@NonNull Context context) {
        return isProviderEnabled(context, LocationManager.NETWORK_PROVIDER);
    }

    /**
     * Check if the passive provider is enabled or not
     *
     * @param context any context
     * @return true if the passive provider is enabled, false otherwise
     */
    public static boolean isPassiveProviderEnabled(@NonNull Context context) {
        return isProviderEnabled(context, LocationManager.PASSIVE_PROVIDER);
    }

    /**
     * Build a dialog to ask the customer to change his location settings
     *
     * @param context a UI context
     */
    @UiThread
    public static void askEnableProviders(@NonNull final Context context) {
        askEnableProviders(context, R.string.provider_settings_message);
    }

    /**
     * Build a dialog to ask the customer to change his location settings
     *
     * @param context         a UI Context
     * @param messageResource the message to show to the customer in the dialog
     */
    @UiThread
    public static void askEnableProviders(@NonNull final Context context, @StringRes int messageResource) {
        askEnableProviders(context, messageResource, R.string.provider_settings_yes, R.string.provider_settings_no);
    }

    /**
     * Build a dialog to ask the customer to change his location settings
     *
     * @param context               a UI Context
     * @param messageResource       the message to show to the customer in the dialog
     * @param positiveLabelResource the positive button text
     * @param negativeLabelResource the negative button text
     */
  //  @UiThread
    public static void askEnableProviders(@NonNull final Context context, @StringRes int messageResource, @StringRes int positiveLabelResource, @StringRes int negativeLabelResource) {
        try {
            SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#fb8462"));
            pDialog.setTitleText(context.getString(R.string.provider_settings_title));
            pDialog.setContentText(context.getString(R.string.provider_settings_message));
            pDialog.setCancelable(true);
            pDialog.setCancelText(context.getString(R.string.cancel));
            pDialog.showCancelButton(true);
            pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sDialog) {
                    sDialog.dismissWithAnimation();
                }
            });
            pDialog.setConfirmText(context.getString(R.string.OK))
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            if (context instanceof TaxiHomeActivity) {
                                ((TaxiHomeActivity) context).startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                                        , AppConstants.RequestCode.ENABLE_LOCATION);
                            } else {
                                context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            }
                        }
                    });
            pDialog.show();
        }
        catch (Exception e){
            Log.e("ShowOkAlert", "Exception: " + e.getMessage());
        }
    }

    /**
     * Check if the provider is enabled of not
     *
     * @param context  any context
     * @param provider the provider to check
     * @return true if the provider is enabled, false otherwise
     */
    private static boolean isProviderEnabled(@NonNull Context context, @NonNull String provider) {
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(provider);
    }
    public static float rotation(Location prev,Location newLoc){

       return prev.bearingTo(newLoc) ;
    }

}
