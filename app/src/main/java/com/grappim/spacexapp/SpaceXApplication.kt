package com.grappim.spacexapp

import androidx.multidex.MultiDexApplication
import com.grappim.spacexapp.core.di.AppComponent
import com.grappim.spacexapp.core.di.DaggerAppComponent
import com.grappim.spacexapp.di.*
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

//todo twitter images (corners, spacing)
//todo toolbar menu needs to be redone
//todo check menu toolbar
//todo maybe add some tesla stuff? There are some apis for this
//todo bug with back button behavior, api data does not load
//todo remove coroutines and implement rx?

class SpaceXApplication : MultiDexApplication() {

  companion object {
    lateinit var instance: SpaceXApplication
  }

  val appComponent: AppComponent by lazy {
    DaggerAppComponent.factory().create(applicationContext)
  }

  override fun onCreate() {
    super.onCreate()
    instance = this
    startKoin {
      androidContext(this@SpaceXApplication)
      modules(
        listOf(
          networkModule,
          spaceXModule,
          twitterModule,
          viewModelFactoryModule,
          getModule,
          mixNodule
        )
      )
    }
    timberInit()
    AndroidThreeTen.init(this)
    Timber.d("Application - onCreate")
  }

  private fun timberInit() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}