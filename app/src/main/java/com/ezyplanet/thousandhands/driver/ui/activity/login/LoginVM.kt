package com.ezyplanet.thousandhands.driver.ui.activity.login

import android.content.Intent
import android.os.Bundle
import com.androidnetworking.error.ANError
import com.ezyplanet.thousandhands.core.ui.base.BaseViewModel
import com.ezyplanet.thousandhands.core.ui.listener.RetryCallback
import com.ezyplanet.thousandhands.core.util.SchedulerProvider
import com.ezyplanet.thousandhands.driver.data.network.AppDataManager
import com.ezyplanet.thousandhands.driver.data.network.request.LoginRequest
import com.ezyplanet.thousandhands.util.connectivity.BaseConnectionManager
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.irmansyah.catalogmoviekotlin.data.DataManager
import org.json.JSONException
import timber.log.Timber
import javax.inject.Inject


class LoginVM @Inject constructor(
        val appDataManager: AppDataManager, schedulerProvider: SchedulerProvider, connectionManager: BaseConnectionManager
) : BaseViewModel<LoginNavigator>( schedulerProvider, connectionManager) {
    var fb_access_token: String? = null
    var countryCode = appDataManager?.getCountryCode()
    var callbackManager: CallbackManager? = null

    init {
        registerFacebookCallback()


    }


    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    fun facebookClick() {
        navigator?.facebookLoginClick()
    }

    fun phoneClick() {
        navigator?.phoneLoginClick()

    }

    fun passwordClick() {
        navigator?.passwordLoginClick()
    }


    fun facebookSmsLogin(fb_token: String) {

        compositeDisposable.add(
                appDataManager!!.postFacebookSmsLogin(LoginRequest.FaceBookSmsLoginRequest(fb_token))
                        .compose(schedulerProvider?.ioToMainSingleScheduler())
                        .subscribe({

                            appDataManager?.setAccessTokent(DataManager.LoggedInMode.LOGGED_IN_MODE_FB, it.access_token)
                            appDataManager.appPreferenceHelper.socketClient=it.socket_token
                            navigator?.openHomeActivity()

                        }, {


                        }))


    }

    fun facebookLogin(fb_token: String?) {

        compositeDisposable.add(
                appDataManager!!.postFacebookLogin(LoginRequest.FacebookLoginRequest(fb_token, appDataManager!!.deviceToken))
                        .compose(schedulerProvider?.ioToMainSingleScheduler())
                        .subscribe({
                            navigator?.openHomeActivity()

                        }, {


                        }))


    }

    fun checkUser(fb_tokent: String?) {

        compositeDisposable.add(appDataManager!!.postCheckUser("facebook", fb_tokent)
                .compose(schedulerProvider?.ioToMainSingleScheduler())
                .subscribe({


                    facebookLogin(fb_tokent)


                }, {
                    val anError = it as ANError?
                    if (anError?.errorCode == 404) {
                        phoneClick()

                    } else {

                        apiErrorObservable(it, object : RetryCallback<String> {
                            override val request: String
                                get() = "" //To change initializer of created properties use File | Settings | File Templates.

                            override fun onSendEvent() {
                                navigator?.hideProgress()
                            }

                            override fun onRetry() {

                                navigator?.showProgress()
                                checkUser(fb_tokent)

                            }
                        })
                    }

                }))


    }


    private fun registerFacebookCallback() {


        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Timber.d("successfully create token on Facebook ")

                val request = GraphRequest.newMeRequest(result?.getAccessToken()) { `object`, response ->
                    try {
                        Timber.d("GraphRequest " + response.toString())

                        fb_access_token = result?.getAccessToken()?.getToken()
                        checkUser(fb_access_token)
                        Timber.d("processLoginViaFb email")
                    } catch (error: JSONException) {

                        fb_access_token = result?.getAccessToken()?.getToken()
                        checkUser(fb_access_token)
                        Timber.d("processLoginViaFb error" + error.message)
                    } catch (e: NullPointerException) {
                        fb_access_token = result?.getAccessToken()?.getToken()
                        checkUser(fb_access_token)
                        Timber.d("processLoginViaFb error" + e.message)
                    }
                }
                val parameters = Bundle()
                parameters.putString("fields", "email") // Parámetros que pedimos initView facebook
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException?) {
            }
        })
    }

}