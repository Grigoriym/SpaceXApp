package com.grappim.spacexapp.di

import com.grappim.spacexapp.repository.SpaceXRepositoryImpl
import com.grappim.spacexapp.network.createOkHttpClient
import com.grappim.spacexapp.network.createRetrofit
import com.grappim.spacexapp.network.services.SpaceXService
import com.grappim.spacexapp.repository.SpaceXRepository
import com.grappim.spacexapp.util.KODEIN_SPACEX_OK_HTTP_CLIENT
import com.grappim.spacexapp.util.KODEIN_SPACEX_RETROFIT
import com.grappim.spacexapp.util.SPACE_X_BASE_URL
import org.koin.core.qualifier.named
import org.koin.dsl.module

val spaceXModule = module {
  single { SpaceXRepositoryImpl(get(), get()) as SpaceXRepository }
  single(named(KODEIN_SPACEX_OK_HTTP_CLIENT)) { createOkHttpClient() }
  single(named(KODEIN_SPACEX_RETROFIT)) {
    createRetrofit(
      SPACE_X_BASE_URL,
      get(named(KODEIN_SPACEX_OK_HTTP_CLIENT))
    )
  }
  single { SpaceXService(get(named(KODEIN_SPACEX_RETROFIT))) }

}