package com.ezyplanet.thousandhands.core.ui.fragment.tab


import androidx.lifecycle.ViewModel
import com.ezyplanet.thousandhands.core.di.qualifier.ViewModelKey
import com.ezyplanet.thousandhands.core.ui.fragment.tab.TabVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TabVMBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(TabVM::class)
    abstract fun bindMessgeListViewModel(tabVM: TabVM): ViewModel
}