package com.grappim.spacexapp.core.di

import com.grappim.spacexapp.BuildConfig
import com.grappim.spacexapp.api.RedditApi
import com.grappim.spacexapp.core.utils.REDDIT_BASE_URL
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Singleton
  @AppComponent.RedditApiQualifier
  fun provideRedditApi(retrofit: Retrofit): RedditApi =
    retrofit.create(RedditApi::class.java)

  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit =
    Retrofit.Builder()
      .baseUrl(REDDIT_BASE_URL)
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .client(createClient())
      .build()

  private fun createClient(): OkHttpClient {
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
    okHttpClientBuilder
      .connectTimeout(20, TimeUnit.SECONDS)
      .readTimeout(20, TimeUnit.SECONDS)
      .writeTimeout(20, TimeUnit.SECONDS)
    return okHttpClientBuilder.build()
  }

}