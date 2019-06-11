package com.grappim.spacexapp.di

import com.grappim.spacexapp.network.SpaceXNetwork
import com.grappim.spacexapp.network.createOkHttpClient
import com.grappim.spacexapp.network.createRetrofit
import com.grappim.spacexapp.network.services.SpaceXService
import com.grappim.spacexapp.repository.SpaceXRepository
import com.grappim.spacexapp.util.KODEIN_SPACEX_OK_HTTP_CLIENT
import com.grappim.spacexapp.util.KODEIN_SPACEX_RETROFIT
import com.grappim.spacexapp.util.SPACE_X_BASE_URL
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val spaceXModule = Kodein.Module("spaceXModule") {

  bind<SpaceXRepository>() with singleton { SpaceXNetwork(instance(), instance()) }
  bind(tag = KODEIN_SPACEX_OK_HTTP_CLIENT) from singleton { createOkHttpClient() }
  bind(tag = KODEIN_SPACEX_RETROFIT) from singleton {
    createRetrofit(
      SPACE_X_BASE_URL,
      instance(tag = KODEIN_SPACEX_OK_HTTP_CLIENT)
    )
  }
  bind() from singleton { SpaceXService(instance(tag = KODEIN_SPACEX_RETROFIT)) }

}