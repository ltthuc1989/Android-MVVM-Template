package com.ezyplanet.thousandhands.core.util

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.ezyplanet.thousandhands.core.R
import com.ezyplanet.thousandhands.core.ui.listener.RetryCallback


import timber.log.Timber
import java.net.SocketTimeoutException

class DialogFactory {


    companion object {
        internal var isShowing: Boolean = false
        fun createRetryErrorDialog(context: Context, message: String, onClickListener: DialogInterface.OnClickListener, t: Throwable): Dialog {

            val alertDialog = AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.dialog_error_title))
                    .setMessage(if (t == null || t is SocketTimeoutException) message else t.message)
                    .setNeutralButton(R.string.retry) { dialogInterface, i ->
                        dialogInterface.dismiss()
                        onClickListener.onClick(dialogInterface, i)
                    }
            Timber.d("ThousandAPI_Error=" + t.message)
            return alertDialog.create()
        }

        fun createGenericErrorDialog(context: Context, message: String): Dialog {
            val alertDialog = AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.dialog_error_title))
                    .setMessage(message)
                    .setNeutralButton(R.string.dialog_action_ok, null)
            return alertDialog.create()
        }

        fun createGenericErrorDialog(context: Context, message: String, retryCallback: DialogInterface.OnClickListener, cancelCanback: DialogInterface.OnClickListener): Dialog {
            val alertDialog = AlertDialog.Builder(context)
                    .setTitle(context.getString(R.string.dialog_error_title))
                    .setMessage(message)
                    .setPositiveButton(context.getString(R.string.retry), retryCallback)
                    .setNegativeButton(context.getString(R.string.cancel), cancelCanback)


            return alertDialog.create()
        }

        fun createGenericErrorDialog(context: Context, @StringRes messageResource: Int): Dialog {
            return createGenericErrorDialog(context, context.getString(messageResource))
        }

        fun createProgressDialog(context: Context, message: String): ProgressDialog {
            val progressDialog = ProgressDialog(context)
            progressDialog.setMessage(message)
            return progressDialog
        }

        fun createProgressDialog(context: Context,
                                 @StringRes messageResource: Int): ProgressDialog {
            return createProgressDialog(context, context.getString(messageResource))
        }

        fun showDialogUpdate(context: Context) {
            if (isShowing)
                return
            isShowing = true
            try {
//            val updateAvailableDialog = UpdateAvailableDialog(context)
//            updateAvailableDialog.show()
//            updateAvailableDialog.setDoNotShowUpdateDialogAgain(object : DoNotShowUpdateDialogAgain() {
//                fun onContinue() {
//                    isShowing = false
//                }
//            })
            } catch (e: Exception) {
                e.printStackTrace()

            }

        }

        fun showCancelRetryAlertDlg(context: Context, message: String,
                                    callback: RetryCallback<*>, pListener: DialogInterface.OnClickListener, nListener: DialogInterface.OnClickListener) {
            val builder = android.app.AlertDialog.Builder(context)
            builder.setMessage(message)
            builder.setPositiveButton(context.getString(R.string.retry), pListener)
            builder.setNegativeButton(context.getString(R.string.cancel), nListener)
            val alertDialog = builder.create()
            alertDialog.setCanceledOnTouchOutside(false)

            alertDialog.show()
        }
    }



}