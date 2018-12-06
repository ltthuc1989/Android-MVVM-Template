package com.ezyplanet.thousandhands.core.ui.base.adapter


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.Html
import android.text.SpannableString
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target
import com.ezyplanet.thousandhands.core.GlideApp
import com.ezyplanet.thousandhands.driver.ui.widgets.decoration.SpaceItemDecoration
import com.ezyplanet.thousandhands.driver.ui.widgets.scoll.RecyclerViewScrollCallback


class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter(value = *arrayOf("visibleThreshold", "resetLoadingState", "onScrolledToBottom"), requireAll = false)
        fun setRecyclerViewScrollCallback(recyclerView: RecyclerView, visibleThreshold: Int, resetLoadingState: Boolean,
                                          onScrolledListener: RecyclerViewScrollCallback.OnScrolledListener) {

            val callback = RecyclerViewScrollCallback
                    .Builder(recyclerView.layoutManager)
                    .visibleThreshold(visibleThreshold)
                    .onScrolledListener(onScrolledListener)
                    .resetLoadingState(resetLoadingState)
                    .build()

            recyclerView.clearOnScrollListeners()
            recyclerView.addOnScrollListener(callback)
        }

        /**
         * @param recyclerView  RecyclerView to bind to SpaceItemDecoration
         * @param spaceInPx space in pixels
         */
        @JvmStatic
        @BindingAdapter("spaceItemDecoration")
        fun setSpaceItemDecoration(recyclerView: RecyclerView, spaceInPx: Float) {
            if (spaceInPx != 0f) {
                val itemDecoration = SpaceItemDecoration(spaceInPx.toInt(), true, false)
                recyclerView.addItemDecoration(itemDecoration)
            } else {
                val itemDecoration = SpaceItemDecoration(spaceInPx.toInt(), true, false)

                recyclerView.addItemDecoration(itemDecoration)
            }
        }

        /**
         * @param recyclerView  RecyclerView to bind to DividerItemDecoration
         * @param orientation 0 for LinearLayout.HORIZONTAL and 1 for LinearLayout.VERTICAL
         */
        @JvmStatic
        @BindingAdapter("dividerItemDecoration")
        fun setDividerItemDecoration(recyclerView: RecyclerView, orientation: Int) {
            val itemDecoration = DividerItemDecoration(recyclerView.context, orientation)
            recyclerView.addItemDecoration(itemDecoration)
        }

        @JvmStatic
        @BindingAdapter("requestFocusListener")
        fun requestFocus(editText: EditText, shouldFocus: Boolean) {
            if (shouldFocus) {
                editText.postDelayed({ editText.requestFocus() }, 100)
            } else {
                editText.postDelayed({ editText.clearFocus() }, 100)
            }
        }

        @JvmStatic
        @BindingAdapter("bindValue")
        fun bindEditTextValue(editText: EditText, field: MutableLiveData<String>) {
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    field.value = s.toString()
                }
            })
        }

        /*@JvmStatic
        @BindingAdapter("changeSearchViewFocusListener")
        fun changeSearchViewFocus(editText: EditText, searchIcon: AppCompatImageView) {
            editText.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    searchIcon.setImageResource(R.drawable.vector_search_black_87)
                } else {
                    searchIcon.setImageResource(R.drawable.vector_search_black_54)
                }
            }
        }*/

        @JvmStatic
        @BindingAdapter("srcImageUrl")
        fun setImageSrc(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                GlideApp.with(imageView.context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView)
            }
        }

        @JvmStatic
        @BindingAdapter("matchSrcImageUrl")
        fun setMatchImageSrc(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty())
                GlideApp.with(imageView.context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(imageView)
        }

        @JvmStatic
        @BindingAdapter("srcImageBitmap")
        fun setImageBitmap(imageView: ImageView, bitmap: Bitmap?) {
            bitmap?.let {
                GlideApp.with(imageView.context).load(it)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
            }
        }

        @JvmStatic
        @BindingAdapter("srcImageDrawable")
        fun setImageDrawable(imageView: ImageView, drawable: Drawable?) {
            drawable?.let {
                GlideApp.with(imageView.context).load(it)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
            }
        }

        @JvmStatic
        @BindingAdapter("app:srcCompat")
        fun setSrcCompatResId(imageView: AppCompatImageView, @DrawableRes drawableResId: Int) {
            imageView.setImageResource(drawableResId)
        }

        @JvmStatic
        @BindingAdapter("app:srcCompat")
        fun setSrcCompatDrawable(imageView: AppCompatImageView, drawable: Drawable) {
            imageView.setImageDrawable(drawable)
        }

        @JvmStatic
        @BindingAdapter("android:background")
        fun setBackgroundResId(view: View, @DrawableRes drawableResId: Int) {
            view.setBackgroundResource(drawableResId)
        }
        @JvmStatic
        @BindingAdapter("webViewClient")
        fun setWebViewClient(view: WebView, client: WebViewClient) {

            val settings =view.getSettings()
            settings.setLoadWithOverviewMode(true)
            settings.setJavaScriptEnabled(true)
            settings.setDomStorageEnabled(true)
            settings.setDatabaseEnabled(true)
            settings.setUseWideViewPort(true)
            if (Build.VERSION.SDK_INT >= 21) {
                settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW)
            }
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                settings.setDatabasePath("/data/data/" + view.getContext().getPackageName() + "/databases/")
            }
            view.webViewClient = client
        }
        @JvmStatic
        @BindingAdapter("webViewChromeClient")
        fun setWebViewChromeClient(view: WebView, client: WebChromeClient) {

            val settings =view.getSettings()
            settings.setLoadWithOverviewMode(true)
            settings.setJavaScriptEnabled(true)
            settings.setDomStorageEnabled(true)
            settings.setDatabaseEnabled(true)
            settings.setUseWideViewPort(true)
            if (Build.VERSION.SDK_INT >= 21) {
                settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW)
            }
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                settings.setDatabasePath("/data/data/" + view.getContext().getPackageName() + "/databases/")
            }
            view.webChromeClient = client
        }
        @JvmStatic
        @BindingAdapter("loadUrl")
        fun loadUrl(view: WebView, url: String?) {
            view.loadUrl(url)
        }
        //Textview
//        @JvmStatic
//        @BindingAdapter("htmlText")
//        fun setHtmlText(textView: TextView, resId :Int) {
//            textView.setText(Html.fromHtml(textView.context.getString(resId)))
//
//        }
//        @JvmStatic
//        @BindingAdapter(value = *arrayOf("textContentClickable", "textContentClick","textBold"), requireAll = false)
//        fun onContentClick(textView: TextViewPlus, content:Array<String?>, clickListener: View.OnClickListener,isBold:Boolean=false) {
//            val changeString = content[1] ?: ""
//            val totalString = content[0]+ changeString
//            val spanText = SpannableString(totalString)
//            val mySpannable = TextSpanable(textView.context,totalString, clickListener, textView.context.getResources().getColor(R.color.white), isBold)
//            spanText.setSpan(mySpannable,
//                    content[0]!!.length,
//                    totalString.length, 0)
//
//            textView.text = spanText
//            textView.movementMethod = LinkMovementMethod.getInstance()
//        }
    }
}