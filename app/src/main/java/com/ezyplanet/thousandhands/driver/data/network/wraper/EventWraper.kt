package com.ezyplanet.thousandhands.driver.data.network.wraper


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//Book trip
@Parcelize
data class BookTripEvent constructor(val data: Data?,
                                     val origin:Location?,val destination:Location?): Parcelable
@Parcelize
data class Data constructor(val id:String,val distance:Float,
                            val formatted_price_by_distance:String? ,
                            val formatted_price_payment_driver:String?,
                            val formatted_price_receipt_customer:String?):Parcelable
@Parcelize
data class Location constructor(val latitude:Double,val  longitude:Double,val address:String?):Parcelable
//

//other event