package com.grappim.spacexapp.di

import com.grappim.spacexapp.network.NetworkHandler
import com.grappim.spacexapp.network.SpaceXNetwork
import com.grappim.spacexapp.network.api.TwitterApi
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptor
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptorImpl
import com.grappim.spacexapp.network.services.SpaceXService
import com.grappim.spacexapp.network.services.createRetrofit
import com.grappim.spacexapp.pagination.TwitterPaginationRepository
import com.grappim.spacexapp.repository.SpaceXRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val mixBinds = Kodein.Module("mix") {
  bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }

  bind() from singleton { NetworkHandler(instance()) }
  bind<SpaceXRepository>() with singleton { SpaceXNetwork(instance(), instance()) }
  bind() from singleton { createRetrofit() }
  bind() from singleton { SpaceXService(instance()) }

  bind() from singleton { TwitterApi(instance()) }
  bind() from singleton { TwitterPaginationRepository(instance()) }
}