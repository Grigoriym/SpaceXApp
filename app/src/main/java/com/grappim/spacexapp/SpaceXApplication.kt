package com.grappim.spacexapp

import androidx.multidex.MultiDexApplication
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import com.grappim.spacexapp.di.*
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext

//todo problems when starting the app, it needs ~1-3 seconds to show splash
//todo big problems with menu in the appbar

class SpaceXApplication : MultiDexApplication() {

  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@SpaceXApplication)
      modules(
        listOf(
          networkModule,
          spaceXModule,
          twitterModule,
          viewModuleFactoryModule,
          getModule
        )
      )
    }
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