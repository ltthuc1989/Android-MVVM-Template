package com.ezyplanet.thousandhands.core.util

import android.util.Patterns

import com.ezyplanet.thousandhands.core.ui.base.MvvmActivity
import com.ezyplanet.thousandhands.core.ui.widget.TransparentProgressDialog

/**
 * Created by jyotidubey on 11/01/18.
 */
object CommonUtil {

    fun showLoadingDialog(baseActivity: MvvmActivity<*, *>): TransparentProgressDialog {
        val display = baseActivity.getWindowManager().getDefaultDisplay()
        val progressDialog = TransparentProgressDialog(baseActivity, display)
        progressDialog.let {
            it.show()
            return it
        }
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }



}