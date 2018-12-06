package com.ezyplanet.thousandhands.driver.ui.activity.pickup


import com.ezyplanet.thousandhands.core.ui.base.BaseViewModel
import com.ezyplanet.thousandhands.core.util.SchedulerProvider

import com.ezyplanet.thousandhands.driver.data.network.AppDataManager


import com.ezyplanet.thousandhands.util.connectivity.BaseConnectionManager
import javax.inject.Inject

class TaxiPickUpVM @Inject constructor(val appDataManager: AppDataManager,
                                       schedulerProvider: SchedulerProvider, connectionManager: BaseConnectionManager
) : BaseViewModel<TaxiPickUpNav>(schedulerProvider, connectionManager) {

    var model = ""

    fun hereClick() {
        if (model.isEmpty()) return
        navigator?.showProgress()
        compositeDisposable.add(appDataManager!!.putArrive(model)
                .compose(schedulerProvider?.ioToMainSingleScheduler())
                .subscribe({

                    navigator?.hideProgress()


                }, {

                    apiErrorObservable(it)


                }))

    }

    fun pickUpClick() {
        if (model.isEmpty()) return
        navigator?.showProgress()
        compositeDisposable.add(appDataManager!!.putStartTrip(model)
                .compose(schedulerProvider?.ioToMainSingleScheduler())
                .subscribe({

                    navigator?.hideProgress()


                }, {

                    apiErrorObservable(it)


                }))
    }


}