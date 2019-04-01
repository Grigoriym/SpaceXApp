package com.grappim.spacexapp

import android.app.Application
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptor
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptorImpl
import com.grappim.spacexapp.ui.capsules.CapsuleSharedViewModelFactory
import com.grappim.spacexapp.ui.cores.CoreSharedViewModelFactory
import com.grappim.spacexapp.ui.missionspayloads.MissionSharedViewModelFactory
import com.grappim.spacexapp.ui.rockets.RocketsSharedViewModelFactory
import com.grappim.spacexapp.ui.ships.ShipsSharedViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

class SpaceXApplication : Application(), KodeinAware {

  override val kodein by Kodein.lazy {
    import(androidXModule(this@SpaceXApplication))

    bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
    bind() from singleton { API(instance()) }

    bind() from provider { CapsuleSharedViewModelFactory(instance()) }
    bind() from provider { RocketsSharedViewModelFactory(instance()) }
    bind() from provider { CoreSharedViewModelFactory(instance()) }
    bind() from provider { ShipsSharedViewModelFactory(instance()) }
    bind() from provider { MissionSharedViewModelFactory(instance()) }
  }

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
    Timber.d("Application - onCreate")
  }
}