package com.ezyplanet.thousandhands.driver.util

object AppConstants{

    const val LOGIN_SUCESS="login_success"
    const val ZOOM_MAP_LEVEL_17=17
    const val ZOOM_MAP_DEFAULT= 12
    const val GOOGLE_API_KEY = "AIzaSyCoeldy_AVU4e-RnUElNihnPHRiLd3PUtE"
    const val GOOGLE_MAPS_API__PLACE_DETAILS = "https://maps.googleapis.com/maps/api/place/details/json?"
    const val GOOGLE_MAPS_API_GEOCODE= "https://maps.googleapis.com/maps/api/geocode/json?latlng="

    object RequestCode{
       const val ENABLE_LOCATION = 1005
    }

    object ResultCode {
      const  val CANCEL_LOGIN = 1006
      const  val LOGOUT_SUCEESS = 2000

    }

    object ActivityResult {
        const  val REQUEST_PLACE_FILTER = 100
       const val PLACE_PICKER_REQUEST = 101
      const  val CATEGORIES_REQUEST = 102
      const  val FILTER_REQUEST = 103
       const val DELETE_IMAGE = 1008
       const val LOGOUT = 1000


    }
}