package com.grappim.spacexapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.grappim.spacexapp.core.utils.PrefsManager
import com.grappim.spacexapp.core.extensions.launchActivity
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class SplashActivity : AppCompatActivity(), KoinComponent {

  private val prefs: PrefsManager by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    Timber.d("SplashActivity - onCreate")
    initSharedPrefs()
    super.onCreate(savedInstanceState)
  }

  override fun onResume() {
    super.onResume()
    this.launchActivity<MainActivity> { }
    finish()
  }

  private fun initSharedPrefs() {
    if (!prefs.isNightThemeEnabled()) {
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    } else {
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
  }
}
