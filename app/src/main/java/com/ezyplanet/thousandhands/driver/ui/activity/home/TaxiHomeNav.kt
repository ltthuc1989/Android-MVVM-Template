package com.ezyplanet.thousandhands.driver.ui.activity.home

import com.ezyplanet.thousandhands.core.ui.base.MvvmNav

interface TaxiHomeNav :MvvmNav{
   fun openAcceptTripDlg(data: Any?)
   fun enbalePlayService():Boolean?
   fun setUpLocationTrack()
   fun startWebSocket()
}