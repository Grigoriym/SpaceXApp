package com.grappim.spacexapp

import androidx.multidex.MultiDexApplication
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import com.grappim.spacexapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext

//todo twitter images (corners, spacing)
//todo toolbar menu needs to be redone
//todo splash screen is terrible - needs to be changed
//todo OMFG this fucking stupid menu, why it can't just ffs work? Why it has to be always buggy as shit? I don't know what to do with this piece of stinky mf shit.
//todo maybe add some tesla stuff? There are some apis for this
//todo bug with back button behavior, api data does not load

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
          viewModelFactoryModule,
          getModule,
          mixNodule,
          redditModule
        )
      )
    }
    timberInit()
    Timber.d("Application - onCreate")
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