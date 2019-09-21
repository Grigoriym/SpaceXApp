package com.grappim.spacexapp.network

import android.content.Context
import com.grappim.spacexapp.BuildConfig
import com.grappim.spacexapp.network.interceptors.Oauth1SigningInterceptor
import com.grappim.spacexapp.network.interceptors.OauthKeys
import com.grappim.spacexapp.util.networkInfo
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

class NetworkHandler(private val context: Context) {
  val isConnected get() = context.networkInfo?.isConnectedOrConnecting
}

fun createRetrofit(baseUrl: String, client: OkHttpClient): Retrofit = Retrofit.Builder()
  .baseUrl(baseUrl)
  .client(client)
  .addConverterFactory(GsonConverterFactory.create())
  .build()

fun createOkHttpClient(vararg interceptors: Interceptor): OkHttpClient {
  val okHttp = OkHttpClient.Builder()

  val logging = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
      message -> Timber.tag("API").d(message)
  })
  logging.level = HttpLoggingInterceptor.Level.BODY

  okHttp.addInterceptor(logging)

  for (i in interceptors){
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