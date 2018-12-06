package com.ezyplanet.thousandhands.driver.ui.activity.changepasword

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry

import com.ezyplanet.thousandhands.core.ui.base.BaseViewModel
import com.ezyplanet.thousandhands.core.util.SchedulerProvider
import com.ezyplanet.thousandhands.driver.BR

import com.ezyplanet.thousandhands.driver.R
import com.ezyplanet.thousandhands.driver.data.network.AppDataManager
import com.ezyplanet.thousandhands.driver.data.network.request.DataClassRequest


import com.ezyplanet.thousandhands.util.connectivity.BaseConnectionManager

import javax.inject.Inject

class ChangePasswordVM @Inject constructor(
       val appDataManager: AppDataManager, schedulerProvider: SchedulerProvider, connectionManager: BaseConnectionManager
) : BaseViewModel<ChangePasswordNav>(schedulerProvider, connectionManager), Observable {


    // two-way data binding for EditText View

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

    private var password = ""
    private var confirmPassword = ""

    @Bindable
    fun getOldPassword(): String {
        return password
    }

    fun setOldPassword(value: String) {
        password = value
        notifyPropertyChanged(BR.oldPassword)
    }


    @Bindable
    fun getConfirmPassword(): String {
        return confirmPassword
    }

    fun setConfirmPassword(value: String) {
        confirmPassword = value
        notifyPropertyChanged(BR.confirmPassword)
    }

    fun saveClick() {
        if (validate()) {
            val passReq = DataClassRequest.PasswordReq(password, confirmPassword)
            navigator?.showProgress()
            compositeDisposable.add(appDataManager!!.postChangePassword(passReq)
                    .compose(schedulerProvider?.ioToMainSingleScheduler())
                    .subscribe({

                        navigator?.showSumbitDlg()
                        navigator?.hideProgress()


                    }, {

                        apiErrorObservable(it)


                    }))

        }

    }

    private fun validate(): Boolean {
        if (password.isEmpty()) {
            navigator?.showAlert(R.string.please_enter_password)
            return false
        }
        if (!confirmPassword.equals(password)) {
            navigator?.showAlert(R.string.password_does_not_match)
            return false
        }
        return true

    }

}