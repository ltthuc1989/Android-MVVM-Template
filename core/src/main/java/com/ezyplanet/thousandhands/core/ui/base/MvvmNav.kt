package com.ezyplanet.thousandhands.core.ui.base


import androidx.annotation.StringRes
import com.ezyplanet.thousandhands.core.ui.listener.RetryCallback

interface MvvmNav {
    fun connectionFail(restId:Int, retryCallback: RetryCallback<*>)
    fun connectionFail(restId:Int)
    fun openActivityOnTokenExpire()
    fun showAlert(message: String?)
    fun showAlert(@StringRes resId: Int)
    fun showErrorSnackBar(@StringRes resId: Int)
    fun hideProgress()
    fun showProgress()
}