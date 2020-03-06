package com.grappim.spacexapp.di

import com.grappim.spacexapp.network.createOkHttpClient
import com.grappim.spacexapp.network.createRetrofit
import com.grappim.spacexapp.network.services.RedditService
import com.grappim.spacexapp.repository.RedditRepository
import com.grappim.spacexapp.repository.RedditRepositoryImpl
import com.grappim.spacexapp.core.utils.KOIN_REDDIT_OK_HTTP_CLIENT
import com.grappim.spacexapp.core.utils.KOIN_REDDIT_RETROFIT
import com.grappim.spacexapp.core.utils.REDDIT_BASE_URL
import org.koin.core.qualifier.named
import org.koin.dsl.module

val redditModule = module {
  single(named(KOIN_REDDIT_OK_HTTP_CLIENT)) {
    createOkHttpClient()
  }
  single(named(KOIN_REDDIT_RETROFIT)) {
    createRetrofit(
      REDDIT_BASE_URL,
      get(named(KOIN_REDDIT_OK_HTTP_CLIENT))
    )
  }
  single { RedditService(get(named(KOIN_REDDIT_RETROFIT))) }
  single { RedditRepositoryImpl() }
}