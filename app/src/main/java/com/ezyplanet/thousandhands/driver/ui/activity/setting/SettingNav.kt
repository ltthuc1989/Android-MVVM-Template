package com.ezyplanet.thousandhands.driver.ui.activity.setting

import com.ezyplanet.thousandhands.core.ui.base.MvvmNav


interface SettingNav: MvvmNav {
    fun openChangePasswordScreen()
    fun logout()

}