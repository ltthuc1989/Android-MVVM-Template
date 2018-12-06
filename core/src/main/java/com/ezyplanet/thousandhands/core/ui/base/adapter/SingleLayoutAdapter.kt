package com.ezyplanet.thousandhands.core.ui.base.adapter


import androidx.databinding.ViewDataBinding
import com.ezyplanet.thousandhands.core.ui.base.BaseViewModel

/**
 * Simplest implementation of [MvvmAdapter] to use as initView single layout adapter.
 */
open class SingleLayoutAdapter<T, B : ViewDataBinding>(
        private val layoutId: Int,
        items: List<T>,
        viewModel: BaseViewModel<*>? = null,
        onBind: B.(Int) -> Unit = {}
) : MvvmAdapter<T, B>(viewModel = viewModel, items = items, onBind = onBind) {

    override fun getLayoutId(position: Int): Int = layoutId

}