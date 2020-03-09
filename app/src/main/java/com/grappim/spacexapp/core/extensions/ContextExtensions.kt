package com.grappim.spacexapp.core.extensions

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat

fun Context.getConnectivityManager(): ConnectivityManager? =
  ContextCompat.getSystemService(this, ConnectivityManager::class.java)