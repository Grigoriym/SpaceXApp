package com.grappim.spacexapp.core.extensions

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.grappim.spacexapp.R
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)

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

fun CombinedLoadStates.getErrorState(): LoadState.Error? =
    this.source.append as? LoadState.Error
        ?: this.source.prepend as? LoadState.Error
        ?: this.append as? LoadState.Error
        ?: this.prepend as? LoadState.Error
        ?: this.refresh as? LoadState.Error