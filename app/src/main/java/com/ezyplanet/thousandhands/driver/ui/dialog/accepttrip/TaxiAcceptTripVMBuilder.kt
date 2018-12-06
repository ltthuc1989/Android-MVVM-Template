package com.ezyplanet.thousandhands.driver.ui.dialog.accepttrip

import androidx.lifecycle.ViewModel
import com.ezyplanet.thousandhands.core.di.qualifier.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TaxiAcceptTripVMBuilder{
    @Binds
    @IntoMap
    @ViewModelKey(TaxiAcceptTripVM::class)
    abstract fun bindTaxiAcceptTripVM(taxiAcceptTripVM: TaxiAcceptTripVM): ViewModel
}