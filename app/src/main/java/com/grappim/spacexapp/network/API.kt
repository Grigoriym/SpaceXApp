package com.grappim.spacexapp.network

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.model.mission.MissionModel
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptor
import com.grappim.spacexapp.util.SPACE_X_BASE_URL
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
      logging.level = HttpLoggingInterceptor.Level.BODY

      val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor(connectivityInterceptor)
        .addInterceptor(logging)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()

      return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(SPACE_X_BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(API::class.java)
    }
  }

  @GET("capsules")
  fun getAllCapsules(): Deferred<Response<List<CapsuleModel>>>

  @GET("capsules/upcoming")
  fun getUpcomingCapsules(): Deferred<Response<List<CapsuleModel>>>

  @GET("capsules/past")
  fun getPastCapsules(): Deferred<Response<List<CapsuleModel>>>

  @GET("capsules/{capsuleSerial}")
  fun getOneCapsuleBySerial(
    @Path("capsuleSerial") capsuleSerial: String?
  ): Deferred<Response<CapsuleModel>>

  @GET("rockets")
  fun getAllRockets(): Deferred<Response<List<RocketModel>>>

  @GET("cores")
  fun getAllCores(): Deferred<Response<List<CoreModel>>>

  @GET("cores/upcoming")
  fun getUpcomingCores(): Deferred<Response<List<CoreModel>>>

  @GET("cores/past")
  fun getPastCores(): Deferred<Response<List<CoreModel>>>

  @GET("cores/{coreSerial}")
  fun getOneCoreBySerial(
    @Path("coreSerial") coreSerial: String?
  ): Deferred<Response<CoreModel>>

//  ********************************

  @GET("missions")
  fun getAllMissions(): Deferred<Response<List<MissionModel>>>

  @GET("missions/{missionId}")
  fun getOneMissionById(
    @Path("missionId") missionId: String?
  ): Deferred<Response<MissionModel>>

  @GET("ships")
  fun getAllShips(): Deferred<Response<List<ShipModel>>>

  @GET("ships/{shipId}")
  fun getOneShipById(
    @Path("shipId") shipId: String?
  ): Deferred<Response<ShipModel>>

  @GET("payloads")
  fun getAllPayloads(): Deferred<Response<List<PayloadModel>>>

  @GET("payloads/{payloadId}")
  fun getPayloadById(
    @Path("payloadId") payloadId: String?
  ): Deferred<Response<PayloadModel>>

  @GET("info")
  fun getInfo(): Deferred<Response<InfoModel>>

  @GET("history")
  fun getHistory(): Deferred<Response<List<HistoryModel>>>

  @GET("launchpads")
  fun getAllLaunchPads(): Deferred<Response<List<LaunchPadModel>>>

  @GET("launchpads/{site_id}")
  fun getLaunchPadBySiteId(
    @Path("site_id") siteId: String?
  ): Deferred<Response<LaunchPadModel>>
}