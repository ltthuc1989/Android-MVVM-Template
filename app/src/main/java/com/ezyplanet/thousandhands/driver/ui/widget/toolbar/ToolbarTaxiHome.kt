package com.ezyplanet.thousandhands.driver.ui.widget.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar

import com.ezyplanet.thousandhands.driver.databinding.ToolbarTaxiHomeBinding
import com.ezyplanet.thousandhands.driver.ui.widget.listener.ToolbarTaxiHomeNav


class ToolbarTaxiHome :Toolbar{

lateinit var binding:ToolbarTaxiHomeBinding
    constructor(context: Context?) : super(context){
        initInflate()

    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        initInflate()

    }


    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initInflate()
    }

    fun initInflate() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ToolbarTaxiHomeBinding.inflate(inflater,this,true)

    }

    fun setListner(listener:ToolbarTaxiHomeNav){
         binding.listener=listener
    }


}