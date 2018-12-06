package com.ezyplanet.thousandhands.driver.di.builder




import com.ezyplanet.thousandhands.driver.ui.activity.home.TaxiHomeActivity
import com.ezyplanet.thousandhands.driver.ui.activity.home.TaxiHomeProvider
import com.ezyplanet.thousandhands.driver.ui.activity.login.LoginActivity
import com.ezyplanet.thousandhands.driver.ui.activity.login.password.PasswordActiviity
import com.ezyplanet.thousandhands.driver.ui.activity.pickup.TaxiPickupActivity
import com.ezyplanet.thousandhands.driver.ui.activity.setting.SettingActivity
import com.ezyplanet.thousandhands.driver.ui.activity.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * The Main Module for binding all of activities.
 * Every Activity should contribute with it's related modules
 */
@Module
abstract class ActivityBuilder {



    @ContributesAndroidInjector
    internal abstract fun bindLoginTourActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun bindSplashActivity(): SplashActivity



    @ContributesAndroidInjector(modules = [(TaxiHomeProvider::class)])
    internal abstract fun bindTaxiHomeActivity(): TaxiHomeActivity

    @ContributesAndroidInjector
    internal abstract fun bindTaxiPickupActivity(): TaxiPickupActivity

    @ContributesAndroidInjector
    internal abstract fun bindPassWordActivity(): PasswordActiviity

    @ContributesAndroidInjector
    internal abstract fun bindSettingActivity(): SettingActivity






}
