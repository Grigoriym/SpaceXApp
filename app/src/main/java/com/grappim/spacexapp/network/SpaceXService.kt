package com.grappim.spacexapp.network

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.util.SPACE_X_BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SpacexService(retrofit: Retrofit) : SpacexApi {

  private val spacexApi by lazy { retrofit.create(SpacexApi::class.java) }

  override suspend fun getAllCapsules(): Response<List<CapsuleModel>> =
    spacexApi.getAllCapsules()

  override suspend fun getUpcomingCapsules(): Response<List<CapsuleModel>> =
    spacexApi.getUpcomingCapsules()

  override suspend fun getPastCapsules(): Response<List<CapsuleModel>> =
    spacexApi.getPastCapsules()

  override suspend fun getOneCapsuleBySerial(capsuleSerial: String?): Response<CapsuleModel> =
    spacexApi.getOneCapsuleBySerial(capsuleSerial)

  override suspend fun getAllRockets(): Response<List<RocketModel>> =
    spacexApi.getAllRockets()

  override suspend fun getAllCores(): Response<List<CoreModel>> =
    spacexApi.getAllCores()

  override suspend fun getUpcomingCores(): Response<List<CoreModel>> =
    spacexApi.getUpcomingCores()

  override suspend fun getPastCores(): Response<List<CoreModel>> =
    spacexApi.getPastCores()

  override suspend fun getOneCoreBySerial(coreSerial: String?): Response<CoreModel> =
    spacexApi.getOneCoreBySerial(coreSerial)

  override suspend fun getAllShips(): Response<List<ShipModel>> =
    spacexApi.getAllShips()

  override suspend fun getOneShipById(shipId: String?): Response<ShipModel> =
    spacexApi.getOneShipById(shipId)

  override suspend fun getAllLaunchPads(): Response<List<LaunchPadModel>> =
    spacexApi.getAllLaunchPads()

  override suspend fun getLaunchPadBySiteId(siteId: String?): Response<LaunchPadModel> =
    spacexApi.getLaunchPadBySiteId(siteId)
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