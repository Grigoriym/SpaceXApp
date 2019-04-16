package com.grappim.spacexapp.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.GlideApp
import com.grappim.spacexapp.util.NIGHT_THEME_PREF_KEY
import com.grappim.spacexapp.util.THEME_PREFS
import com.grappim.spacexapp.util.startActivity
import kotlinx.android.synthetic.main.activity_splash.*
import timber.log.Timber

private const val SPLASH_DELAY: Long = 1000

class SplashActivity : AppCompatActivity() {

  private var delayHandler: Handler? = null

  private val runnable: Runnable = Runnable {
    if (!isFinishing) {
      this.startActivity<MainActivity>()
      finish()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Timber.d("SplashActivity - onCreate")
    setContentView(R.layout.activity_splash)
    initSharedPrefs()
    GlideApp.with(this)
      .load(ContextCompat.getDrawable(this, R.drawable.logo))
      .into(ivSplash)
    delayHandler = Handler()
    delayHandler?.postDelayed(runnable, SPLASH_DELAY)
  }

  private fun initSharedPrefs() {
    val prefs = getSharedPreferences(THEME_PREFS, Context.MODE_PRIVATE)
    val restoredValue = prefs.getBoolean(NIGHT_THEME_PREF_KEY, false)
    if (!restoredValue) {
      delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
    } else {
      delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
    }
  }

  override fun onDestroy() {
    if (delayHandler != null) {
      delayHandler?.removeCallbacks(runnable)
    }
    super.onDestroy()
  }
}
