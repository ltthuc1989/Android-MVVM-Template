package com.ezyplanet.thousandhands.driver.ui.activity.login

import androidx.lifecycle.ViewModel
import com.ezyplanet.thousandhands.core.di.qualifier.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class LoginVMBuilder{
    @Binds
    @IntoMap
    @ViewModelKey(LoginVM::class)
    abstract fun bindLoginTourVM(loginTourVM: LoginVM): ViewModel
}