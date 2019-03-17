package com.grappim.spacexapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.startActivity

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
    setContentView(R.layout.activity_splash)
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
