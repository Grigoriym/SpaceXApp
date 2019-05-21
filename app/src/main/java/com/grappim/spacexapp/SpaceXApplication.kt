package com.grappim.spacexapp

import android.app.Application
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptor
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptorImpl
import com.grappim.spacexapp.repository.SpaceXRepositoryImpl
import com.grappim.spacexapp.ui.capsules.CapsuleSharedViewModelFactory
import com.grappim.spacexapp.ui.cores.CoreSharedViewModelFactory
import com.grappim.spacexapp.ui.history.HistoryViewModelFactory
import com.grappim.spacexapp.ui.info.InfoViewModelFactory
import com.grappim.spacexapp.ui.launchpads.LaunchPadViewModelFactory
import com.grappim.spacexapp.ui.missionspayloads.MissionSharedViewModelFactory
import com.grappim.spacexapp.ui.rockets.RocketsSharedViewModelFactory
import com.grappim.spacexapp.ui.ships.ShipsSharedViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext

class SpaceXApplication : Application(), KodeinAware {
  override val kodein by Kodein.lazy {
    import(androidXModule(this@SpaceXApplication))

    bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
    bind() from singleton { API(instance()) }
    bind() from singleton { SpaceXRepositoryImpl(instance()) }

    bind() from provider { CapsuleSharedViewModelFactory(instance()) }
    bind() from provider { RocketsSharedViewModelFactory(instance()) }
    bind() from provider { CoreSharedViewModelFactory(instance()) }
    bind() from provider { ShipsSharedViewModelFactory(instance()) }
    bind() from provider { MissionSharedViewModelFactory(instance()) }
    bind() from provider { InfoViewModelFactory(instance()) }
    bind() from provider { HistoryViewModelFactory(instance()) }
    bind() from provider { LaunchPadViewModelFactory(instance()) }
  }

  override fun onCreate() {
    super.onCreate()
    timberInit()
    Timber.d("Application - onCreate")
    AndroidThreeTen.init(this)
    enableTLS12OnPreLollipop()
  }

  private fun timberInit() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  private fun enableTLS12OnPreLollipop() {
    try {
      ProviderInstaller.installIfNeeded(applicationContext)
      val sslContext: SSLContext = SSLContext.getInstance("TLSv1.2")
      sslContext.init(null, null, null)
      sslContext.createSSLEngine()
    } catch (e: GooglePlayServicesRepairableException) {
      e.printStackTrace()
    } catch (e: GooglePlayServicesNotAvailableException) {
      e.printStackTrace()
    } catch (e: NoSuchAlgorithmException) {
      e.printStackTrace()
    } catch (e: KeyManagementException) {
      e.printStackTrace()
    }
  }
}