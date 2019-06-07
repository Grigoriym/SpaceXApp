package com.grappim.spacexapp

import androidx.multidex.MultiDexApplication
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import com.grappim.spacexapp.di.getModule
import com.grappim.spacexapp.di.viewModelFactoryModule
import com.grappim.spacexapp.network.*
import com.grappim.spacexapp.network.api.API
import com.grappim.spacexapp.network.api.TwitterApi
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptor
import com.grappim.spacexapp.network.interceptors.ConnectivityInterceptorImpl
import com.grappim.spacexapp.pagination.TwitterPaginationRepository
import com.grappim.spacexapp.repository.NewSpaceXRepository
import com.grappim.spacexapp.repository.SpaceXRepositoryImpl
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
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
    import(getModule)
    import(viewModelFactoryModule)

    bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
    bind() from singleton { API(instance()) }
    bind() from singleton { SpaceXRepositoryImpl(instance()) }

    bind() from singleton { NetworkHandler(instance()) }
    bind<NewSpaceXRepository>() with singleton { SpaceXNetwork(instance(), instance()) }
    bind() from singleton { createRetrofit() }
    bind() from singleton { SpaceXService(instance()) }

    bind() from singleton { TwitterApi(instance()) }
    bind() from singleton { TwitterPaginationRepository(instance()) }
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