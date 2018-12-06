package com.ezyplanet.thousandhands.driver.ui.activity.pickup

import com.ezyplanet.thousandhands.driver.R
import com.ezyplanet.thousandhands.core.ui.base.MvvmActivity
import com.ezyplanet.thousandhands.core.util.extension.replaceFragment
import com.ezyplanet.thousandhands.driver.databinding.ActivityTaxiPickUpBinding


import com.ezyplanet.thousandhands.driver.ui.fragment.TaxiPickUpMap






class TaxiPickupActivity : MvvmActivity<ActivityTaxiPickUpBinding, TaxiPickUpVM>(), TaxiPickUpNav {
    override val layoutId: Int= R.layout.activity_taxi_pick_up
    override val viewModel: TaxiPickUpVM by getLazyViewModel()

    override fun onViewInitialized(binding: ActivityTaxiPickUpBinding) {
        super.onViewInitialized(binding)
        viewModel.navigator=this
        binding.viewModel=viewModel
        viewModel.model=""
        replaceFragment(TaxiPickUpMap())





    }


}