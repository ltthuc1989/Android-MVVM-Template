package com.ezyplanet.thousandhands.driver.ui.widget.toolbar

import android.content.Context
import android.util.AttributeSet
import com.ezyplanet.thousandhands.driver.R
import com.ezyplanet.thousandhands.core.ui.widget.MvvmToolbar
import com.ezyplanet.thousandhands.driver.databinding.ToolbarTaxiPickupBinding

class ToolbarTaxiPickUp :MvvmToolbar<ToolbarTaxiPickupBinding>{
    override val layoutToolbarId: Int= R.layout.toolbar_taxi_pickup

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)


    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun initInflate() {
    }
}