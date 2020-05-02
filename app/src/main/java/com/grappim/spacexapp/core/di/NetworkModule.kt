package com.grappim.spacexapp.core.di

import com.grappim.spacexapp.BuildConfig
import com.grappim.spacexapp.api.RedditApi
import com.grappim.spacexapp.api.SpaceXApi
import com.grappim.spacexapp.api.TwitterApi
import com.grappim.spacexapp.core.di.qualifiers.*
import com.grappim.spacexapp.core.network.Oauth1SigningInterceptor
import com.grappim.spacexapp.core.network.OauthKeys
import com.grappim.spacexapp.core.utils.REDDIT_BASE_URL
import com.grappim.spacexapp.core.utils.SPACE_X_BASE_URL
import com.grappim.spacexapp.core.utils.TWITTER_API_BASE_URL
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Singleton
  @RedditApiQualifier
  fun provideRedditApi(
    @RedditRetrofit retrofit: Retrofit
  ): RedditApi = retrofit.create(RedditApi::class.java)

  @Provides
  @Singleton
  @TwitterApiQualifier
  fun provideTwitterApi(
    @TwitterRetrofit retrofit: Retrofit
  ): TwitterApi = retrofit.create(TwitterApi::class.java)

  @Provides
  @Singleton
  @SpacexApiQualifier
  fun provideSpacexApi(
    @SpacexRetrofit retrofit: Retrofit
  ): SpaceXApi = retrofit.create(SpaceXApi::class.java)

  @Provides
  @Singleton
  @RedditRetrofit
  fun provideRedditRetrofit(): Retrofit =
    Retrofit.Builder()
      .baseUrl(REDDIT_BASE_URL)
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .client(createClient())
      .build()

  @Provides
  @Singleton
  @TwitterRetrofit
  fun provideTwitterRetrofit(): Retrofit =
    Retrofit.Builder()
      .baseUrl(TWITTER_API_BASE_URL)
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .client(createClient(createTwitterOauthInterceptor()))
      .build()

  @Provides
  @Singleton
  @SpacexRetrofit
  fun provideSpacexRetrofit(): Retrofit =
    Retrofit.Builder()
      .baseUrl(SPACE_X_BASE_URL)
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .client(createClient())
      .build()

  private fun createClient(vararg interceptors: Interceptor): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
      val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
          Timber.tag("API").d(message)
        }
      }).apply {
        level = HttpLoggingInterceptor.Level.BODY
      }
      okHttpClientBuilder.addInterceptor(logging)
    }
    interceptors.forEach {
      okHttpClientBuilder.addInterceptor(it)
    }
    okHttpClientBuilder
      .connectTimeout(20, TimeUnit.SECONDS)
      .readTimeout(20, TimeUnit.SECONDS)
      .writeTimeout(20, TimeUnit.SECONDS)
    return okHttpClientBuilder.build()
  }

  private fun createTwitterOauthInterceptor(): Interceptor =
    Oauth1SigningInterceptor(::getOauthKeys)

  private fun getOauthKeys() = OauthKeys(
    consumerKey = BuildConfig.ApiKey,
    consumerSecret = BuildConfig.SecretApiKey,
    accessToken = BuildConfig.AccessToken,
    accessSecret = BuildConfig.AccessTokenSecret
  )

}