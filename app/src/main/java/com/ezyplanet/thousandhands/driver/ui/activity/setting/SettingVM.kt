package com.ezyplanet.thousandhands.driver.ui.activity.setting


import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import com.ezyplanet.thousandhands.core.ui.base.BaseViewModel
import com.ezyplanet.thousandhands.core.util.SchedulerProvider
import com.ezyplanet.thousandhands.driver.BR
import com.ezyplanet.thousandhands.driver.data.network.AppDataManager
import com.ezyplanet.thousandhands.driver.data.network.request.DataClassRequest
import com.ezyplanet.thousandhands.driver.data.network.request.UserRequest

import com.ezyplanet.thousandhands.util.connectivity.BaseConnectionManager

import javax.inject.Inject

class SettingVM @Inject constructor(
       val appDataManager: AppDataManager, schedulerProvider: SchedulerProvider, connectionManager: BaseConnectionManager
) : BaseViewModel<SettingNav>(schedulerProvider, connectionManager), Observable {

    // two-way data binding for switch View

    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    @Suppress("unused")
    private fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    /**
     * Notifies listeners that initView specific property has changed. The getter for the property
     * that changes should be marked with [Bindable] to generate initView field in
     * `BR` to be used as `fieldId`.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    private fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }

    private var isNotifyOn = appDataManager.getUserInfo()!!.isSend_notifications

    var notifyStatus: Boolean
        @Bindable get() {
            return isNotifyOn
        }
        set(value) {
            // These methods take care of calling notifyPropertyChanged()
            // if (value) startButtonClicked() else pauseButtonClicked()
           if(isNotifyOn!=value) {
               switchNotify(value)
           }
        }

    // onClick binding
    fun changePassword() {
        navigator?.openChangePasswordScreen()
    }

    private fun switchLanguage() {

    }

    fun logoutClick() {
        disableDevice()
    }


    private fun switchNotify(isOn: Boolean) {
        val userRequest = UserRequest()
        val userBean = UserRequest.UserBean()
        userBean.isSend_notifications = isOn
        userRequest.setUser(userBean)
        navigator?.showProgress()
        compositeDisposable.add(appDataManager!!.putMedetail(userRequest)
                .compose(schedulerProvider?.ioToMainSingleScheduler())
                .subscribe({
                    isNotifyOn = isOn
                    appDataManager!!.updateUserInfo(it)
                    notifyPropertyChanged(BR.notifyStatus)
                    navigator?.hideProgress()


                }, {

                    apiErrorObservable(it)


                }))
    }


    private fun disableDevice() {
        navigator?.showProgress()

        val device_token = appDataManager?.deviceToken
        val disableReqs = DataClassRequest.DisableDeviceReq(device_token)
        compositeDisposable.add(appDataManager!!.putDisableDevice(disableReqs)
                .compose(schedulerProvider?.ioToMainSingleScheduler())
                .subscribe({

                    revoke()


                }, {

                    //apiErrorObservable(it)
                    revoke()


                }))

    }

    private fun revoke() {
        val token = appDataManager?.appPreferenceHelper?.token
        val device_token = appDataManager?.deviceToken
        val request = DataClassRequest.RevokeRequest(token, device_token)

        compositeDisposable.add(appDataManager!!.postRevoke(request)
                .compose(schedulerProvider?.ioToMainSingleScheduler())
                .subscribe({
                    appDataManager!!.updateUserInfo(null)
                    navigator?.logout()
                    navigator?.hideProgress()


                }, {
                    navigator?.logout()
                    navigator?.hideProgress()
                    //apiErrorObservable(it)


                }))

    }


}