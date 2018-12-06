package com.ezyplanet.thousandhands.driver.ui.activity.home

import androidx.lifecycle.ViewModel
import com.ezyplanet.thousandhands.core.di.qualifier.ViewModelKey

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TaxiHomeVMBuilder{
    @Binds
    @IntoMap
    @ViewModelKey(TaxiHomeVM::class)
    abstract fun bindTaxiHomeVM(taxiHomeVM: TaxiHomeVM): ViewModel
}