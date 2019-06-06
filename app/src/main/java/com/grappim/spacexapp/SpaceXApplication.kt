package com.grappim.spacexapp

import androidx.multidex.MultiDexApplication
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import com.grappim.spacexapp.network.*
import com.grappim.spacexapp.network.gets.*
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptor
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptorImpl
import com.grappim.spacexapp.pagination.TwitterPaginationRepository
import com.grappim.spacexapp.repository.NewSpaceXRepository
import com.grappim.spacexapp.repository.SpaceXRepositoryImpl
import com.grappim.spacexapp.ui.capsules.CapsuleViewModelFactory
import com.grappim.spacexapp.ui.cores.CoreViewModelFactory
import com.grappim.spacexapp.ui.history.HistoryViewModelFactory
import com.grappim.spacexapp.ui.info.InfoViewModelFactory
import com.grappim.spacexapp.ui.launchpads.LaunchPadViewModelFactory
import com.grappim.spacexapp.ui.missionspayloads.MissionViewModelFactory
import com.grappim.spacexapp.ui.rockets.RocketsViewModelFactory
import com.grappim.spacexapp.ui.ships.ShipsViewModelFactory
import com.grappim.spacexapp.ui.social_media.twitter.TwitterViewModelFactory
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

//todo when no internet connection app crashes
// todo retrofit coroutines adapter is now deprecated because of new version of retrofit

class SpaceXApplication : MultiDexApplication(), KodeinAware {
  override val kodein by Kodein.lazy {
    import(androidXModule(this@SpaceXApplication))

    bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
    bind() from singleton { API(instance()) }
    bind() from singleton { SpaceXRepositoryImpl(instance()) }

    bind() from singleton { NetworkHandler(instance()) }
    bind<NewSpaceXRepository>() with singleton { SpacexNetwork(instance(), instance()) }
    bind() from singleton { createRetrofit() }
    bind() from singleton { SpacexService(instance()) }

    bind() from singleton { GetRockets(instance()) }
    bind() from singleton { GetAllCapsules(instance()) }
    bind() from singleton { GetPastCapsules(instance()) }
    bind() from singleton { GetUpcomingCapsules(instance()) }
    bind() from singleton { GetAllCores(instance()) }
    bind() from singleton { GetPastCores(instance()) }
    bind() from singleton { GetUpcomingCores(instance()) }
    bind() from singleton { GetAllShips(instance()) }

    bind() from singleton { TwitterApi(instance()) }
    bind() from singleton { TwitterPaginationRepository(instance()) }

    bind() from provider { CapsuleViewModelFactory(instance(), instance(), instance()) }
    bind() from provider { RocketsViewModelFactory(instance()) }
    bind() from provider { CoreViewModelFactory(instance(), instance(), instance()) }
    bind() from provider { ShipsViewModelFactory(instance()) }
    bind() from provider { MissionViewModelFactory(instance()) }
    bind() from provider { InfoViewModelFactory(instance()) }
    bind() from provider { HistoryViewModelFactory(instance()) }
    bind() from provider { LaunchPadViewModelFactory(instance()) }
    bind() from provider { TwitterViewModelFactory(instance()) }
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