package com.ezyplanet.thousandhands.driver.ui.activity.home

import com.ezyplanet.thousandhands.driver.ui.dialog.accepttrip.TaxiAccepTripDlg
import com.ezyplanet.thousandhands.driver.ui.fragment.TaxiHomeMapFrag
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TaxiHomeProvider {
    @ContributesAndroidInjector
    abstract fun provideTaxiHomeMapFragment(): TaxiHomeMapFrag
    @ContributesAndroidInjector
    abstract fun provideTaxiAcceptDlgFragment(): TaxiAccepTripDlg
}