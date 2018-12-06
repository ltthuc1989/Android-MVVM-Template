package com.ezyplanet.thousandhands.core.ui.base

import android.app.Dialog
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.ezyplanet.thousandhands.core.R
import com.ezyplanet.thousandhands.core.ui.listener.RetryCallback
import com.ezyplanet.thousandhands.core.util.ScreenUtils.getScreenWidth


import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class MvvmDialogFrag<V : BaseViewModel<*>, B : ViewDataBinding> : DialogFragment(), MvvmView<V, B> {


    protected val TAG = this::class.java.simpleName
    override lateinit var binding: B
    private var mActivity: MvvmActivity<*, *>? = null

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    /**
     * Attempt to get viewModel lazily from [viewModelFactory] with the scope of given activity.
     *
     * @param activity given scope.
     * @return T an instance of requested ViewModel.
     */
    inline fun <reified T : BaseViewModel<*>> getLazyViewModel(scope: ViewModelScope): Lazy<T> =
            lazy {
                when (scope) {
                    ViewModelScope.ACTIVITY -> ViewModelProviders.of(requireActivity(), viewModelFactory)[T::class.java]
                    ViewModelScope.FRAGMENT -> ViewModelProviders.of(this, viewModelFactory)[T::class.java]
                }

            }

    inline fun <reified T : ViewModel> getLazyNormalViewModel(scope: ViewModelScope): Lazy<T> =
            lazy {
                when (scope) {
                    ViewModelScope.ACTIVITY -> ViewModelProviders.of(requireActivity(), viewModelFactory)[T::class.java]
                    ViewModelScope.FRAGMENT -> ViewModelProviders.of(this, viewModelFactory)[T::class.java]
                }
            }

    override fun onStart() {
        super.onStart()
        onChildStart()
    }

   open fun onChildStart() {
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!
                    .setLayout((getScreenWidth(context) * .9).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle)
        isCancelable = true

        return super.onCreateDialog(savedInstanceState)
    }


    override fun onAttach(context: Context?) {
        // we should inject fragment dependencies before invoking super.onAttach()

        AndroidSupportInjection.inject(this)

        Log.d(TAG,"OnAttatch")
        super.onAttach(context)
        if (context is MvvmActivity<*, *>) {
           mActivity=getBaseActivity()
        }



    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // initialize binding
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.setLifecycleOwner(this)

        // set viewModel as an observer to this activity lifecycle events
        lifecycle.addObserver(viewModel)


        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(TAG, "OnDetach")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "OnStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG, "OnDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "OnDestroy")
    }

    abstract fun setUpNavigator()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // observe viewModel uiActions in order to pass parent activity as argument of uiAction
        setUpNavigator()
        viewModel.activityAction.observe(this, Observer { it?.invoke(requireActivity()) })
        viewModel.fragmentAction.observe(this, Observer { it?.invoke(this) })
        onViewInitialized(binding)

        if(savedInstanceState==null){
            viewModel.setIsUiStageChange(true)

        }
    }


    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }

    fun connectionFail(restId: Int, retryCallback: RetryCallback<*>) {
        mActivity?.connectionFail(restId, retryCallback)
    }

    fun connectionFail(restId: Int) {
        mActivity?.connectionFail(restId)

    }

    fun openActivityOnTokenExpire() {
        mActivity?.openActivityOnTokenExpire()

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
        mActivity?.showAlert(message)

    }

    fun showAlert(@StringRes resId: Int) {
        mActivity?.showAlert(resId)

    }

    fun hideProgress() {
        mActivity?.hideProgress()

    }

    fun showProgress() {
        mActivity?.showProgress()

    }

    private fun getBaseActivity(): MvvmActivity<*, *>? {
        if (context == null) return null
        if (context is MvvmActivity<*, *>) return context as MvvmActivity<*, *>?
        return if (context is ContextWrapper) getBaseActivity() else null
    }




}