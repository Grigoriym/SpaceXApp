package com.grappim.spacexapp.network

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.mission.MissionModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptor
import com.grappim.spacexapp.util.FieldConstants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface API {

  companion object {
    operator fun invoke(
      connectivityInterceptor: ConnectivityInterceptor
    ): API {
      val logging = HttpLoggingInterceptor()
      logging.level = HttpLoggingInterceptor.Level.BASIC

      val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(connectivityInterceptor)
        .addInterceptor(logging)
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
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
  fun getCapsules(): Deferred<Response<List<CapsuleModel>>>

  //https://api.spacexdata.com/v3/capsules/upcoming
  @GET("capsules/upcoming")
  fun getUpcomingCapsules(): Deferred<Response<List<CapsuleModel>>>

  //https://api.spacexdata.com/v3/capsules/past
  @GET("capsules/past")
  fun getPastCapsules(): Deferred<Response<List<CapsuleModel>>>

  //https://api.spacexdata.com/v3/rockets
  @GET("rockets")
  fun getAllRockets(): Deferred<Response<List<RocketModel>>>

  //  https://api.spacexdata.com/v3/missions
  @GET("missions")
  fun getAllMissions(): Deferred<Response<List<MissionModel>>>

  //https://api.spacexdata.com/v3/missions/{{mission_id}}
  @GET("missions/{missionId}")
  fun getOneMissionById(
    @Path("missionId") missionId: String?
  ): Deferred<Response<MissionModel>>
}