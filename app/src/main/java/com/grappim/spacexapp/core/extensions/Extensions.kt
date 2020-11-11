package com.grappim.spacexapp.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.utils.SafeClickListener
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

inline val Context.connectivityManager: ConnectivityManager?
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

fun View.hideKeyboard(): Boolean {
    return try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
        false
    }
}

inline fun <reified T : Activity> Context?.startActivity() =
    this?.startActivity(Intent(this, T::class.java))

inline fun <reified T : Any> Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivityForResult(intent, requestCode, options)
}

inline fun <reified T : Any> Context.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent, options)
}

fun Fragment.startBrowser(uriString: String?) {
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(uriString)
    activity?.startActivity(i)
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)

fun View.gone(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun View.hide(): View {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
    return this
}

fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.showOrGone(show: Boolean) {
    visibility = if (show) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

inline fun View.showIf(condition: () -> Boolean): View {
    visibility = if (visibility != View.VISIBLE && condition()) {
        View.VISIBLE
    } else {
        View.GONE
    }
    return this
}

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setSafeOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}

fun View.showSnackbar(text: String, timeLength: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, text, timeLength).show()
}

fun setMyImageResource(bool: Boolean?): Int =
    when (bool) {
        true -> R.drawable.ic_check_circle_black_24dp
        else -> R.drawable.ic_cancel_black_24dp
    }

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Float.dp: Float
    get() = (this / Resources.getSystem().displayMetrics.density)

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.px: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

fun Context.actionBarSize(): Int {
    var abr: Int = 0
    val tv = TypedValue()
    if (this.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
        abr =
            TypedValue.complexToDimensionPixelSize(tv.data, this.resources.displayMetrics)
    }
    return abr
}

fun Context.statusBarHeight(): Int {
    var result = 0
    val resourceId = this
        .resources
        .getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = this.resources.getDimensionPixelSize(resourceId)
    }
    return result
}

val Context.networkInfo: NetworkInfo?
    get() =
        (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

fun <T : Any, L : LiveData<T>> LifecycleOwner.onObserve(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<Throwable>> LifecycleOwner.onFailure(liveData: L, body: (Throwable?) -> Unit) =
    liveData.observe(this, Observer(body))

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun Double.myFormat(): String {
    val dfs = DecimalFormatSymbols(Locale.getDefault()).also {
        it.decimalSeparator = ','
        it.groupingSeparator = ' '
    }
    val df = DecimalFormat("#,###.##", dfs)
    return df.format(this)
}

fun Drawable.setMyColorFilter(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        this.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_IN)
    } else {
        this.setColorFilter(color, PorterDuff.Mode.SRC_IN)
    }
}