package com.ezyplanet.thousandhands.driver.ui.activity.home


import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.Gravity
import androidx.annotation.NonNull
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProviders

import com.ezyplanet.thousandhands.driver.R

import com.ezyplanet.thousandhands.core.ui.base.MvvmActivity
import com.ezyplanet.thousandhands.core.util.extension.gotoActivity
import com.ezyplanet.thousandhands.core.util.livedata.ColdEventRx
import com.ezyplanet.thousandhands.core.viewmodel.DataChangeVM
import com.ezyplanet.thousandhands.driver.DriverApp
import com.ezyplanet.thousandhands.driver.data.network.request.Address
import com.ezyplanet.thousandhands.driver.data.network.wraper.FayeMessageWrapper
import com.ezyplanet.thousandhands.driver.databinding.ActivityTaxiHomeBinding
import com.ezyplanet.thousandhands.driver.ui.activity.login.LoginActivity
import com.ezyplanet.thousandhands.driver.ui.activity.setting.SettingActivity

import com.ezyplanet.thousandhands.driver.ui.dialog.accepttrip.TaxiAccepTripDlg

import com.ezyplanet.thousandhands.driver.ui.fragment.TaxiHomeMapFrag
import com.ezyplanet.thousandhands.driver.ui.widget.listener.HomeNavigationViewNav
import com.ezyplanet.thousandhands.driver.ui.widget.listener.ToolbarTaxiHomeNav
import com.ezyplanet.thousandhands.driver.util.AppConstants
import com.ezyplanet.thousandhands.driver.util.extension.replaceMapFragment
import com.ezyplanet.thousandhands.driver.util.location.LocationTracker
import com.ezyplanet.thousandhands.driver.util.location.LocationUtils
import com.ezyplanet.thousandhands.driver.util.location.TrackerSettings
import com.ezyplanet.thousandhands.driver.viewmodel.SocketEventVM
import com.ezyplanet.thousandhands.util.livedata.HotEventObserver
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.maps.GoogleMap


class TaxiHomeActivity : MvvmActivity<ActivityTaxiHomeBinding, TaxiHomeVM>(), TaxiHomeNav, ToolbarTaxiHomeNav,TaxiHomeMapFrag.TaxiMapListener,
        HomeNavigationViewNav {
    override val layoutId: Int = R.layout.activity_taxi_home
    override val viewModel: TaxiHomeVM by getLazyViewModel()
    private var isOpenDrawer = false
    private val PLAY_SERVICES_RESOLUTION_REQUEST = 9000
    var locationTracker: LocationTracker? = null
    private var dataChangeVM: DataChangeVM? = null
    private var socketEventVM:SocketEventVM?=null

    override fun onViewInitialized(binding: ActivityTaxiHomeBinding) {
        super.onViewInitialized(binding)
        viewModel.navigator = this
        binding.viewModel = viewModel
        binding.widgetToolbarHome.setListner(this)
        binding.homeNavigationView.setListner(this)
        viewModel.getMedetail()
        dataChangeVM = ViewModelProviders.of(this).get(DataChangeVM::class.java)
        socketEventVM = ViewModelProviders.of(this).get(SocketEventVM::class.java)


        socketEventVM?.onNewMessage?.observe(this,HotEventObserver{

           viewModel.receiveEvent(it)

        })



        replaceMapFragment(TaxiHomeMapFrag().setListner(this))


    }

    override fun openAcceptTripDlg(data: Any?) {

        TaxiAccepTripDlg.newInstance(data).show(supportFragmentManager, TaxiAccepTripDlg.tag)

    }


    override fun onDrawerClick() {
        if (!isOpenDrawer || !binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.openDrawer(Gravity.LEFT)
            isOpenDrawer = true
        } else {
            binding.drawerLayout.closeDrawer(Gravity.LEFT)
            isOpenDrawer = false
        }
    }

    override fun onSettingClick() {
        var intent= Intent(this,SettingActivity::class.java)
        startActivityForResult(intent, AppConstants.ActivityResult.LOGOUT)
    }


    override fun enbalePlayService(): Boolean? {


        val apiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = apiAvailability.isGooglePlayServicesAvailable(this)
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show()
            } else {
                // Timber.d(TAG + "This device is not supported." + " " + getDeviceName())
                finish()
            }
            return false
        }
        return true


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PASSWORD_LOGIN_CODE) {
            if (data == null)
                return
            dataChangeVM?.isLogined?.value = ColdEventRx(true)


        }
        if (resultCode == AppConstants.ResultCode.LOGOUT_SUCEESS) {
            (application as DriverApp).socketHelper.disconnect()
            gotoActivity(LoginActivity::class,true)


        }
    }

    override fun setUpLocationTrack() {
        if (locationTracker == null && LocationUtils.isGpsProviderEnabled(this)) {
            val settings = TrackerSettings()
                    .setUseGPS(true)
                    .setUseNetwork(false)
                    .setUsePassive(false)
                    .setTimeBetweenUpdates(4 * 1000)
                    .setMetersBetweenUpdates(50f)
            try {
                locationTracker = object : LocationTracker(this, settings) {
                    override fun onLocationFound(@NonNull location: Location) {
                        dataChangeVM?.locationUpdate?.postValue(ColdEventRx(location))
                        viewModel.updateLocation(location)


                    }

                    override fun onTimeout() {

                    }


                }
                locationTracker?.quickFix()

            } catch (error: SecurityException) {
                error.printStackTrace()
            }

        }
    }



    override fun startWebSocket() {
        (application as DriverApp).socketHelper.startWebSocketService(socketEventVM)

    }

    override fun onConnected(arg0: Bundle?, googleApiClient: GoogleApiClient?) {
    }

    override fun onConnectionFailed(arg0: ConnectionResult?) {
    }

    override fun onMapReady(googleMap: GoogleMap?) {
    }

    override fun onDone(address: Address?) {
    }

    override fun onProcessing() {
    }
}