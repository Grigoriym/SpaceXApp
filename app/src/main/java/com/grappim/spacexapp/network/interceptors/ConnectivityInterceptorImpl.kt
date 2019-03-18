package com.grappim.spacexapp.network.interceptors

import android.content.Context
import android.net.ConnectivityManager
import com.grappim.spacexapp.util.NoConnectivityException
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
    val connectivityManager = appContext
      ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
  }
}