package com.ezyplanet.thousandhands.driver.ui.activity.splash

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.ezyplanet.thousandhands.core.ui.base.MvvmActivity
import com.ezyplanet.thousandhands.core.util.extension.gotoActivity
import com.ezyplanet.thousandhands.driver.databinding.ActivitySplashBinding
import com.ezyplanet.thousandhands.driver.R
import com.ezyplanet.thousandhands.driver.ui.activity.home.TaxiHomeActivity
import com.ezyplanet.thousandhands.driver.ui.activity.login.LoginActivity
import java.util.*


class SplashActivity : MvvmActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    private val ALL_PERMISSIONS = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE)
    private val PERMISSION = 1001

    override val layoutId = R.layout.activity_splash
    override val viewModel: SplashViewModel by getLazyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M Permission check
            requestPermission(ALL_PERMISSIONS, PERMISSION)
        } else {
           // Handler().postDelayed({ viewModel.initData(this) }, 1000)
        viewModel.getSetting()
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION -> viewModel.getSetting()
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onViewInitialized(binding: ActivitySplashBinding) {
        super.onViewInitialized(binding)
        binding.viewModel=viewModel
        viewModel.navigator=this
    }



    fun requestPermission(permissionsList: Array<String>?, requestCode: Int) {
        if (permissionsList == null) {
            Handler().postDelayed({
                viewModel.getSetting()}, 1000)
            return
        }
        val permissionNeeded = ArrayList<String>()
        for (permission in permissionsList) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionNeeded.add(permission)
            }
        }
        if (permissionNeeded.size > 0) {
            ActivityCompat.requestPermissions(this,
                    permissionNeeded.toTypedArray(), requestCode)
        } else {
            Handler().postDelayed({ viewModel.getSetting() }, 1000)
        }
    }


    override fun openLoginActivity() {
        gotoActivity(LoginActivity::class,true)

    }

    override fun openHomeActivity() {

        gotoActivity(TaxiHomeActivity::class,true)
    }




}