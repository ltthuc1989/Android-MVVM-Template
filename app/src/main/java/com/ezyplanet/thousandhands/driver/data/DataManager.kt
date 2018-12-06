package com.irmansyah.catalogmoviekotlin.data

import com.ezyplanet.thousandhands.driver.data.network.ApiHelper
import com.ezyplanet.thousandhands.driver.data.network.response.User

interface DataManager : ApiHelper {
    fun updateApiHeader(userId: Long?, accessToken: String)

    fun setUserAsLoggedOut()
    fun setAccessTokent(loggedInMode: LoggedInMode,access_token:String?)


    fun updateUserInfo(
            user: User,

            loggedInMode: LoggedInMode
    )
    fun updateUserInfo(
            user: User?
    )

    enum class LoggedInMode private constructor(val type: Int) {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3)
    }
    fun getUserInfo():User?

    fun getCountryCode():String?



}