package com.ezyplanet.thousandhands.driver.data.preferences

import android.content.Context
import javax.inject.Inject

/**
 * Created by jyotidubey on 04/01/18.
 */
 class AppPreferenceHelper @Inject constructor(context: Context){
    companion object {
        const val PREF_NAME = "pref_arch"
        val PREF_KEY_USER="PREF_KEY_USER"
        val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE"
        val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"
        val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
        val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"
        val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"
        val PREF_KEY_SOCKET_CLIENT= "socket_client"

    }

    val mPrefs by lazy { context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) }

    var token: String? by PreferenceDelegate(mPrefs, PREF_KEY_ACCESS_TOKEN, "")
    var loginMode :Int by PreferenceDelegate(mPrefs, PREF_KEY_USER_LOGGED_IN_MODE,0)
    var user :String? by  PreferenceDelegate(mPrefs, PREF_KEY_USER,"")
    var socketClient :String? by  PreferenceDelegate(mPrefs, PREF_KEY_SOCKET_CLIENT,"")

}






