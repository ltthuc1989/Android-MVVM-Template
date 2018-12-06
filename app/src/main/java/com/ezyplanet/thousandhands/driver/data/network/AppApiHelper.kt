package com.ezyplanet.thousandhands.driver.data.network

import com.androidnetworking.error.ANError
import com.ezyplanet.thousandhands.driver.data.network.request.*
import com.ezyplanet.thousandhands.driver.data.network.response.*
import com.ezyplanet.thousandhands.driver.data.network.wraper.BookTripEvent
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by jyotidubey on 04/01/18.
 */
class AppApiHelper @Inject constructor(private val apiHeader: ApiHeader) : ApiHelper {

    val  limitPage:Int=20
    override fun getApiHeader(): ApiHeader {
    return  apiHeader
    }

    override fun getMedetail(): Single<User> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_getMedetail)
                .addHeaders(apiHeader.protectedApiHeader)
                .build()
                .getObjectSingle(User::class.java)

    }

    override fun getSetting(): Single<Setting> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_getSetting)
                .build()
                .getObjectSingle(Setting::class.java)
    }


    override fun postServerLogin(serverLoginRequest: LoginRequest.ServerLoginRequest): Single<OauthResponse> {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOIN_auth)
                .addBodyParameter(serverLoginRequest)
                .build()
                .getObjectSingle(OauthResponse::class.java)
    }

    override fun postPhoneLogin(phoneLoginRequest: LoginRequest.PhoneLoginRequest): Single<OauthResponse> {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOIN_auth)
                .addBodyParameter(phoneLoginRequest)
                .build()
                .getObjectSingle(OauthResponse::class.java)
    }

    override fun postFacebookSmsLogin(faceBookSmsLoginRequest: LoginRequest.FaceBookSmsLoginRequest): Single<OauthResponse> {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOIN_auth)
                .addBodyParameter(faceBookSmsLoginRequest)
                .build()
                .getObjectSingle(OauthResponse::class.java)
    }

    override fun postFacebookLogin(facebookLoginRequest: LoginRequest.FacebookLoginRequest): Single<OauthResponse> {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOIN_auth)
                .addBodyParameter(facebookLoginRequest)
                .build()
                .getObjectSingle(OauthResponse::class.java)
    }


    override fun postCheckUser(act: String, access_token: String?): Single<Int> {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_CHECK_USER)
                .addQueryParameter("act", act)
                .addQueryParameter("access_token", access_token)
                .build()
                .getObjectSingle(Int::class.java)

    }

    override fun postDevices(deviceRequest: DeviceRequest): Single<DeviceResp> {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_registerDevice)
                .addHeaders(apiHeader.protectedApiHeader)
                .addApplicationJsonBody(deviceRequest)
                .build()
                .getObjectSingle(DeviceResp::class.java)    }



    override fun getUserStatus(): Single<UserStatus> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_USER_STATUS)
                .addHeaders(apiHeader.protectedApiHeader)
                .build()
                .getObjectSingle(UserStatus::class.java)
    }

    override fun putMedetail(userRequest: UserRequest): Single<User> {
        return Rx2AndroidNetworking.put(ApiEndPoint.ENDPOINT_getMedetail)
                .addHeaders(apiHeader.protectedApiHeader)
                .addApplicationJsonBody(userRequest)
                .build()
                .getObjectSingle(User::class.java)
    }

    override fun putDisableDevice(disableDeviceReq: DataClassRequest.DisableDeviceReq): Single<DisableResp> {
        return Rx2AndroidNetworking.put(ApiEndPoint.ENDPOIND_DISABLE_DEVICE)
                .addHeaders(apiHeader.protectedApiHeader)
                .addBodyParameter(disableDeviceReq)
                .build()
                .getObjectSingle(DisableResp::class.java)    }

    override fun postRevoke(revokeRequest: DataClassRequest.RevokeRequest): Single<ANError> {
        return Rx2AndroidNetworking.put(ApiEndPoint.ENDPOIND_DISABLE_DEVICE)
                .addHeaders(apiHeader.protectedApiHeader)
                .addBodyParameter(revokeRequest)
                .build()
                .getObjectSingle(ANError::class.java)    }

    override fun postChangePassword(passwordReq: DataClassRequest.PasswordReq): Single<ANError> {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_CHANGE_PASSWORD)
                .addHeaders(apiHeader.protectedApiHeader)
                .addBodyParameter(passwordReq)
                .build()
                .getObjectSingle(ANError::class.java)    }



    override fun putHelperLocation(address: Address): Single<String> {
        return Rx2AndroidNetworking.put(ApiEndPoint.ENDPOINT_updateHelpersPosition)
                .addHeaders(apiHeader.protectedApiHeader)
                .addApplicationJsonBody(address)
                .build().stringSingle

    }

    override fun putAcceptTrip(id: String?): Single<BookTripEvent> {
        return Rx2AndroidNetworking.put(ApiEndPoint.ENDPOIN_ACCEPT_TRIP)
                .addHeaders(apiHeader.protectedApiHeader)
                .addPathParameter("id",id)
                .build()
                .getObjectSingle(BookTripEvent::class.java)
    }

    override fun putStartTrip(id: String?): Single<String> {
        return Rx2AndroidNetworking.put(ApiEndPoint.ENDPOINT_START_TRIP)
                .addHeaders(apiHeader.protectedApiHeader)
                .addPathParameter("id",id)
                .build()
                .stringSingle
    }

    override fun putArrive(id: String?): Single<String> {
        return Rx2AndroidNetworking.put(ApiEndPoint.ENDPOINT_ARRIVE)
                .addHeaders(apiHeader.protectedApiHeader)
                .addPathParameter("id",id)
                .build()
                .stringSingle
    }
}