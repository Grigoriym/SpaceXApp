package com.grappim.spacexapp.network

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptor
import com.grappim.spacexapp.util.FieldConstants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface API {

  companion object {
    operator fun invoke(
      connectivityInterceptor: ConnectivityInterceptor
    ): API {
      val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(connectivityInterceptor)
        .build()
      return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(FieldConstants.SPACE_X_BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(API::class.java)
    }
  }

  // https://api.spacexdata.com/v3/capsules
  @GET("capsules")
  fun getCapsules(): Call<List<CapsuleModel>>

  @GET("capsules/upcoming")
  fun getUpcomingCapsules(): Call<List<CapsuleModel>>

  @GET("rockets")
  fun getAllRockets(): Call<List<RocketModel>>
}