package com.ezyplanet.thousandhands.driver.ui.dialog.accepttrip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ezyplanet.thousandhands.core.ui.base.BaseViewModel
import com.ezyplanet.thousandhands.driver.data.network.AppDataManager
import com.ezyplanet.thousandhands.driver.data.network.wraper.BookTripEvent
import com.ezyplanet.thousandhands.util.connectivity.BaseConnectionManager
import javax.inject.Inject

class TaxiAcceptTripVM @Inject constructor(val appDataManager: AppDataManager,connectionManager: BaseConnectionManager) : BaseViewModel<TaxiAcceptTripNav>(connectionManager) {

    private val model = MutableLiveData<BookTripEvent>()

    fun getModel() :LiveData<BookTripEvent> = model
    fun setModel(bookTripEvent: BookTripEvent){
        model.postValue(bookTripEvent)
    }

    fun openPickUpScreen(){

        navigator?.showProgress()
        compositeDisposable.add(appDataManager!!.putAcceptTrip(model.value?.data?.id)
                .compose(schedulerProvider?.ioToMainSingleScheduler())
                .subscribe({

                    navigator?.hideProgress()
                    navigator?.openPickUpActivity()


                }, {

                    apiErrorObservable(it)


                }))

    }


}