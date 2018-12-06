package com.ezyplanet.thousandhands.driver.data.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class LoginRequest {

    data class ServerLoginRequest internal constructor(@Expose
                                                       @SerializedName("email") internal val email: String,
                                                       @Expose
                                                       @SerializedName("password") internal val password: String,
                                                       @Expose
                                                       @SerializedName("grant_type") internal val grantType: String = "password",
                                                       @Expose
                                                       @SerializedName("client_type") internal val clientType: String = "android")

    data class PhoneLoginRequest internal constructor(@Expose
                                                      @SerializedName("phone") internal val phone: String,
                                                      @Expose
                                                      @SerializedName("password") internal val password: String,
                                                      @Expose
                                                      @SerializedName("grant_type")
                                                      internal val grantType: String = "password",
                                                      @Expose
                                                      @SerializedName("client_type")
                                                      internal val clientType: String = "android")

    data class FaceBookSmsLoginRequest internal constructor(@Expose
                                                            @SerializedName("accountkit_token") internal val accountKitToken: String,
                                                            @Expose
                                                            @SerializedName("provider") internal val phone: String = "facebook_sms",

                                                            @Expose
                                                            @SerializedName("grant_type") internal val grantType: String = "assertion",
                                                            @Expose
                                                            @SerializedName("client_type") internal val clientType: String = "android")

    data class FaceBookAcountKitLoginRequest internal constructor(@Expose
                                                                  @SerializedName("access_token") internal val fbTokent: String,
                                                                  @Expose
                                                                  @SerializedName("accountkit_token") internal val accountKitToken: String,
                                                                  @Expose
                                                                  @SerializedName("provider") internal val phone: String = "facebook_sms",

                                                                  @Expose
                                                                  @SerializedName("grant_type") internal val grantType: String = "assertion",
                                                                  @Expose
                                                                  @SerializedName("client_type") internal val clientType: String = "android")

    data class FacebookLoginRequest internal constructor(@Expose
                                                         @SerializedName("access_token") internal val fbTokent: String?,
                                                         @Expose
                                                         @SerializedName("device_token") internal val deviceTokent: String?,

                                                         @Expose
                                                         @SerializedName("provider") internal val phone: String = "facebook",

                                                         @Expose
                                                         @SerializedName("grant_type") internal val grantType: String = "assertion",
                                                         @Expose
                                                         @SerializedName("client_type") internal val clientType: String = "android")
}