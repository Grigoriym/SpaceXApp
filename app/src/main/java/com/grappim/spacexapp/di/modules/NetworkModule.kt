package com.grappim.spacexapp.di.modules

import com.grappim.spacexapp.BuildConfig
import com.grappim.spacexapp.api.RedditApi
import com.grappim.spacexapp.api.SpaceXApi
import com.grappim.spacexapp.api.TwitterApi
import com.grappim.spacexapp.core.network.ErrorMappingInterceptor
import com.grappim.spacexapp.core.network.Oauth1SigningInterceptor
import com.grappim.spacexapp.core.network.OauthKeys
import com.grappim.spacexapp.core.utils.REDDIT_BASE_URL
import com.grappim.spacexapp.core.utils.SPACE_X_BASE_URL
import com.grappim.spacexapp.core.utils.TWITTER_API_BASE_URL
import com.grappim.spacexapp.di.qualifiers.GeneralClientQualifier
import com.grappim.spacexapp.di.qualifiers.HttpLoggingInterceptorQualifier
import com.grappim.spacexapp.di.qualifiers.OauthClientQualifier
import com.grappim.spacexapp.di.qualifiers.RedditApiQualifier
import com.grappim.spacexapp.di.qualifiers.RedditRetrofit
import com.grappim.spacexapp.di.qualifiers.SpacexApiQualifier
import com.grappim.spacexapp.di.qualifiers.SpacexRetrofit
import com.grappim.spacexapp.di.qualifiers.TwitterApiQualifier
import com.grappim.spacexapp.di.qualifiers.TwitterRetrofit
import com.grappim.spacexapp.di.scopes.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @AppScope
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

    @Provides
    @AppScope
    @RedditApiQualifier
    fun provideRedditApi(
        @RedditRetrofit retrofit: Retrofit
    ): RedditApi = retrofit.create(RedditApi::class.java)

    @Provides
    @AppScope
    @TwitterApiQualifier
    fun provideTwitterApi(
        @TwitterRetrofit retrofit: Retrofit
    ): TwitterApi = retrofit.create(TwitterApi::class.java)

    @Provides
    @AppScope
    @SpacexApiQualifier
    fun provideSpacexApi(
        @SpacexRetrofit retrofit: Retrofit
    ): SpaceXApi = retrofit.create(SpaceXApi::class.java)

    @Provides
    @AppScope
    @RedditRetrofit
    fun provideRedditRetrofit(
        builder: Retrofit.Builder,
        @GeneralClientQualifier generalClient: OkHttpClient
    ): Retrofit =
        builder
            .baseUrl(REDDIT_BASE_URL)
            .client(generalClient)
            .build()

    @Provides
    @AppScope
    @TwitterRetrofit
    fun provideTwitterRetrofit(
        builder: Retrofit.Builder,
        @OauthClientQualifier oauthClient: OkHttpClient
    ): Retrofit =
        builder
            .baseUrl(TWITTER_API_BASE_URL)
            .client(oauthClient)
            .build()

    @Provides
    @AppScope
    @SpacexRetrofit
    fun provideSpacexRetrofit(
        builder: Retrofit.Builder,
        @GeneralClientQualifier generalClient: OkHttpClient
    ): Retrofit =
        builder
            .baseUrl(SPACE_X_BASE_URL)
            .client(generalClient)
            .build()

    @Provides
    @AppScope
    @OauthClientQualifier
    fun provideOauthOkHttpClient(
        @HttpLoggingInterceptorQualifier loggingInterceptor: HttpLoggingInterceptor,
        oauthInterceptor: Oauth1SigningInterceptor,
        errorMappingInterceptor: ErrorMappingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(errorMappingInterceptor)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(loggingInterceptor)
                }
                addInterceptor(oauthInterceptor)
            }
            .build()

    @Provides
    @AppScope
    @GeneralClientQualifier
    fun provideOkHttpClient(
        @HttpLoggingInterceptorQualifier loggingInterceptor: HttpLoggingInterceptor,
        errorMappingInterceptor: ErrorMappingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(errorMappingInterceptor)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(loggingInterceptor)
                }
            }
            .build()

    @Provides
    @AppScope
    @HttpLoggingInterceptorQualifier
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message -> Timber.tag("API").d(message) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @AppScope
    fun provideOauthKeys(): OauthKeys =
        OauthKeys(
            consumerKey = BuildConfig.ApiKey,
            consumerSecret = BuildConfig.SecretApiKey,
            accessToken = BuildConfig.AccessToken,
            accessSecret = BuildConfig.AccessTokenSecret
        )

    @Provides
    @AppScope
    fun provideOauthInterceptor(
        oauthKeys: OauthKeys
    ): Oauth1SigningInterceptor = Oauth1SigningInterceptor(oauthKeys)

}