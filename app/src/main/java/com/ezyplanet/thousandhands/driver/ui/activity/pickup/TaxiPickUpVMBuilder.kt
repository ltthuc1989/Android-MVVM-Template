package com.ezyplanet.thousandhands.driver.ui.activity.pickup

import androidx.lifecycle.ViewModel
import com.ezyplanet.thousandhands.core.di.qualifier.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TaxiPickUpVMBuilder{
    @Binds
    @IntoMap
    @ViewModelKey(TaxiPickUpVM::class)
    abstract fun bindTaxiHomeVM(taxiPickUpVM: TaxiPickUpVM): ViewModel
}