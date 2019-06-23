package com.grappim.spacexapp.di

import com.grappim.spacexapp.network.createOkHttpClient
import com.grappim.spacexapp.network.createRetrofit
import com.grappim.spacexapp.network.createTwitterOauthInterceptor
import com.grappim.spacexapp.network.services.TwitterService
import com.grappim.spacexapp.repository.TwitterPaginationRepository
import com.grappim.spacexapp.repository.TwitterPaginationRepositoryImpl
import com.grappim.spacexapp.util.KOIN_TWITTER_INTERCEPTOR
import com.grappim.spacexapp.util.KOIN_TWITTER_OK_HTTP_CLIENT
import com.grappim.spacexapp.util.KOIN_TWITTER_RETROFIT
import com.grappim.spacexapp.util.TWITTER_API_BASE_URL
import org.koin.core.qualifier.named
import org.koin.dsl.module

val twitterModule = module {
  single(named(KOIN_TWITTER_INTERCEPTOR)) { createTwitterOauthInterceptor() }
  single(named(KOIN_TWITTER_OK_HTTP_CLIENT)) {
    createOkHttpClient(
      get(
        named(
          KOIN_TWITTER_INTERCEPTOR
        )
      )
    )
  }
  single(named(KOIN_TWITTER_RETROFIT)) {
    createRetrofit(
      TWITTER_API_BASE_URL,
      get(named(KOIN_TWITTER_OK_HTTP_CLIENT))
    )
  }
  single { TwitterService(get(named(KOIN_TWITTER_RETROFIT))) }
  single { TwitterPaginationRepositoryImpl() }
}