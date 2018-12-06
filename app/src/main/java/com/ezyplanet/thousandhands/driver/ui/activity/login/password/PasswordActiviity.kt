package com.ezyplanet.thousandhands.driver.ui.activity.login.password

import android.content.Intent
import android.widget.Toast
import com.ezyplanet.thousandhands.core.ui.base.MvvmActivity
import com.ezyplanet.thousandhands.core.util.extension.gotoActivity
import com.ezyplanet.thousandhands.driver.R
import com.ezyplanet.thousandhands.driver.databinding.ActivityLoginPasswordBinding
import com.ezyplanet.thousandhands.driver.ui.activity.home.TaxiHomeActivity
import com.ezyplanet.thousandhands.driver.util.AppConstants


class PasswordActiviity : MvvmActivity<ActivityLoginPasswordBinding, PasswordVM>(), PasswordNavigator {
    override val viewModel: PasswordVM by getLazyViewModel()

    override val layoutId: Int= R.layout.activity_login_password


    override fun onViewInitialized(binding: ActivityLoginPasswordBinding) {
        super.onViewInitialized(binding)
        binding.viewModel=viewModel
        viewModel.navigator=this

    }
    override fun openHomeActivity() {
       // val intent = Intent()
       // intent.putExtra(AppConstants.LOGIN_SUCESS,true)
       // setResult(REQUEST_PASSWORD_LOGIN_CODE,intent)
        //finish()
        gotoActivity(TaxiHomeActivity::class,true)

    }

    override fun serverLogin() {
        var email=binding.edtEmail.text.toString()
        var password=binding.edtPassword.text.toString()
        if(viewModel.isEmailAndPasswordValid(email,password)) {
            viewModel.loginClick(email, password)
        }else{
            Toast.makeText(this, getString(R.string.input_email), Toast.LENGTH_SHORT).show()
        }
    }
}
