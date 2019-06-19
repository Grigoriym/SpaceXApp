package com.grappim.spacexapp.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.NIGHT_THEME_PREF_KEY
import com.grappim.spacexapp.util.THEME_PREFS
import com.grappim.spacexapp.util.launchActivity
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

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
    val prefs = getSharedPreferences(THEME_PREFS, Context.MODE_PRIVATE)
    val restoredValue = prefs.getBoolean(NIGHT_THEME_PREF_KEY, false)
    if (!restoredValue) {
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    } else {
      AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
  }
}
