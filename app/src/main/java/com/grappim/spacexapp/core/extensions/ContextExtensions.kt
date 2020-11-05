package com.grappim.spacexapp.core.extensions

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.exception.NetworkException

fun Context.getConnectivityManager(): ConnectivityManager? =
    ContextCompat.getSystemService(this, ConnectivityManager::class.java)

fun Context.getErrorMessage(throwable: Throwable): String =
    if (throwable is NetworkException) {
        when (throwable.errorCode) {
            NetworkException.ERROR_NO_INTERNET -> getString(R.string.error_no_internet)
            else -> getString(R.string.error_something_gone_wrong)
        }
    } else {
        throwable.message.toString()
    }