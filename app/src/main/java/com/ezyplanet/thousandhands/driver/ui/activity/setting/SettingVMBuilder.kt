package com.ezyplanet.thousandhands.driver.ui.activity.setting

import androidx.lifecycle.ViewModel
import com.ezyplanet.thousandhands.core.di.qualifier.ViewModelKey


import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SettingVMBuilder{
    @Binds
    @IntoMap
    @ViewModelKey(SettingVM::class)
    abstract fun bindSettingVM(setingVM: SettingVM): ViewModel
}