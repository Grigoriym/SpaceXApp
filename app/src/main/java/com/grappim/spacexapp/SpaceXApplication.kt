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

//todo twitter images (corners, spacing)
//todo toolbar menu needs to be redone
//todo splash screen is terrible - needs to be changed
//todo though date/time works, it needs to be refactored
//todo OMFG this fucking stupid menu, why it can't just ffs work? Why it has to be always buggy as shit? I don't know what to do with this piece of stinky mf shit.

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
          getModule,
          mixNodule,
          redditModule
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