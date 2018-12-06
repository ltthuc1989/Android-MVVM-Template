package com.ezyplanet.thousandhands.driver.ui.activity.splash

import androidx.lifecycle.ViewModel
import com.ezyplanet.thousandhands.core.di.qualifier.ViewModelKey

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SplashViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindHomeViewModel(splashViewModel: SplashViewModel): ViewModel
}