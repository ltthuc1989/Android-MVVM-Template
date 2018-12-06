package com.ezyplanet.thousandhands.driver.ui.activity.login

import com.ezyplanet.thousandhands.core.ui.base.MvvmNav


interface LoginNavigator: MvvmNav {


         fun facebookLoginClick()
         fun phoneLoginClick()
         fun passwordLoginClick()
         fun openHomeActivity()
         fun showLoading()
         fun hideLoading()




}