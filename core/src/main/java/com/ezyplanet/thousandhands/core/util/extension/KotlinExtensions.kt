package com.ezyplanet.thousandhands.core.util.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.ezyplanet.thousandhands.core.R
import com.ezyplanet.thousandhands.util.livedata.NonNullLiveData
import com.google.gson.Gson
import com.google.gson.JsonElement
import io.reactivex.Flowable
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.reflect.KClass

/**
 * Observe [LiveData] on an instance of [LifecycleOwner] like [Fragment] or [Activity]
 * @param l instance of [L]
 * @param observer initView lambda function that receives initView nullable [T] and will be invoked when data is available
 */
fun <T, L : LiveData<T>> LifecycleOwner.observe(l: L, observer: (T?) -> Unit) {
    l.observe(this, Observer {
        observer(it)
    })
}

/**
 * Observe [NonNullLiveData] on an instance of [LifecycleOwner] like [Fragment] or [Activity]
 *
 * @param l instance of [NonNullLiveData]
 * @param observer initView lambda function that receives initView non-null [T] and will be invoked when data is available
 */
fun <T> LifecycleOwner.observe(l: NonNullLiveData<T>, observer: (T) -> Unit) {
    l.observe(this, { observer(it) })
}


inline fun <reified T> Gson.fromJson(jsonElement: JsonElement): T? = this.fromJson<T>(jsonElement, object : com.google.gson.reflect.TypeToken<T>() {}.type)

inline fun <reified T> Gson.fromJson(jsonStr: String): T? = this.fromJson<T>(jsonStr, object : com.google.gson.reflect.TypeToken<T>() {}.type)


/**
 * Add all given values to fragment bundle arguments
 * @param params list of [Pair]s that include key, value for each argument
 */
fun <T : Fragment> T.setArguments(vararg params: Pair<String, Any?>): T {
    val args = Bundle()
    if (params.isNotEmpty()) args.fill(*params)
    arguments = args
    return this
}

/**
 * get [Serializable] from [Bundle] without need to class casting using kotlin 'reified' feature
 */
inline fun <reified T> Bundle.getGenericSerializable(key: String) = getSerializable(key) as T

/**
 * put values into bundle based on their types.
 *
 * @param params list of [Pair]s that include key, value for each argument
 */
fun Bundle.fill(vararg params: Pair<String, Any?>) = apply {
    params.forEach {
        val value = it.second
        when (value) {
            null -> putSerializable(it.first, null as Serializable?)
            is Int -> putInt(it.first, value)
            is Long -> putLong(it.first, value)
            is CharSequence -> putCharSequence(it.first, value)
            is String -> putString(it.first, value)
            is Float -> putFloat(it.first, value)
            is Double -> putDouble(it.first, value)
            is Char -> putChar(it.first, value)
            is Short -> putShort(it.first, value)
            is Boolean -> putBoolean(it.first, value)
            is Serializable -> putSerializable(it.first, value)
            is Bundle -> putBundle(it.first, value)
            is Parcelable -> putParcelable(it.first, value)
            is LongArray -> putLongArray(it.first, value)
            is FloatArray -> putFloatArray(it.first, value)
            is DoubleArray -> putDoubleArray(it.first, value)
            is CharArray -> putCharArray(it.first, value)
            is ShortArray -> putShortArray(it.first, value)
            is BooleanArray -> putBooleanArray(it.first, value)
            is IntArray -> putIntArray(it.first, value)
            is Array<*> -> when {
                value.isArrayOf<CharSequence>() -> putCharSequenceArray(it.first, value as Array<out CharSequence>)
                value.isArrayOf<String>() -> putStringArray(it.first, value as Array<out String>)
                value.isArrayOf<Parcelable>() -> putParcelableArrayList(it.first, value as ArrayList<out Parcelable>)
                else -> throw Exception("Bundle extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            else -> throw Exception("Bundle extra ${it.first} has wrong type ${value.javaClass.name}")
        }
    }
}
/*

*/
/**
 * Converts [LiveData] into initView [Flowable]
 */
fun <T> LiveData<T>.toFlowable(lifecycleOwner: LifecycleOwner): Flowable<T> =
        Flowable.fromPublisher(LiveDataReactiveStreams.toPublisher(lifecycleOwner, this))

/**
 * close keyboard on [Activity.getCurrentFocus] view
 */
fun Activity.closeKeyboard() {
    val view = currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

/**
 * close keyboard on this view
 */
fun View.closeKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * @return returns formatted time in 'HH:mm:ss' format and in 'fa' locale
 */
fun Date.timeString() = SimpleDateFormat("HH:mm:ss", Locale("fa", "IR"))
        .let { it.format(this)!! }

/**
 * For Actvities, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(
        provider: ViewModelProvider.Factory
) =
        ViewModelProviders.of(this, provider).get(VM::class.java)

/**
 * For Fragments, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
        provider: ViewModelProvider.Factory
) =
        ViewModelProviders.of(this, provider).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want initView [ViewModel] scoped to the Activity.
 */
inline fun <reified VM : ViewModel> Fragment.activityViewModelProvider(
        provider: ViewModelProvider.Factory
) =
        ViewModelProviders.of(requireActivity(), provider).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want initView [ViewModel] scoped to the parent
 * Fragment.
 */
inline fun <reified VM : ViewModel> Fragment.parentViewModelProvider(
        provider: ViewModelProvider.Factory
) =
        ViewModelProviders.of(parentFragment!!, provider).get(VM::class.java)

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

/**
 * Implementation of lazy that is not thread safe. Useful when you know what thread you will be
 * executing on and are not worried about synchronization.
 */
fun <T> lazyFast(operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}

inline fun <FRAGMENT : Fragment> FRAGMENT.putArgs(argsBuilder: Bundle.() -> Unit): FRAGMENT = this.apply { arguments = Bundle().apply(argsBuilder) }
inline fun <FRAGMENT : DialogFragment> FRAGMENT.putArgs(argsBuilder: Bundle.() -> Unit): FRAGMENT = this.apply { arguments = Bundle().apply(argsBuilder) }

fun Activity.gotoActivity(cls: KClass<out Activity>, finish: Boolean = false) {
    val intent = Intent(this, cls.java)
    startActivity(intent)
    if (finish) finish()
}
fun Activity.gotoActivity(cls: KClass<out Activity>,
                          extras: Map<String, Any?>? = null,finish: Boolean=false) {
    val intent = Intent(this, cls.java)

    extras?.forEach { intent.addExtra(it.key, it.value) }
    startActivity(intent)
    if (finish) finish()
}
 fun Intent.addExtra(key: String, value: Any?) {
    when (value) {
        is Long -> putExtra(key, value)
        is String -> putExtra(key, value)
        is Boolean -> putExtra(key, value)
        is Float -> putExtra(key, value)
        is Double -> putExtra(key, value)
        is Int -> putExtra(key, value)
        is Parcelable -> putExtra(key, value)
        //Add other types when needed
    }
}
inline fun <reified T> Activity.getExtra(extra: String): T? {
    return intent.extras?.get(extra) as? T?
}
inline fun <F> AppCompatActivity.replaceFragment(fragment: F) where F : Fragment {
    supportFragmentManager.inTransaction {
        replace(R.id.fragment_container,fragment)
    }
}

