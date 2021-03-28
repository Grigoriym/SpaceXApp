package com.grappim.spacexapp

import android.app.Application
import com.grappim.spacexapp.di.components.AppComponent
import com.grappim.spacexapp.di.components.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

//todo twitter images (corners, spacing)
//todo toolbar menu needs to be redone
//todo check menu toolbar
//todo maybe add some tesla stuff? There are some apis for this

class SpaceXApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .factory()
            .create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
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