package com.ezyplanet.thousandhands.driver.ui.dialog.accepttrip

import com.google.gson.annotations.SerializedName

class TripInfo{
    @SerializedName("id")
    private var id: String? = null

    @SerializedName("distance")
    private var distance: String? = null

    @SerializedName("formatted_price_by_distance")
    private var formatted_price_by_distance: String? = null

    @SerializedName("formatted_price_payment_driver")
    private var formatted_price_payment_driver: String? = null

    @SerializedName("formatted_price_receipt_customer")
    private var formatted_price_receipt_customer: String? = null


    @SerializedName("origin")
    private var origin: Origin? = null

    @SerializedName("destination")
    private var destination: Origin? = null

    class Origin{
        @SerializedName("latitude")
        private var latitude: Float? = null

        @SerializedName("longitude")
        private var longitude: Float? = null

        @SerializedName("address")
        private var address: String? = null

        fun getAddress(): String? {
            return address
        }
    }

    fun getDistance(): String? {
        return distance
    }

    fun getFormatted_price_by_distance(): String? {
        return formatted_price_by_distance
    }

    fun getFormatted_price_payment_driver(): String? {
        return formatted_price_payment_driver
    }

    fun getOriginAddress(): String? {
        return origin?.getAddress()
    }

    fun getDestinationAddress(): String? {
        return destination?.getAddress()
    }

    fun getFormattedPriceReceiptCustomer(): String? {
        return formatted_price_receipt_customer
    }
}