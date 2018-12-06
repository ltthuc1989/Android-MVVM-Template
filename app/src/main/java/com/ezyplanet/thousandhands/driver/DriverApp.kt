package com.ezyplanet.thousandhands.driver

import android.app.Activity
import android.app.Application
import com.ezyplanet.thousandhands.driver.data.SocketHelper
import com.ezyplanet.thousandhands.driver.di.component.DaggerAppComponent

import com.facebook.FacebookSdk
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class DriverApp : Application(), HasActivityInjector {
    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject   lateinit var socketHelper: SocketHelper




    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(this)
        initDebugModeValues()
        initOneSignal()
        initLeakcanary()
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    private fun initDebugModeValues() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    /**
     * @return android dispatching injector for providing dependencies in android activities
     */
    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

    internal fun initOneSignal() {


    }
    private fun initLeakcanary(){
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
    }

}