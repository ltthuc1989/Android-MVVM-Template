package com.ezyplanet.thousandhands.driver.di.component

import android.app.Application
import com.ezyplanet.thousandhands.driver.DriverApp
import com.ezyplanet.thousandhands.driver.di.builder.ActivityBuilder
import com.ezyplanet.thousandhands.driver.di.module.AppModule
import com.ezyplanet.thousandhands.driver.di.module.UtilModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Main Application [Component] that included all of modules and sub components.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    (AppModule::class),
    (UtilModule::class),
    (AndroidInjectionModule::class),
    (ActivityBuilder::class)])
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(app: DriverApp)
}
