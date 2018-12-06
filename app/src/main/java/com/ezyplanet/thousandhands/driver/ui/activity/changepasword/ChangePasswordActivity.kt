package com.ezyplanet.thousandhands.driver.ui.activity.changepasword

import com.ezyplanet.thousandhands.driver.R
import com.ezyplanet.thousandhands.core.ui.base.MvvmActivity
import com.ezyplanet.thousandhands.driver.databinding.ActivityChangePasswordBinding




class ChangePasswordActivity : MvvmActivity<ActivityChangePasswordBinding, ChangePasswordVM>(), ChangePasswordNav {
    override val viewModel: ChangePasswordVM by getLazyViewModel()
    override val layoutId: Int = R.layout.activity_change_password

    override fun onViewInitialized(binding: ActivityChangePasswordBinding) {
        super.onViewInitialized(binding)
       // binding.viewModel = viewModel
        viewModel.navigator = this

    }

    override fun showSumbitDlg() {
       // SubmitDlg.newInstance(getString(R.string.password_changed_successfully)).show(supportFragmentManager,SubmitDlg.tag)

    }
}