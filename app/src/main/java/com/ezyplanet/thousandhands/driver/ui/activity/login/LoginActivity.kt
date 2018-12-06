package com.ezyplanet.thousandhands.driver.ui.activity.login

import android.content.Context
import android.content.Intent

import com.ezyplanet.thousandhands.core.ui.base.MvvmActivity
import com.ezyplanet.thousandhands.driver.databinding.ActivityLoginMainBinding
import com.ezyplanet.thousandhands.driver.R
import com.ezyplanet.thousandhands.driver.ui.fragment.accountkit.AccountKitAdvancedUIManager
import com.ezyplanet.thousandhands.driver.ui.activity.login.password.PasswordActiviity
import com.facebook.AccessToken
import com.facebook.accountkit.AccountKitLoginResult
import com.facebook.accountkit.ui.AccountKitActivity
import com.facebook.accountkit.ui.AccountKitConfiguration
import com.facebook.accountkit.ui.LoginType
import com.facebook.internal.CallbackManagerImpl
import com.facebook.login.LoginManager
import java.util.*
import com.google.android.gms.common.util.IOUtils.toByteArray
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import android.util.Base64
import android.util.Log
import androidx.fragment.app.FragmentActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LoginActivity : MvvmActivity<ActivityLoginMainBinding, LoginVM>(), LoginNavigator {

    var APP_REQUEST_PHONE_SMS_CODE = 99
    var APP_REQUEST_PHONE_FACEBOOK_SMS_CODE = 11
    override val viewModel: LoginVM by getLazyViewModel()


    override val layoutId: Int= R.layout.activity_login_main
    var countryCode:String?=null

    override fun onViewInitialized(binding: ActivityLoginMainBinding) {
        super.onViewInitialized(binding)
        binding.viewModel=viewModel
        viewModel.navigator=this
        countryCode= viewModel.countryCode
        printHashKey(this)
    }
    override fun facebookLoginClick() {
        if (AccessToken.getCurrentAccessToken() != null) {
            LoginManager.getInstance().logOut()
        }
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList<String>("public_profile", "email"))

    }

    override fun phoneLoginClick() {

        val intent = Intent(this, AccountKitActivity::class.java)

        val configurationBuilder: AccountKitConfiguration.AccountKitConfigurationBuilder
        if (countryCode!=null) {
           // if (accountKitBundle == null) {
                configurationBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(
                        LoginType.PHONE,
                        AccountKitActivity.ResponseType.TOKEN).setFacebookNotificationsEnabled(false).setDefaultCountryCode(countryCode)
                        .setAdvancedUIManager(AccountKitAdvancedUIManager())
           // }// or .ResponseType.TOKEN
//            else {
//                configurationBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(
//                        LoginType.PHONE,
//                        AccountKitActivity.ResponseType.TOKEN).setFacebookNotificationsEnabled(false).setDefaultCountryCode(countryCode).setInitialPhoneNumber(PhoneNumber(countryCode, accountKitBundle.getPhone()))
//                        .setAdvancedUIManager(AccountKitAdvancedUIManager())
//            }
        } else {
            configurationBuilder = AccountKitConfiguration.AccountKitConfigurationBuilder(
                    LoginType.PHONE,
                    AccountKitActivity.ResponseType.TOKEN).setFacebookNotificationsEnabled(false)
                    .setAdvancedUIManager(AccountKitAdvancedUIManager()) // or .ResponseType.TOKEN
        }

//        if (getArguments() != null) {
//
//            setMultiplExtra(intent, getArguments())
//
//        }
        intent.putExtra(
                AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                configurationBuilder.build())
       startActivityForResult(intent, APP_REQUEST_PHONE_SMS_CODE)

    }


    override fun passwordLoginClick() {
        val intent = Intent(this, PasswordActiviity::class.java)
        startActivityForResult(intent,REQUEST_PASSWORD_LOGIN_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == APP_REQUEST_PHONE_SMS_CODE || requestCode == APP_REQUEST_PHONE_FACEBOOK_SMS_CODE) { // confirm that this response matches your request
            if (data == null) {
                return
            }
            if (resultCode == 0) {
                hideProgress()
            }
            val loginResult = data.getParcelableExtra<AccountKitLoginResult>(AccountKitLoginResult.RESULT_KEY)
            val toastMessage: String
            if (loginResult.error != null) {
                toastMessage = loginResult.error!!.errorType.message
                //listener.onLoginFail(toastMessage)
            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled"
               // listener.onLoginCancel(toastMessage)
            } else {
                if (requestCode == APP_REQUEST_PHONE_SMS_CODE) {
                  //  TuneUtils.measureEvent(loginActivity, "Register - Phone Number")

                     viewModel.facebookSmsLogin(loginResult.accessToken!!.token)
                } else {
                    viewModel.facebookLogin(loginResult.accessToken!!.token)
                   // TuneUtils.measureEvent(loginActivity, "Register - Facebook")

                }
            }

            // Surface the result to your customer in an appropriate way.

        } else if (requestCode == CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()) {
            if (data == null) {
                hideProgress()
            }
            viewModel.onActivityResult(requestCode, resultCode, data)
        }else if(requestCode==REQUEST_PASSWORD_LOGIN_CODE){
           if(data==null)
               return
            setResult(resultCode,data)
            finish()
        }
    }



    override fun openHomeActivity() {

        setResult(REQUEST_PASSWORD_LOGIN_CODE)
        finish()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun printHashKey(pContext: Context) {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val hashKey = String(Base64.encode(md.digest(), 0))
                Log.i(javaClass.simpleName, "printHashKey() Hash Key: $hashKey")
            }
        } catch (e: NoSuchAlgorithmException) {
            Log.e(javaClass.simpleName, "printHashKey()", e)
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "printHashKey()", e)
        }

    }

}
