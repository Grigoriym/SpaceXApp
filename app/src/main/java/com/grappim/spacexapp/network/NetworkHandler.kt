package com.grappim.spacexapp.network

import android.content.Context
import com.grappim.spacexapp.util.networkInfo
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkHandler(private val context: Context) {
  val isConnected get() = context.networkInfo?.isConnectedOrConnecting
}

fun createRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
  .baseUrl(baseUrl)
  .client(createOkHttpClient())
  .addCallAdapterFactory(CoroutineCallAdapterFactory())
  .addConverterFactory(GsonConverterFactory.create())
  .build()

fun createOkHttpClient(): OkHttpClient {
  val logging = HttpLoggingInterceptor()
  logging.level = HttpLoggingInterceptor.Level.BODY

  return OkHttpClient.Builder()
    .addInterceptor(logging)
    .connectTimeout(20, TimeUnit.SECONDS)
    .readTimeout(20, TimeUnit.SECONDS)
    .writeTimeout(20, TimeUnit.SECONDS)
    .build()
}