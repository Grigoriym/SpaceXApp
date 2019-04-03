package com.grappim.spacexapp.network.interceptors

import android.content.Context
import com.grappim.spacexapp.util.NoConnectivityException
import com.grappim.spacexapp.util.connectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class ConnectivityInterceptorImpl(
  context: Context?
) : ConnectivityInterceptor {

  private val appContext = context?.applicationContext

  override fun intercept(chain: Interceptor.Chain): Response {
    if (!isOnline()) {
      Timber.d("!isOnline")
      throw NoConnectivityException()
    }
    return chain.proceed(chain.request())
  }

  private fun isOnline(): Boolean {
    val networkInfo = appContext?.connectivityManager?.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
  }
}