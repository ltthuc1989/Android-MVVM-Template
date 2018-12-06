package com.ezyplanet.thousandhands.driver.data.network

import com.androidnetworking.error.ANError
import com.ezyplanet.thousandhands.driver.data.network.request.*
import com.ezyplanet.thousandhands.driver.data.network.response.*
import com.ezyplanet.thousandhands.driver.data.network.wraper.BookTripEvent
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by jyotidubey on 04/01/18.
 */
interface ApiHelper {

    fun getApiHeader(): ApiHeader

    fun putDisableDevice(disableDeviceReq: DataClassRequest.DisableDeviceReq):Single<DisableResp>
    fun putMedetail(userRequest: UserRequest):Single<User>
    fun putHelperLocation(address:Address):Single<String>
    fun putAcceptTrip(id:String?) :Single<BookTripEvent>
    fun putStartTrip(id:String?) :Single<String>
    fun putArrive(id:String?) :Single<String>

    fun getUserStatus():Single<UserStatus>
    fun getMedetail(): Single<User>
    fun getSetting(): Single<Setting>


    fun postServerLogin(serverLoginRequest: LoginRequest.ServerLoginRequest) :Single<OauthResponse>
    fun postPhoneLogin(phoneLoginRequest: LoginRequest.PhoneLoginRequest) : Single<OauthResponse>
    fun postFacebookSmsLogin(faceBookSmsLoginRequest: LoginRequest.FaceBookSmsLoginRequest) :Single<OauthResponse>
    fun postFacebookLogin(facebookLoginRequest: LoginRequest.FacebookLoginRequest) :Single<OauthResponse>
    fun postCheckUser(act:String,access_token:String?):Single<Int>
    fun postDevices(deviceRequest: DeviceRequest):Single<DeviceResp>
    fun postRevoke(revokeRequest: DataClassRequest.RevokeRequest):Single<ANError>
    fun postChangePassword(passwordReq: DataClassRequest.PasswordReq):Single<ANError>
}