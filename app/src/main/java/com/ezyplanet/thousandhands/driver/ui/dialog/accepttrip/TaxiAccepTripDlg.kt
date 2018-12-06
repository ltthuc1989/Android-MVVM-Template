package com.ezyplanet.thousandhands.driver.ui.dialog.accepttrip

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import com.ezyplanet.thousandhands.driver.R


import com.ezyplanet.thousandhands.core.ui.base.MvvmDialogFrag
import com.ezyplanet.thousandhands.core.ui.base.ViewModelScope
import com.ezyplanet.thousandhands.core.util.ScreenUtils
import com.ezyplanet.thousandhands.core.util.extension.gotoActivity
import com.ezyplanet.thousandhands.core.util.extension.putArgs
import com.ezyplanet.thousandhands.driver.data.network.wraper.BookTripEvent
import com.ezyplanet.thousandhands.driver.databinding.DialogTaxiAcceptTripBinding
import com.ezyplanet.thousandhands.driver.ui.activity.pickup.TaxiPickupActivity

class TaxiAccepTripDlg : MvvmDialogFrag<TaxiAcceptTripVM, DialogTaxiAcceptTripBinding>(), TaxiAcceptTripNav {
    companion object {
        const val key="message"

        private val key_trip= "trip_booked"

        val tag= this::class.java.simpleName
        fun newInstance(data: Any?) = TaxiAccepTripDlg().putArgs {
          putParcelable(key_trip,data as BookTripEvent)


        }
    }


    override val viewModel: TaxiAcceptTripVM by getLazyViewModel(ViewModelScope.ACTIVITY)
    override val layoutId: Int = R.layout.dialog_taxi_accept_trip
    override fun setUpNavigator() {
        viewModel.navigator = this
    }

    override fun onChildStart() {
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!
                    .setLayout((ScreenUtils.getScreenWidth(context) ), ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }



    override fun onViewInitialized(binding: DialogTaxiAcceptTripBinding) {
        binding.viewModel=viewModel
        arguments?.getParcelable<BookTripEvent>(key_trip)?.let{
            viewModel.setModel(it)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.setBackgroundDrawable(
                ColorDrawable(Color.WHITE))
        return dialog
    }

    override fun openPickUpActivity() {
        activity?.gotoActivity(TaxiPickupActivity::class)
        dismiss()
    }
}