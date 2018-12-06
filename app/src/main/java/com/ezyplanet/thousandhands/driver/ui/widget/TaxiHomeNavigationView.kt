package com.ezyplanet.thousandhands.driver.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ezyplanet.thousandhands.driver.databinding.NavTaxiHomeHeaderBinding

import com.ezyplanet.thousandhands.driver.ui.activity.home.TaxiHomeMD
import com.ezyplanet.thousandhands.driver.ui.widget.listener.HomeNavigationViewNav
import com.ezyplanet.thousandhands.driver.ui.widget.listener.ToolbarTaxiHomeNav

import com.google.android.material.navigation.NavigationView

class TaxiHomeNavigationView :NavigationView{
    lateinit var binding:NavTaxiHomeHeaderBinding
    var _viewModel:TaxiHomeMD?=null
    var viewModel:TaxiHomeMD?
    get() = _viewModel
    set(value) {
        _viewModel= value
        binding.viewModel=viewModel
    }

    constructor(context: Context?) : super(context) {
        initInflate()

    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initInflate()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initInflate()
    }

    private fun initInflate(){
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = NavTaxiHomeHeaderBinding.inflate(inflater)
        addHeaderView(binding.root)
        binding.viewModel=viewModel


    }
    fun setListner(listener: HomeNavigationViewNav){
        binding.listener=listener
    }


}