package com.ezyplanet.thousandhands.driver.ui.activity.splash

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.util.Log
import com.ezyplanet.thousandhands.core.ui.base.BaseViewModel
import com.ezyplanet.thousandhands.core.util.DialogFactory
import com.ezyplanet.thousandhands.core.util.SchedulerProvider
import com.ezyplanet.thousandhands.driver.R
import com.ezyplanet.thousandhands.driver.data.network.AppDataManager
import com.ezyplanet.thousandhands.util.connectivity.BaseConnectionManager

import javax.inject.Inject


class SplashViewModel @Inject constructor(val appDataManager: AppDataManager, schedulerProvider: SchedulerProvider, connectionManager: BaseConnectionManager
) : BaseViewModel<SplashNavigator>(schedulerProvider,connectionManager) {


    fun startSeeding() {

    }

    fun decideNextActivity() {
        if(appDataManager.isLogin()) navigator?.openHomeActivity()
        else navigator?.openLoginActivity()


    }

//    fun initData(activity: Activity){
//        if(isNetworkConnected()==true){
//            if (!activity.isFinishing()) {
//                DialogFactory.createGenericErrorDialog(activity, activity.getString(R.string.network_isNotAvailable),
//                        object : DialogInterface.OnClickListener{
//                            override fun onClick(dialog: DialogInterface?, which: Int) {
//                                dialog?.dismiss()
//                            }
//                        },object :DialogInterface.OnClickListener {
//                    override fun onClick(dialog: DialogInterface?, which: Int) {
//                        dialog?.dismiss()
//                     }
//                }).show()
//
//
//            }
//
//            return
//        }
//    }

    fun getVersion(context: Context): String {
        try {
            return "v"+context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e("About", e.message)
        }

        return ""
    }
    fun getSetting(){

            if(appDataManager?.setting==null){
                navigator?.showProgress()
                compositeDisposable.add(appDataManager!!.getSetting()
                        .compose(schedulerProvider?.ioToMainSingleScheduler())


                        .subscribe({

                            appDataManager?.setting=it
                            navigator?.hideProgress()
                            decideNextActivity()


                        }, {

                            apiErrorObservable(it)

                        }))
            }else{
                decideNextActivity()
            }



    }

}