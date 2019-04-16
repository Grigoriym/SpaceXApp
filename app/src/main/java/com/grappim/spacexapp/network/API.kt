package com.grappim.spacexapp.network

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.cores.CoreModel
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
        .addInterceptor(connectivityInterceptor)
        .addInterceptor(logging)
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
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

  //CAPSULES
  // https://api.spacexdata.com/v3/capsules
  @GET("capsules")
  fun getCapsules(): Deferred<Response<List<CapsuleModel>>>

  //https://api.spacexdata.com/v3/capsules/upcoming
  @GET("capsules/upcoming")
  fun getUpcomingCapsules(): Deferred<Response<List<CapsuleModel>>>

  //https://api.spacexdata.com/v3/capsules/past
  @GET("capsules/past")
  fun getPastCapsules(): Deferred<Response<List<CapsuleModel>>>

  //https://api.spacexdata.com/v3/capsules/{{capsule_serial}}
  @GET("capsules/{capsuleSerial}")
  fun getOneCapsuleBySerial(
    @Path("capsuleSerial") capsuleSerial: String?
  ): Deferred<Response<CapsuleModel>>

  //ROCKETS
  //https://api.spacexdata.com/v3/rockets
  @GET("rockets")
  fun getAllRockets(): Deferred<Response<List<RocketModel>>>

  //MISSIONS
  //  https://api.spacexdata.com/v3/missions
  @GET("missions")
  fun getAllMissions(): Deferred<Response<List<MissionModel>>>

  //https://api.spacexdata.com/v3/missions/{{mission_id}}
  @GET("missions/{missionId}")
  fun getOneMissionById(
    @Path("missionId") missionId: String?
  ): Deferred<Response<MissionModel>>

  //CORES
  //  https://api.spacexdata.com/v3/cores
  @GET("cores")
  fun getAllCores(): Deferred<Response<List<CoreModel>>>

  //  https://api.spacexdata.com/v3/cores/upcoming
  @GET("cores/upcoming")
  fun getUpcomingCores(): Deferred<Response<List<CoreModel>>>

  //  https://api.spacexdata.com/v3/cores/past
  @GET("cores/past")
  fun getPastCores(): Deferred<Response<List<CoreModel>>>

  //  https://api.spacexdata.com/v3/cores/{{core_serial}}
  @GET("cores/{coreSerial}")
  fun getOneCoreBySerial(
    @Path("coreSerial") coreSerial: String?
  ): Deferred<Response<CoreModel>>

  //  SHIPS
  //https://api.spacexdata.com/v3/ships
  @GET("ships")
  fun getAllShips(): Deferred<Response<List<ShipModel>>>

  //https://api.spacexdata.com/v3/ships/{{ship_id}}
  @GET("ships/{shipId}")
  fun getOneShipById(
    @Path("shipId") shipId: String?
  ): Deferred<Response<ShipModel>>

  //  PAYLOADS
  //  https://api.spacexdata.com/v3/payloads
  @GET("payloads")
  fun getAllPayloads(): Deferred<Response<List<PayloadModel>>>

  //  https://api.spacexdata.com/v3/payloads/{{payload_id}}
  @GET("payloads/{payloadId}")
  fun getPayloadById(
    @Path("payloadId") payloadId: String?
  ): Deferred<Response<PayloadModel>>
}