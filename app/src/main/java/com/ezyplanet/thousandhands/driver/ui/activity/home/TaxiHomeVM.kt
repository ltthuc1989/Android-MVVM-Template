package com.ezyplanet.thousandhands.driver.ui.activity.home

import android.location.Location
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ezyplanet.thousandhands.core.ui.base.BaseViewModel
import com.ezyplanet.thousandhands.core.util.SchedulerProvider
import com.ezyplanet.thousandhands.driver.data.network.AppDataManager
import com.ezyplanet.thousandhands.driver.data.network.request.Address

import com.ezyplanet.thousandhands.driver.data.network.response.User
import com.ezyplanet.thousandhands.driver.data.network.wraper.BookTripEvent
import com.ezyplanet.thousandhands.driver.data.network.wraper.FayeMessageWrapper
import com.ezyplanet.thousandhands.driver.util.location.LocationUtils

import com.ezyplanet.thousandhands.util.connectivity.BaseConnectionManager

import javax.inject.Inject

class TaxiHomeVM @Inject constructor(
        val appDataManager: AppDataManager, schedulerProvider: SchedulerProvider, connectionManager: BaseConnectionManager
) : BaseViewModel<TaxiHomeNav>(schedulerProvider, connectionManager) {

    private val model = MutableLiveData<TaxiHomeMD>()
    private var prevLocation: Location? = null

    private var rotation = 0f
    fun getModel(): LiveData<TaxiHomeMD> = model
    private fun setModel(user: User) {
        model.postValue(TaxiHomeMD(user.avatar_url, user.full_name))
        navigator?.setUpLocationTrack()

    }


//     fun registerDeviceForPush(){
//
//          if(navigator?.enbalePlayService()==true&&isNetworkConnected()!!) {
//
//
//               OneSignal.idsAvailable { userid, regisId ->
//                    val deviceRequest = DeviceRequest();
//                    val deviceEntity = DeviceRequest.DeviceEntity()
//                    deviceEntity.client = "android"
//                    deviceEntity.setDeviceInfo(deviceUtilImpl.getAndroidVersion() + " - " + deviceUtilImpl.getAppVersion())
//                    deviceEntity.token = regisId
//                    deviceRequest.device = deviceEntity
//                    if(appDataManager!!.isLogin()) {
//
//
//                         compositeDisposable.add(appDataManager!!.postDevices(deviceRequest)
//                                 .compose(schedulerProvider?.ioToMainSingleScheduler())
//                                 .subscribe({
//                                      appDataManager?.deviceToken = regisId
//                                      appDataManager?.oneSignalId = userid
//
//                                      if(appDataManager!!.getUserInfo()==null){
//                                           getMedetail()
//                                      }
//                                 }) {
//                                      apiErrorObservable(it)
//
//                                 })
//                    }
//               }
//          }
//
//
//     }


    fun getMedetail() {
        if (!appDataManager!!.isLogin()) return


        navigator?.showProgress()
        compositeDisposable.add(appDataManager!!.getMedetail()
                .compose(schedulerProvider?.ioToMainSingleScheduler())
                .subscribe({
                    if (appDataManager!!.getUserInfo() == null) {
                        appDataManager?.updateUserInfo(it)


                        navigator?.hideProgress()

                    } else {
                        appDataManager?.updateUserInfo(it)


                        navigator?.hideProgress()
                    }
                    setModel(it)

                    navigator?.startWebSocket()


                }, {

                    apiErrorObservable(it)


                }))

    }


    fun updateLocation(location: Location) {
        if (prevLocation == null) {
            prevLocation = location
            if (location.hasBearing()) {
                rotation = location.bearing
            }
        } else {
            rotation = LocationUtils.rotation(prevLocation, location)
            prevLocation = location
        }

        if (appDataManager!!.isLogin()) {
            val loc = Address()
            var isOK = false
            if (location != null && location.latitude > 0 && location.longitude > 0) {
                loc.latitude = location.latitude
                loc.longitude = location.longitude
                isOK = true
            } else {
                val userLoc = appDataManager!!.getUserInfo()?.location
                if (userLoc != null && !TextUtils.isEmpty(userLoc.getLongitude())) {
                    val location = userLoc
                    val lat = java.lang.Double.parseDouble(location.getLatitude())
                    val longtitude = java.lang.Double.parseDouble(location.getLongitude())
                    if (lat > 0 && longtitude > 0) {
                        loc.latitude = lat
                        loc.longitude = longtitude
                        isOK = true
                    }
                } else {
                    // ToastUtils.showShortMessage("Cannot update current position", getApplicationContext());
                    return
                }
            }
            if (!isOK) {
                return
            }
            // Timber.d("update location " + UserManager.getInstance().getUser().getEmail() + " " + UserManager.getInstance().getUser().getFull_name() + " with " + "lat " + loc.latitude + " long " + loc.longitude)

            loc.device_token = appDataManager?.deviceToken
            loc.rotation = rotation
            if (isNetworkConnected() == true) {
                compositeDisposable.add(appDataManager!!.putHelperLocation(loc)
                        .compose(schedulerProvider?.ioToMainSingleScheduler())
                        .subscribe({

                            appDataManager?.currentLocation = loc


                        }, {

                            apiErrorObservable(it)


                        }))


            }
        } else {
            val loc = Address()
            if (loc != null && loc.latitude != 0.0) {
                loc.latitude = loc.latitude
                loc.longitude = loc.longitude
                appDataManager?.currentLocation = loc
            }
        }

    }

    fun receiveEvent(data:Any){
        if(data is BookTripEvent){
            navigator?.openAcceptTripDlg(data)
        }

    }

}