package com.ezyplanet.thousandhands.driver.data.network
import android.content.Context
import com.androidnetworking.error.ANError
import com.ezyplanet.thousandhands.driver.data.SocketHelper
import com.ezyplanet.thousandhands.driver.data.network.request.DataClassRequest
import com.ezyplanet.thousandhands.driver.data.network.request.DeviceRequest
import com.ezyplanet.thousandhands.driver.data.network.request.LoginRequest
import com.ezyplanet.thousandhands.driver.data.network.request.UserRequest
import com.ezyplanet.thousandhands.driver.data.network.response.*
import com.ezyplanet.thousandhands.driver.data.preferences.AppPreferenceHelper
import com.ezyplanet.thousandhands.driver.util.JsonHelper
import com.irmansyah.catalogmoviekotlin.data.DataManager
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton




import com.ezyplanet.thousandhands.driver.data.network.request.*
import com.ezyplanet.thousandhands.driver.data.network.wraper.BookTripEvent


@Singleton
class AppDataManager @Inject constructor(val context: Context, var appPreferenceHelper: AppPreferenceHelper, private val apiHelper: ApiHelper) : DataManager {

    private var _deviceToken: String? = null
    var deviceToken: String?
        get() = _deviceToken
        set(value) {
            _deviceToken = value
        }
     var oneSignalId: String? = null
    var setting: Setting?=null


    private var _currentLocation: Address?=null
    var currentLocation:Address?
     get() = _currentLocation
    set(value) {
        _currentLocation= value
    }






    fun isLogin(): Boolean {
        return (appPreferenceHelper.loginMode != DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type)
    }


    override fun setAccessTokent(loggedInMode: DataManager.LoggedInMode, access_token: String?) {
        appPreferenceHelper.token = access_token;
        appPreferenceHelper.loginMode = loggedInMode.type
        getApiHeader().protectedApiHeader.accessToken = "bearer " + access_token


    }

    override fun getUserInfo(): User? {
        var user: User? = null
        if (appPreferenceHelper.user.isNullOrEmpty()) {

        } else {
            user = JsonHelper.getUserfromJson(appPreferenceHelper.user)
        }
        return user
    }

    override fun updateUserInfo(user: User?) {
        if (user == null) setUserAsLoggedOut()
        else appPreferenceHelper.user = JsonHelper.userToJson(user)

    }

    override fun getCountryCode(): String? {
        return "vn"
    }

    override fun updateApiHeader(userId: Long?, accessToken: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUserAsLoggedOut() {
        appPreferenceHelper.user = ""
        appPreferenceHelper.token = ""
        appPreferenceHelper.loginMode = DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type
        getApiHeader().protectedApiHeader.accessToken = ""


    }

    override fun updateUserInfo(user: User, loggedInMode: DataManager.LoggedInMode) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getApiHeader(): ApiHeader {
        return apiHelper.getApiHeader()

    }

    //api call

    override fun getMedetail(): Single<User> {
        return apiHelper.getMedetail()
    }

    override fun getSetting(): Single<Setting> {
        return  apiHelper.getSetting()
    }

    override fun postServerLogin(serverLoginRequest: LoginRequest.ServerLoginRequest): Single<OauthResponse> {

        return apiHelper.postServerLogin(serverLoginRequest)
    }

    override fun postPhoneLogin(phoneLoginRequest: LoginRequest.PhoneLoginRequest): Single<OauthResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun postFacebookSmsLogin(faceBookSmsLoginRequest: LoginRequest.FaceBookSmsLoginRequest): Single<OauthResponse> {
        return apiHelper.postFacebookSmsLogin(faceBookSmsLoginRequest)
    }

    override fun postFacebookLogin(facebookLoginRequest: LoginRequest.FacebookLoginRequest): Single<OauthResponse> {
        return apiHelper.postFacebookLogin(facebookLoginRequest)
    }

    override fun postCheckUser(act: String, access_tokent: String?): Single<Int> {
        return apiHelper.postCheckUser(act, access_tokent)
    }

    override fun postDevices(deviceRequest: DeviceRequest): Single<DeviceResp> {
        return apiHelper.postDevices(deviceRequest)
    }



    override fun getUserStatus(): Single<UserStatus> {
        return apiHelper.getUserStatus()
    }

    override fun putMedetail(userRequest: UserRequest): Single<User> {
        return apiHelper.putMedetail(userRequest)
    }

    override fun putDisableDevice(disableDeviceReq: DataClassRequest.DisableDeviceReq): Single<DisableResp> {
        return apiHelper.putDisableDevice(disableDeviceReq)
    }

    override fun postRevoke(revokeRequest: DataClassRequest.RevokeRequest): Single<ANError> {
        return apiHelper.postRevoke(revokeRequest)
    }

    override fun postChangePassword(passwordReq: DataClassRequest.PasswordReq): Single<ANError> {
        return apiHelper.postChangePassword(passwordReq)
    }


    override fun putHelperLocation(address: Address): Single<String> {
     return  apiHelper.putHelperLocation(address)
    }

    override fun putAcceptTrip(id: String?): Single<BookTripEvent> {
        return  apiHelper.putAcceptTrip(id)
    }

    override fun putStartTrip(id: String?): Single<String> {
        return  apiHelper.putStartTrip(id)
    }

    override fun putArrive(id: String?): Single<String> {
        return  apiHelper.putArrive(id)
    }
}