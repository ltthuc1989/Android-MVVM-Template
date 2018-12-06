package com.ezyplanet.thousandhands.driver.ui.activity.login.password

import android.text.TextUtils
import com.ezyplanet.thousandhands.core.ui.base.BaseViewModel
import com.ezyplanet.thousandhands.core.ui.listener.RetryCallback
import com.ezyplanet.thousandhands.core.util.CommonUtil
import com.ezyplanet.thousandhands.core.util.SchedulerProvider
import com.ezyplanet.thousandhands.driver.data.network.AppDataManager
import com.ezyplanet.thousandhands.driver.data.network.request.LoginRequest

import com.ezyplanet.thousandhands.util.connectivity.BaseConnectionManager
import com.irmansyah.catalogmoviekotlin.data.DataManager
import javax.inject.Inject


class PasswordVM @Inject constructor(
        val appDataManager: AppDataManager, schedulerProvider: SchedulerProvider, connectionManager: BaseConnectionManager
) : BaseViewModel<PasswordNavigator>(schedulerProvider, connectionManager) {


    fun loginClick(email: String, password: String) {
        navigator?.showProgress()
        compositeDisposable.add(appDataManager!!.postServerLogin(LoginRequest.ServerLoginRequest(email, password))
                .compose(schedulerProvider?.ioToMainSingleScheduler())
                .subscribe({
                    appDataManager?.setAccessTokent(DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER, it.access_token)
                   appDataManager.appPreferenceHelper.socketClient=it.socket_token
                    navigator?.openHomeActivity()

                }, {
                    apiErrorObservable(it, object : RetryCallback<Unit> {
                        override val request: Unit
                            get() = loginClick(email, password)

                        override fun onSendEvent() {
                            navigator?.hideProgress()
                        }

                        override fun onRetry() {

                            navigator?.showProgress()

                        }
                    })

                }))

    }

    fun serverLogin() {
        navigator?.serverLogin()

    }

    fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        // validate email and password
        if (TextUtils.isEmpty(email)) {
            return false
        }
        if (!CommonUtil.isEmailValid(email)) {
            return false
        }
         if (TextUtils.isEmpty(password)) {
           return false
        }
        return true;
    }


}
