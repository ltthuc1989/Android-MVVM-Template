package com.ezyplanet.thousandhands.driver.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.ezyplanet.thousandhands.driver.R
import com.ezyplanet.thousandhands.core.ui.widget.MvvmWidget
import com.ezyplanet.thousandhands.driver.databinding.WidgetTaxiHomeBottomBinding

class WidgetTaxiHomeBottom :MvvmWidget<WidgetTaxiHomeBottomBinding>{


    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)


    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun initInflate() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = WidgetTaxiHomeBottomBinding.inflate(inflater,this,true)
    }
}
