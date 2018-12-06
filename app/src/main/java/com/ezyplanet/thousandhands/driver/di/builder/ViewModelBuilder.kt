package com.ezyplanet.thousandhands.driver.di.builder

import androidx.lifecycle.ViewModelProvider
import com.ezyplanet.thousandhands.core.ui.fragment.tab.TabVMBuilder
import com.ezyplanet.thousandhands.core.viewmodel.ArchViewModelFactory
import com.ezyplanet.thousandhands.driver.ui.activity.home.TaxiHomeVMBuilder
import com.ezyplanet.thousandhands.driver.ui.activity.login.LoginVMBuilder
import com.ezyplanet.thousandhands.driver.ui.activity.login.password.PasswordVMBuilder
import com.ezyplanet.thousandhands.driver.ui.activity.pickup.TaxiPickUpVMBuilder
import com.ezyplanet.thousandhands.driver.ui.activity.setting.SettingVMBuilder
import com.ezyplanet.thousandhands.driver.ui.activity.splash.SplashViewModelBuilder
import com.ezyplanet.thousandhands.driver.ui.dialog.accepttrip.TaxiAcceptTripVMBuilder




import dagger.Binds
import dagger.Module

/**
 * With this module all of ViewModels binds into generated Map<Class, ViewModel> and the map
 * will be injected in [ArchViewModelFactory]. In order to do this, we have to bind all
 * ViewModelBuilder modules in this module.
 *
 * And finally [ArchViewModelFactory] will be provided as [ViewModelProvider.Factory].
 *
 */
@Module(includes = [
    (SplashViewModelBuilder::class),
    (SettingVMBuilder::class),
    (TabVMBuilder::class),
    (TaxiHomeVMBuilder::class),
    (PasswordVMBuilder::class),
    (LoginVMBuilder::class),
    (TaxiAcceptTripVMBuilder::class),
    (TaxiPickUpVMBuilder::class)

])
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(archViewModelFactory: ArchViewModelFactory): ViewModelProvider.Factory
}