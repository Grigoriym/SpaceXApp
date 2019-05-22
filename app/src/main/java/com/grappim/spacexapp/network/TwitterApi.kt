package com.grappim.spacexapp.network

import com.grappim.spacexapp.BuildConfig
import com.grappim.spacexapp.model.twitter.UserTimelineModel
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptor
import com.grappim.spacexapp.util.TWITTER_API_BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface TwitterApi {

  companion object {
    operator fun invoke(
      connectivityInterceptor: ConnectivityInterceptor
    ): TwitterApi {
      val logging = HttpLoggingInterceptor()
      logging.level = HttpLoggingInterceptor.Level.BODY

      val okHttpClient = OkHttpClient.Builder()
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

  @Headers(
    "Authorization: OAuth oauth_consumer_key=\"${BuildConfig.ApiKey}\"," +
        "oauth_token=\"${BuildConfig.AccessToken}\"," +
        "oauth_signature_method=\"HMAC-SHA1\"," +
        "oauth_timestamp=\"1558438998\"," +
        "oauth_nonce=\"ZXaaq3H1s9q\"," +
        "oauth_version=\"1.0\"," +
        "oauth_signature=\"F6DyBFF1LhYzi9Ty%2BICeRUnuHKk%3D\""
  )
  @GET("statuses/user_timeline.json")
  fun getUserTimeline(
    @Query("user_id") userId: String? = null,
    @Query("screen_name") screenName: String? = "SpaceX"
  ): Deferred<Response<List<UserTimelineModel>>>
}