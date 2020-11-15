package com.grappim.spacexapp.core.extensions

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.grappim.spacexapp.R
import com.grappim.spacexapp.SpaceXApplication
import com.grappim.spacexapp.core.exception.NetworkException
import com.grappim.spacexapp.di.components.FragmentsComponent

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

inline fun <reified T : Any> Context.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent, options)
}

fun Context.getFragmentsComponent(): FragmentsComponent =
    (this.applicationContext as SpaceXApplication)
        .appComponent
        .fragmentsComponent()
        .create()
