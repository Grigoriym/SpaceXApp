package com.grappim.spacexapp.network

import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.util.SPACE_X_BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

class SpacexService(retrofit: Retrofit) : NewApi {

  private val spacexApi by lazy { retrofit.create(NewApi::class.java) }

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

interface NewApi {

  @GET("rockets")
  suspend fun getAllRockets(): Response<List<RocketModel>>

  @GET("capsules")
  suspend fun getAllCapsules(): Response<List<CapsuleModel>>

  @GET("capsules/upcoming")
  suspend fun getUpcomingCapsules(): Response<List<CapsuleModel>>

  @GET("capsules/past")
  suspend fun getPastCapsules(): Response<List<CapsuleModel>>

  @GET("capsules/{capsuleSerial}")
  suspend fun getOneCapsuleBySerial(
    @Path("capsuleSerial") capsuleSerial: String?
  ): Response<CapsuleModel>

}