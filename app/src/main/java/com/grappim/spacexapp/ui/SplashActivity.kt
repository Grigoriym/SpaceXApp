package com.grappim.spacexapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.GlideApp
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
    GlideApp.with(this)
      .load(ContextCompat.getDrawable(this, R.drawable.logo))
      .into(ivSplash)
    delayHandler = Handler()
    delayHandler?.postDelayed(runnable, SPLASH_DELAY)
  }

  override fun onDestroy() {
    if (delayHandler != null) {
      delayHandler?.removeCallbacks(runnable)
    }
    super.onDestroy()
  }
}
