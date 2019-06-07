package com.grappim.spacexapp.network.services

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.model.info.InfoModel
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.model.mission.MissionModel
import com.grappim.spacexapp.model.payloads.PayloadModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.network.api.SpaceXApi
import com.grappim.spacexapp.util.SPACE_X_BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SpaceXService(retrofit: Retrofit) : SpaceXApi {

  private val spaceXApi by lazy { retrofit.create(SpaceXApi::class.java) }

  override suspend fun getAllCapsules(): Response<List<CapsuleModel>> =
    spaceXApi.getAllCapsules()

  override suspend fun getUpcomingCapsules(): Response<List<CapsuleModel>> =
    spaceXApi.getUpcomingCapsules()

  override suspend fun getPastCapsules(): Response<List<CapsuleModel>> =
    spaceXApi.getPastCapsules()

  override suspend fun getOneCapsuleBySerial(capsuleSerial: String?): Response<CapsuleModel> =
    spaceXApi.getOneCapsuleBySerial(capsuleSerial)

  override suspend fun getAllRockets(): Response<List<RocketModel>> =
    spaceXApi.getAllRockets()

  override suspend fun getAllCores(): Response<List<CoreModel>> =
    spaceXApi.getAllCores()

  override suspend fun getUpcomingCores(): Response<List<CoreModel>> =
    spaceXApi.getUpcomingCores()

  override suspend fun getPastCores(): Response<List<CoreModel>> =
    spaceXApi.getPastCores()

  override suspend fun getOneCoreBySerial(coreSerial: String?): Response<CoreModel> =
    spaceXApi.getOneCoreBySerial(coreSerial)

  override suspend fun getAllShips(): Response<List<ShipModel>> =
    spaceXApi.getAllShips()

  override suspend fun getOneShipById(shipId: String?): Response<ShipModel> =
    spaceXApi.getOneShipById(shipId)

  override suspend fun getAllLaunchPads(): Response<List<LaunchPadModel>> =
    spaceXApi.getAllLaunchPads()

  override suspend fun getLaunchPadBySiteId(siteId: String?): Response<LaunchPadModel> =
    spaceXApi.getLaunchPadBySiteId(siteId)

  override suspend fun getInfo(): Response<InfoModel> =
    spaceXApi.getInfo()

  override suspend fun getHistory(): Response<List<HistoryModel>> =
    spaceXApi.getHistory()

  override suspend fun getAllPayloads(): Response<List<PayloadModel>> =
    spaceXApi.getAllPayloads()

  override suspend fun getPayloadById(payloadId: String?): Response<PayloadModel> =
    spaceXApi.getPayloadById(payloadId)

  override suspend fun getAllMissions(): Response<List<MissionModel>> =
    spaceXApi.getAllMissions()

  override suspend fun getOneMissionById(missionId: String?): Response<MissionModel> =
    spaceXApi.getOneMissionById(missionId)
}

fun createRetrofit(): Retrofit = Retrofit.Builder()
  .baseUrl(SPACE_X_BASE_URL)
  .client(createOkHttpClient())
  .addCallAdapterFactory(CoroutineCallAdapterFactory())
  .addConverterFactory(GsonConverterFactory.create())
  .build()

fun createOkHttpClient(): OkHttpClient {
  val logging = HttpLoggingInterceptor()
  logging.level = HttpLoggingInterceptor.Level.BASIC

  return OkHttpClient.Builder()
    .addInterceptor(logging)
    .connectTimeout(20, TimeUnit.SECONDS)
    .readTimeout(20, TimeUnit.SECONDS)
    .writeTimeout(20, TimeUnit.SECONDS)
    .build()
}