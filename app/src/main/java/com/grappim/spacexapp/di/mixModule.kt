package com.grappim.spacexapp.di

import android.app.NotificationManager
import androidx.core.content.ContextCompat
import com.grappim.spacexapp.util.KOIN_NOTIFICATION_MANAGER
import com.grappim.spacexapp.util.PrefsManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mixNodule = module {
  single { PrefsManager(androidContext()) }
  single(named(KOIN_NOTIFICATION_MANAGER)) {
    ContextCompat.getSystemService(
      androidContext(),
      NotificationManager::class.java
    ) as NotificationManager
  }
}