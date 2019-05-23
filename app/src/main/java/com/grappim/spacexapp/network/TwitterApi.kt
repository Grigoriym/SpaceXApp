package com.grappim.spacexapp.network

import com.grappim.spacexapp.BuildConfig
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptor
import com.grappim.spacexapp.network.interceptors.Oauth1SigningInterceptor
import com.grappim.spacexapp.network.interceptors.OauthKeys
import com.grappim.spacexapp.util.TWITTER_API_BASE_URL
import com.grappim.spacexapp.util.TWITTER_USER_TIMELINE_GET
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
import java.util.concurrent.TimeUnit

interface TwitterApi {

  companion object {
    operator fun invoke(
      connectivityInterceptor: ConnectivityInterceptor
    ): TwitterApi {
      val logging = HttpLoggingInterceptor()
      logging.level = HttpLoggingInterceptor.Level.BODY

      fun getOauthKeys() = OauthKeys(
        consumerKey = BuildConfig.ApiKey,
        consumerSecret = BuildConfig.SecretApiKey,
        accessToken = BuildConfig.AccessToken,
        accessSecret = BuildConfig.AccessTokenSecret
      )

      val twitterOauthInterceptor = Oauth1SigningInterceptor(
        ::getOauthKeys
      )

      val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(twitterOauthInterceptor)
        .addInterceptor(connectivityInterceptor)
        .addInterceptor(logging)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()

      return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(TWITTER_API_BASE_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TwitterApi::class.java)
    }
  }

  @GET(TWITTER_USER_TIMELINE_GET)
  fun getUserTimelineAsync(
    @Query("user_id") userId: String? = null,
    @Query("screen_name") screenName: String? = "SpaceX",
    @Query("count") count: Int? = 50,
    @Query("include_entities") includeEntities: Boolean? = true
  ): Deferred<Response<List<UserTimelineModel>>>
}