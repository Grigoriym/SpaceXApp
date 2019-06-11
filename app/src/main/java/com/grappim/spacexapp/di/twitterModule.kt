package com.grappim.spacexapp.di

import com.grappim.spacexapp.network.createOkHttpClient
import com.grappim.spacexapp.network.createRetrofit
import com.grappim.spacexapp.network.createTwitterOauthInterceptor
import com.grappim.spacexapp.network.services.TwitterService
import com.grappim.spacexapp.pagination.TwitterPaginationRepository
import com.grappim.spacexapp.util.KODEIN_TWITTER_INTERCEPTOR
import com.grappim.spacexapp.util.KODEIN_TWITTER_OK_HTTP_CLIENT
import com.grappim.spacexapp.util.KODEIN_TWITTER_RETROFIT
import com.grappim.spacexapp.util.TWITTER_API_BASE_URL
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val twitterModule = Kodein.Module("twitterModule") {

  bind(tag = KODEIN_TWITTER_INTERCEPTOR) from singleton { createTwitterOauthInterceptor() }
  bind(tag = KODEIN_TWITTER_OK_HTTP_CLIENT) from singleton {
    createOkHttpClient(instance(tag = KODEIN_TWITTER_INTERCEPTOR))
  }
  bind(tag = KODEIN_TWITTER_RETROFIT) from singleton {
    createRetrofit(
      TWITTER_API_BASE_URL,
      instance(tag = KODEIN_TWITTER_OK_HTTP_CLIENT)
    )
  }

  bind() from singleton { TwitterService(instance(tag = KODEIN_TWITTER_RETROFIT)) }
  bind() from singleton { TwitterPaginationRepository(instance()) }

}