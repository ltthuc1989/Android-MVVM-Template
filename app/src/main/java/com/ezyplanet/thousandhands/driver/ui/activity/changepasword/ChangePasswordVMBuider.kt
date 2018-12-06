package com.ezyplanet.thousandhands.driver.ui.activity.changepasword

import androidx.lifecycle.ViewModel
import com.ezyplanet.thousandhands.core.di.qualifier.ViewModelKey

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChangePasswordVMBuider{
    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordVM::class)
    abstract fun bindChangePasswordVM(changePasswordVM: ChangePasswordVM): ViewModel
}