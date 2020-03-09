package com.grappim.spacexapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.grappim.spacexapp.BuildConfig
import com.grappim.spacexapp.core.extensions.getConnectivityManager
import com.grappim.spacexapp.core.extensions.networkInfo
import com.grappim.spacexapp.network.interceptors.Oauth1SigningInterceptor
import com.grappim.spacexapp.network.interceptors.OauthKeys
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

class NetworkHandlerOld(private val context: Context) {
  val isConnected get() = context.networkInfo?.isConnectedOrConnecting
}

@Singleton
@Suppress("DEPRECATION")
class NetworkHandler @Inject constructor(private val context: Context) {
  val isConnected: Boolean
    get() {
      var result = false
      val connectivityManager = context.getConnectivityManager()
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager?.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
          actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
          actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
          actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
          else -> false
        }
      } else {
        connectivityManager.run {
          connectivityManager?.activeNetworkInfo?.run {
            result = when (type) {
              ConnectivityManager.TYPE_WIFI -> true
              ConnectivityManager.TYPE_MOBILE -> true
              ConnectivityManager.TYPE_ETHERNET -> true
              else -> false
            }
          }
        }
      }
      return result
    }
}

fun createRetrofit(baseUrl: String, client: OkHttpClient): Retrofit = Retrofit.Builder()
  .baseUrl(baseUrl)
  .client(client)
  .addConverterFactory(GsonConverterFactory.create())
  .build()

fun createOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {
  val okHttp = OkHttpClient.Builder()

  if (BuildConfig.DEBUG) {
    val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
      override fun log(message: String) {
        Timber.tag("API").d(message)
      }
    }).apply {
      level = HttpLoggingInterceptor.Level.BODY
    }

    okHttp.addInterceptor(logging)
  }

  for (i in interceptors) {
    okHttp.addInterceptor(i)
  }

  return okHttp
    .connectTimeout(20, TimeUnit.SECONDS)
    .readTimeout(20, TimeUnit.SECONDS)
    .writeTimeout(20, TimeUnit.SECONDS)
    .build()
}

fun createTwitterOauthInterceptor(): Interceptor {
  return Oauth1SigningInterceptor(
    ::getOauthKeys
  )
}

fun getOauthKeys() = OauthKeys(
  consumerKey = BuildConfig.ApiKey,
  consumerSecret = BuildConfig.SecretApiKey,
  accessToken = BuildConfig.AccessToken,
  accessSecret = BuildConfig.AccessTokenSecret
)