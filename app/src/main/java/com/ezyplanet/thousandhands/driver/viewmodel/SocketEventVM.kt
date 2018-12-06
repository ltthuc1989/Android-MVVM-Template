package com.ezyplanet.thousandhands.driver.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.ezyplanet.thousandhands.driver.data.network.wraper.FayeMessageWrapper
import com.ezyplanet.thousandhands.driver.ui.dialog.accepttrip.TripInfo
import com.ezyplanet.thousandhands.util.livedata.HotEventRx

class SocketEventVM :ViewModel(){
    val onNewMessage = MutableLiveData<HotEventRx<Any>>()
}