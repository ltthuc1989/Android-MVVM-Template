package com.ezyplanet.thousandhands.driver.data.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataClassRequest {
    data class RevokeRequest internal constructor(@Expose
                                                  @SerializedName("token") internal val token: String?,
                                                  @Expose
                                                  @SerializedName("device_token") internal val deviceToken: String?)

    data class DisableDeviceReq internal constructor(val device_token: String?)
    data class PasswordReq internal constructor(val old_password: String?, val new_password: String?)
    data class LocationReq constructor(val latitude:String,val longitude:String,val rotation:Float)
}