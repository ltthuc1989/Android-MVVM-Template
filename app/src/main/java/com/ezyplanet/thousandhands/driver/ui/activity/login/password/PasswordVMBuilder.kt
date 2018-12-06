package com.ezyplanet.thousandhands.driver.ui.activity.login.password

import androidx.lifecycle.ViewModel
import com.ezyplanet.thousandhands.core.di.qualifier.ViewModelKey

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PasswordVMBuilder{
    @Binds
    @IntoMap
    @ViewModelKey(PasswordVM::class)
    abstract fun bindPassWordVM(passwordVM: PasswordVM): ViewModel
}