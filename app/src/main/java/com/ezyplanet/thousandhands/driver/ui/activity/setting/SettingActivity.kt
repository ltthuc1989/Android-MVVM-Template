package com.ezyplanet.thousandhands.driver.ui.activity.setting


import android.content.Intent

import com.ezyplanet.thousandhands.driver.R
import com.ezyplanet.thousandhands.core.ui.base.MvvmActivity
import com.ezyplanet.thousandhands.driver.DriverApp
import com.ezyplanet.thousandhands.driver.databinding.ActivitySettingBinding

import com.ezyplanet.thousandhands.driver.ui.activity.changepasword.ChangePasswordActivity
import com.ezyplanet.thousandhands.driver.util.AppConstants



class SettingActivity : MvvmActivity<ActivitySettingBinding, SettingVM>(), SettingNav {
    override val viewModel: SettingVM by getLazyViewModel()

    override val layoutId: Int = R.layout.activity_setting


    override fun onViewInitialized(binding: ActivitySettingBinding) {
        super.onViewInitialized(binding)
        binding.viewModel = viewModel
        viewModel.navigator = this

    }

    override fun openChangePasswordScreen() {
        val intent = Intent(this, ChangePasswordActivity::class.java)
        startActivity(intent)
    }

    override fun logout() {
        setResult(AppConstants.ResultCode.LOGOUT_SUCEESS)
        finish()


    }


}