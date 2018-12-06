package com.ezyplanet.thousandhands.driver.data.network.request

import android.os.Build
import android.text.TextUtils

class DeviceRequest {

    /**
     * token : abc
     * client : ios
     * device_name : HTC One
     */

    var device: DeviceEntity? = null

    class DeviceEntity {
        var token: String? = null
        var client: String? = null
        var device_name: String? = null
        var push_service: String? = null
        fun setDevice_name() {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            if (model.startsWith(manufacturer)) {
                this.device_name = capitalize(model)
            }
            this.device_name = capitalize(manufacturer) + " " + model
        }

        fun setDeviceInfo(version: String) {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            if (model.startsWith(manufacturer)) {
                this.device_name = capitalize(model)
            }
            this.device_name = capitalize(manufacturer) + " - " + model + " - " + version
        }

        private fun capitalize(str: String): String? {
            if (TextUtils.isEmpty(str)) {
                return str
            }
            val arr = str.toCharArray()
            var capitalizeNext = true
            var phrase = ""
            for (c in arr) {
                if (capitalizeNext && Character.isLetter(c)) {
                    phrase += Character.toUpperCase(c)
                    capitalizeNext = false
                    continue
                } else if (Character.isWhitespace(c)) {
                    capitalizeNext = true
                }
                phrase += c
            }
            return phrase
        }
    }
}