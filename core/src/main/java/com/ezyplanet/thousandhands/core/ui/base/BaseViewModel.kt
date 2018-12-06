package com.ezyplanet.thousandhands.core.ui.base


import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.androidnetworking.common.ANConstants
import com.androidnetworking.error.ANError


import com.ezyplanet.thousandhands.core.ui.listener.RetryCallback
import com.ezyplanet.thousandhands.core.util.CoreConstants
import com.ezyplanet.thousandhands.core.util.SchedulerProvider
import com.ezyplanet.thousandhands.util.connectivity.BaseConnectionManager
import com.ezyplanet.thousandhands.util.livedata.ActivityActionLiveData
import com.ezyplanet.thousandhands.util.livedata.FragmentActionLiveData

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.disposables.CompositeDisposable

import timber.log.Timber
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import com.ezyplanet.thousandhands.core.R
import com.ezyplanet.thousandhands.core.data.network.ApiError
import com.ezyplanet.thousandhands.core.data.network.DataClassError
import com.ezyplanet.thousandhands.core.ui.listener.LoginNavigator
import com.ezyplanet.thousandhands.core.ui.listener.PasswordNavigator
import com.ezyplanet.thousandhands.core.util.CoreConstants.API_STATUS_CODE_LOCAL_ERROR
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import javax.net.ssl.HttpsURLConnection


abstract class BaseViewModel<n : MvvmNav>(private val connectionManager: BaseConnectionManager)
    : ViewModel(), LifecycleObserver {


    var schedulerProvider: SchedulerProvider?=null
    constructor(    schedulerProvider: SchedulerProvider, connectionManager: BaseConnectionManager) : this(connectionManager) {

        this.schedulerProvider=schedulerProvider
    }





    var navigator: n? = null
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val activityAction = ActivityActionLiveData()
    val fragmentAction = FragmentActionLiveData()
    //for ListViewModel
    var resetLoadingState: Boolean = false
    var visibleThreshold= CoreConstants.VISIBLE_THRESHOLD
    val toolbarTitle :MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    var  isLogIn =MutableLiveData<Boolean>()
    private val _isUiStateChange = MutableLiveData<Boolean>()
    val isUiStateChange: LiveData<Boolean>
        get() = _isUiStateChange

    fun setIsUiStageChange(isUiStateChange: Boolean) {
        if (_isUiStateChange.value != isUiStateChange) {
            _isUiStateChange.value = isUiStateChange
             reLoadData()


        }
    }


    fun updateLoginSatus(boolean: Boolean){
        isLogIn.value= boolean
    }


    open fun reLoadData(){}

   open fun getEmptyString(context: Context):String?{

        return context.getString(R.string.no_data)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.clear()
        navigator?.hideProgress()
    }



    fun onTokenExpired() {
        // todo : what should i do if token expired ?!
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {
    }

    /**
     * We can use lifeCycle in inherited classes if we need
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume(){}


    fun openActivityOnTokenExpire() {}

    fun showAlert(@StringRes resId: Int) {}
    fun showCancelRetryAlert(@StringRes resId: Int, callback: RetryCallback<*>) {}
    fun showNetworkFail(callback: RetryCallback<*>) {}


    fun showAlert(message: String) {}

    fun onErrorSnackBar(@StringRes resId: Int) {}
    //
    fun onErrorSnackBar(message: String) {}

    fun isNetworkConnected(): Boolean? {
      return connectionManager?.isNetworkConnected()
    }

    fun hideKeyboard() {

    }
    fun onLoadmore(page: Int){

    }


    fun apiErrorObservable(throwable: Throwable, retryCallback: RetryCallback<*>) {
        if (throwable != null) {
            val anError = throwable as ANError?
            if (anError == null) {

                navigator?.connectionFail(R.string.can_not_connect_to_server, retryCallback)



                return
            }

            if (anError?.errorCode == API_STATUS_CODE_LOCAL_ERROR && anError?.errorDetail == ANConstants.CONNECTION_ERROR) {

                navigator?.connectionFail(R.string.can_not_connect_to_server, retryCallback)
                return

            }

            if (anError?.errorCode == API_STATUS_CODE_LOCAL_ERROR && anError?.errorDetail == ANConstants.REQUEST_CANCELLED_ERROR) {

                navigator?.connectionFail(R.string.can_not_connect_to_server, retryCallback)

                return
            }
            if(anError?.errorCode== HttpsURLConnection.HTTP_UNAUTHORIZED||anError?.errorCode==HttpsURLConnection.HTTP_FORBIDDEN){
//                if (navigator !is LoginNavigator && navigator !is PasswordNavigator) {
//                    appDataManager?.setUserAsLoggedOut()
//                    navigator?.openActivityOnTokenExpire()
//                    return
//                }
            }
            val type = object : TypeToken<List<DataClassError.ErrorResp>>() {

            }.type
            val builder = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            val gson = builder.create()

            var apiError: List<DataClassError.ErrorResp>
            try {
                apiError = gson.fromJson(anError?.errorBody, type)

                if ((apiError == null || apiError.get(0) == null) && anError?.errorCode != HttpsURLConnection.HTTP_UNAUTHORIZED) {
                    navigator?.hideProgress()
                    navigator?.showAlert(R.string.something_wrong)

                    return

                }
                var msg = ""
                for (i in apiError.indices) {
                    msg = if (i != apiError.size - 1)
                        msg + apiError[i].full_messages[0] + ","
                    else msg + apiError[i].full_messages[0]
                }
                Timber.d("error $msg")
                when (anError?.errorCode) {
                    HttpsURLConnection.HTTP_UNAUTHORIZED, HttpsURLConnection.HTTP_FORBIDDEN -> {

                        if (navigator !is LoginNavigator && navigator !is PasswordNavigator) {

                            navigator?.openActivityOnTokenExpire()
                            return
                        }

                        if (apiError != null&&msg!=null) {
                            navigator?.hideProgress()
                            navigator?.showAlert(msg)
                            return
                        }else{
                            var error = gson.fromJson(anError?.errorBody,ApiError::class.java)
                            navigator?.hideProgress()
                            navigator?.showAlert(error.message)
                            return
                        }
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR, HttpsURLConnection.HTTP_NOT_FOUND -> if (apiError != null) {
                        navigator?.hideProgress()
                        navigator?.showAlert(msg)
                        return
                    }
                    else -> if (apiError != null) {
                        navigator?.hideProgress()
                        navigator?.showAlert(msg)
                        return
                    }
                }


            } catch (err: JsonSyntaxException) {

                var apiError: DataClassError.ErrorResp? = null
                apiError = gson.fromJson<DataClassError.ErrorResp>(anError?.errorBody, DataClassError.ErrorResp::class.java)

                when (anError?.errorCode) {
                    HttpsURLConnection.HTTP_UNAUTHORIZED, HttpsURLConnection.HTTP_FORBIDDEN -> {
                        if (navigator !is LoginNavigator && navigator !is PasswordNavigator) {

                            navigator?.openActivityOnTokenExpire()
                            return
                        }

                        if (apiError != null&&apiError.full_messages!=null) {
                            navigator?.hideProgress()
                            navigator?.showAlert(apiError.full_messages[0])
                            return
                        }else{

                           var error = gson.fromJson(anError?.errorBody, ApiError::class.java)
                            navigator?.hideProgress()
                            navigator?.showAlert(error.message)
                            return
                        }
                    }

                    HttpsURLConnection.HTTP_INTERNAL_ERROR, HttpsURLConnection.HTTP_NOT_FOUND -> if (apiError != null) {
                        navigator?.hideProgress()
                        navigator?.showAlert(apiError.full_messages[0])
                        return
                    }

                    else -> if (apiError != null) {
                        navigator?.hideProgress()
                        navigator?.showAlert(apiError.full_messages[0])
                        return
                    }
                }
            }

        } else {

        }

    }

    fun apiErrorObservable(throwable: Throwable) {
        if (throwable != null) {
            val anError = throwable as ANError?
            if (anError == null) {

                navigator?.connectionFail(R.string.can_not_connect_to_server)

                return
            }

            if (anError?.errorCode == API_STATUS_CODE_LOCAL_ERROR && anError?.errorDetail == ANConstants.CONNECTION_ERROR) {

                navigator?.connectionFail(R.string.can_not_connect_to_server)
                return

            }

            if (anError?.errorCode == API_STATUS_CODE_LOCAL_ERROR && anError?.errorDetail == ANConstants.REQUEST_CANCELLED_ERROR) {

                navigator?.connectionFail(R.string.can_not_connect_to_server)

                return
            }
            if(anError?.errorCode== HttpsURLConnection.HTTP_UNAUTHORIZED||anError?.errorCode==HttpsURLConnection.HTTP_FORBIDDEN){
                if (navigator !is LoginNavigator && navigator !is PasswordNavigator) {

                    navigator?.openActivityOnTokenExpire()
                    return
                }
            }

            val type = object : TypeToken<List<DataClassError.ErrorResp>>() {

            }.type
            val builder = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
            val gson = builder.create()

            var apiError: List<DataClassError.ErrorResp>
            try {
                apiError = gson.fromJson(anError?.errorBody, type)

                if ((apiError == null || apiError.get(0) == null) && anError?.errorCode != HttpsURLConnection.HTTP_UNAUTHORIZED) {
                    navigator?.hideProgress()
                    navigator?.showAlert(R.string.something_wrong)

                    return

                }
                var msg = ""
                for (i in apiError.indices) {
                    msg = if (i != apiError.size - 1)
                        msg + apiError[i].full_messages[0] + ","
                    else msg + apiError[i].full_messages[0]
                }
                Timber.d("error $msg")
                when (anError?.errorCode) {
                    HttpsURLConnection.HTTP_UNAUTHORIZED, HttpsURLConnection.HTTP_FORBIDDEN -> {

                        if (navigator !is LoginNavigator && navigator !is PasswordNavigator) {

                            navigator?.openActivityOnTokenExpire()
                            return
                        }

                        if (apiError != null&&msg!=null) {
                            navigator?.hideProgress()
                            navigator?.showAlert(msg)
                            return
                        }else{
                            var error = gson.fromJson(anError?.errorBody,ApiError::class.java)
                            navigator?.hideProgress()
                            navigator?.showAlert(error.message)
                            return
                        }
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR, HttpsURLConnection.HTTP_NOT_FOUND -> if (apiError != null) {
                        navigator?.hideProgress()
                        navigator?.showAlert(msg)
                        return
                    }
                    else -> if (apiError != null) {
                        navigator?.hideProgress()
                        navigator?.showAlert(msg)
                        return
                    }
                }


            } catch (err: JsonSyntaxException) {

                var apiError: DataClassError.ErrorResp? = null
                apiError = gson.fromJson<DataClassError.ErrorResp>(anError?.errorBody, DataClassError.ErrorResp::class.java)

                when (anError?.errorCode) {
                    HttpsURLConnection.HTTP_UNAUTHORIZED, HttpsURLConnection.HTTP_FORBIDDEN -> {
                        if (navigator !is LoginNavigator && navigator !is PasswordNavigator) {
                            navigator?.openActivityOnTokenExpire()
                            return
                        }

                        if (apiError != null&&apiError.full_messages!=null) {
                            navigator?.hideProgress()
                            navigator?.showAlert(apiError.full_messages[0])
                            return
                        }else{

                            var error = gson.fromJson(anError?.errorBody,ApiError::class.java)
                            navigator?.hideProgress()
                            navigator?.showAlert(error.message)
                            return
                        }
                    }

                    HttpsURLConnection.HTTP_INTERNAL_ERROR, HttpsURLConnection.HTTP_NOT_FOUND -> if (apiError != null) {
                        navigator?.hideProgress()
                        navigator?.showAlert(R.string.something_wrong)
//                        if(anError?.errorCode==HttpsURLConnection.HTTP_INTERNAL_ERROR){
//
//                        }else{
//                            navigator?.showAlert(apiError.full_messages[0])
//                        }

                        return
                    }

                    else -> if (apiError != null) {
                        navigator?.hideProgress()
                        navigator?.showAlert(apiError.full_messages[0])
                        return
                    }
                }
            }

        } else {

        }

    }
  //retrofit
//    fun apiErrorObservable(throwable: Throwable) {
//        if (throwable is HttpException) {
//            val response = throwable.response()
//          //  val response = throwable as Response<*>
//            val gson = Gson()
//            val type = object : TypeToken<List<ErrorResponse>>() {
//
//            }.type
//            val responseList: List<ErrorResponse>
//            val errorResponse: ErrorResponse?
//            val baseErrorResponse: BaseErrorResponse?
//            val errorsResponse: ErrorsResponse
//
//            val error: String
//
//
//            try {
//                error = response.errorBody()!!.string()
//                if (!response.isSuccessful && response.code() != HttpURLConnection.HTTP_UNAUTHORIZED) {
//                    if (response.code() == HttpURLConnection.HTTP_INTERNAL_ERROR || response.code() > HttpURLConnection.HTTP_INTERNAL_ERROR) {
//                        // AlertUtils.showOkAlert(getActivity(), "", getString(R.string.can_not_connect_to_server))
//                        navigator?.connectionFail(R.string.can_not_connect_to_server)
//                        return
//                    }
//                    try {
//                        responseList = gson.fromJson(error, type)
//                        var msg = ""
//                        for (i in responseList.indices) {
//                            if (i != responseList.size - 1) {
//                                msg = msg + responseList[i].full_messages[0] + ","
//                            } else {
//                                msg = msg + responseList[i].full_messages[0]
//                            }
//                        }
//                        Timber.d("error $msg")
//                        // AlertUtils.showOkAlert(getActivity(), "", msg)
//                        navigator?.hideProgress()
//                        navigator?.showAlert(msg)
//
//                        return
//                    } catch (er: Exception) {
//                        errorResponse = gson.fromJson(error, ErrorResponse::class.java)
//                        if (errorResponse != null && errorResponse.attribute == null) {
//                            baseErrorResponse = gson.fromJson(error, BaseErrorResponse::class.java)
//                            if (baseErrorResponse!!.error == null) {
//                                errorsResponse = gson.fromJson(error, ErrorsResponse::class.java)
//                                if (errorsResponse.errors[0].message != null) {
//                                    //AlertUtils.showOkAlert(getActivity(), "", errorsResponse.errors[0].message)
//                                    navigator?.hideProgress()
//                                    navigator?.showAlert(R.string.something_wrong)
//                                    return
//                                } else {
//                                    // Timber.d("Cannot Parse Respone", error + ",Status Code:" + response.code())
//
//                                    return
//
//                                }
//                            }
//                            if (baseErrorResponse.error_description != null) {
//
//                                navigator?.hideProgress()
//                                navigator?.showAlert(baseErrorResponse.error_description)
//
//                                return
//                            } else {
//                                //  Timber.d("Cannot Parse Respone", error + ",Status Code:" + response.code())
//                                navigator?.hideProgress()
//                                navigator?.showAlert(error)
//                                return
//                            }
//
//                        }
//                    }
//
//                } else if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
//                    baseErrorResponse = gson.fromJson(error, BaseErrorResponse::class.java)
//                    if (baseErrorResponse != null) {
//
//                        navigator?.hideProgress()
//                        navigator?.showAlert(baseErrorResponse.error_description)
//                        return
//                    }
//                    // UserManager.getInstance().logout(getActivity() as BaseActivity?)
//                    navigator?.openActivityOnTokenExpire()
//                    return
//                }
//
//
//            } catch (err: Exception) {
//                navigator?.showAlert(err.message)
//                return
//            }
//
//        } else if (throwable is SocketTimeoutException) {
//            navigator?.connectionFail(R.string.can_not_connect_to_server)
//        } else if (throwable  is IOException) {
//           // view.onNetworkError()
//        } else {
//           // view.onUnknownError(e.getMessage())
//        }
//
//
//
//
//
//    }
}