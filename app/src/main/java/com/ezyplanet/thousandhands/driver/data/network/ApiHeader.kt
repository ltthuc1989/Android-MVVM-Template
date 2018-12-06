package com.ezyplanet.thousandhands.driver.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

/**
 * Created by jyotidubey on 11/01/18.
 */
class ApiHeader @Inject constructor(internal val publicApiHeader: PublicApiHeader, internal val protectedApiHeader: ProtectedApiHeader) {

    class PublicApiHeader @Inject constructor(
                                              @Expose
                                              @SerializedName
                                              ("api_key") val apiKey: String)

    class ProtectedApiHeader @Inject constructor(@Expose
                                                 @SerializedName("Authorization") var accessToken: String?)

}