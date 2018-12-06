package com.ezyplanet.thousandhands.core.ui.listener

interface RetryCallback<T> {
    val request: T
    fun onSendEvent()
    fun onRetry()


}
