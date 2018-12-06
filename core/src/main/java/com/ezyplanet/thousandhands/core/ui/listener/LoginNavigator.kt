package com.ezyplanet.thousandhands.core.ui.listener

import com.ezyplanet.thousandhands.core.ui.base.MvvmNav


interface LoginNavigator:MvvmNav{


         fun facebookLoginClick()
         fun phoneLoginClick()
         fun passwordLoginClick()
         fun openHomeActivity()
         fun showLoading()
         fun hideLoading()




}