package com.grappim.spacexapp.di

import com.grappim.spacexapp.network.NetworkHandler
import com.grappim.spacexapp.network.SpaceXNetwork
import com.grappim.spacexapp.network.api.TwitterApi
import com.grappim.spacexapp.network.createRetrofit
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptor
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptorImpl
import com.grappim.spacexapp.network.services.SpaceXService
import com.grappim.spacexapp.network.services.TwitterService
import com.grappim.spacexapp.pagination.TwitterPaginationRepository
import com.grappim.spacexapp.repository.SpaceXRepository
import com.grappim.spacexapp.util.SPACE_X_BASE_URL
import com.grappim.spacexapp.util.TWITTER_API_BASE_URL
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

//todo name tags

val mixBinds = Kodein.Module("mix") {

  bind() from singleton { NetworkHandler(instance()) }
  bind<SpaceXRepository>() with singleton { SpaceXNetwork(instance(), instance()) }

  bind(tag = "spaceX") from singleton { createRetrofit(SPACE_X_BASE_URL) }
  bind(tag = "twitter") from singleton { createRetrofit(TWITTER_API_BASE_URL) }

  bind() from singleton { SpaceXService(instance(tag = "spaceX")) }
  bind() from singleton { TwitterService(instance(tag = "twitter")) }

  bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
  bind(tag = "Twitter Api") from singleton { TwitterApi(instance()) }
  bind() from singleton { TwitterPaginationRepository(instance(tag="Twitter Api")) }
}