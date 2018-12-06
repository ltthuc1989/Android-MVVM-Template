package com.ezyplanet.thousandhands.core.ui.base


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ezyplanet.thousandhands.core.util.CommonUtil

import com.ezyplanet.thousandhands.core.ui.listener.RetryCallback
import com.ezyplanet.thousandhands.core.ui.widget.TransparentProgressDialog
import com.ezyplanet.thousandhands.core.util.AlertUtils
import com.ezyplanet.thousandhands.core.util.DialogFactory

import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Every Activity should inherit from this base activity in order to create relevant binding class,
 * inject dependencies and handling default actions.
 * @param V A ViewModel class that inherited from [BaseViewModel], will be used as default ViewModel of activity
 * @param B A Binding class that inherited from [ViewDataBinding], will be used for creating View of this activity
 */
abstract class MvvmActivity<B : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(), MvvmView<V, B>, HasSupportFragmentInjector, MvvmFragment.Callback {
    override lateinit var binding: B

    private val PLAY_SERVICES_RESOLUTION_REQUEST = 9000
    private val REQUEST_CROP_GALLERY = 301
    private val REQUEST_CROP_CAPTURE = 302
    private val REQUEST_TAKE_VIDEO = 303
    val REQUEST_PASSWORD_LOGIN_CODE = 304
    protected val EXPIRED_TOKEN = "expired_token"
    val REQUEST_CAMERA_PERMISSION = 101
    internal var hasShowingDlg: Boolean = false
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory
    private var progressDialog: TransparentProgressDialog? = null


    /**
     * Attempt to get viewModel lazily from [viewModelFactory] with the scope of given activity.
     *
     * @param activity given scope.
     * @return T an instance of requested ViewModel.
     */
    inline fun <reified T : BaseViewModel<*>> getLazyViewModel(): Lazy<T> =
            lazy { ViewModelProviders.of(this, viewModelFactory)[T::class.java] }


    override fun onCreate(savedInstanceState: Bundle?) {
        // we should inject dependencies before invoking super.onCreate()
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        // initialize binding
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.setLifecycleOwner(this)


        // set viewModel as an observer to this activity lifecycle events
        lifecycle.addObserver(viewModel)
        //todo:  viewModel.checkConnection()
        // observe viewModel uiActions in order to pass this activity as argument of uiAction
        viewModel.activityAction.observe(this, Observer { it?.invoke(this) })

        onViewInitialized(binding)
    }


    fun hideProgress() {
        progressDialog?.let { if (it.isShowing) it.cancel() }
    }

    fun showProgress() {
        progressDialog?.let { if (it.isShowing) return }
        progressDialog = CommonUtil.showLoadingDialog(this)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector
    override fun onFragmentAttached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentDetached(tag: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



    fun connectionFail(restId: Int, retryCallback: RetryCallback<*>) {
        runOnUiThread {
            if (hasShowingDlg == false) {
                retryCallback.onSendEvent()
                hasShowingDlg = true
                DialogFactory.showCancelRetryAlertDlg(this@MvvmActivity, getString(restId), retryCallback, DialogInterface.OnClickListener { dialogInterface, i ->
                    hasShowingDlg = false
                    dialogInterface.dismiss()


                    retryCallback.onRetry()
                }, DialogInterface.OnClickListener { dialogInterface, i ->
                    hasShowingDlg = false
                    dialogInterface.dismiss()

                })
            }
        }
    }

    fun connectionFail(restId: Int) {
        showAlert(restId)
    }

    fun openActivityOnTokenExpire() {
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
    }

    fun showErrorSnackBar(@StringRes resId: Int) {
        showErrorSnackBar(getString(resId))
    }


    fun showErrorSnackBar(message: String?) {
        if (message != null) {
            // showSnackBar(message)

        } else {
            // showSnackBar(getString(R.string.some_error));

        }
    }

    fun showAlert(message: String?) {
        if (hasShowingDlg == false) {
            if (message != null) {
                hasShowingDlg = true
                AlertUtils.showOkAlertDialog(this, message, DialogInterface.OnDismissListener { hasShowingDlg = false })
            } else {
                AlertUtils.showOkAlertDialog(this, message)
            }
        }
    }

    fun showAlert(@StringRes resId: Int) {
        showAlert(getString(resId))
    }



}

