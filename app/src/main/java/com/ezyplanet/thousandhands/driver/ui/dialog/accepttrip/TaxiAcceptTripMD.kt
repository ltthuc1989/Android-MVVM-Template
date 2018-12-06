package com.ezyplanet.thousandhands.driver.ui.dialog.accepttrip

import android.os.Parcel
import android.os.Parcelable

data class TaxiAcceptTripMD constructor(val distance:String,val formatted_price_by_distance:String, val formatted_price_payment_driver: String, val formatted_price_receipt_customer: String, val origin_address: String
                                        , val destination_address: String

): Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(distance)
        parcel.writeString(formatted_price_by_distance)
        parcel.writeString(formatted_price_payment_driver)
        parcel.writeString(formatted_price_receipt_customer)
        parcel.writeString(origin_address)
        parcel.writeString(destination_address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaxiAcceptTripMD> {
        override fun createFromParcel(parcel: Parcel): TaxiAcceptTripMD {
            return TaxiAcceptTripMD(parcel)
        }

        override fun newArray(size: Int): Array<TaxiAcceptTripMD?> {
            return arrayOfNulls(size)
        }
    }

}