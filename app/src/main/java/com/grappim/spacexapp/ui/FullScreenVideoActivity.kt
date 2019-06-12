package com.grappim.spacexapp.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.PARCELABLE_TWITTER_VIDEO
import kotlinx.android.synthetic.main.activity_full_screen_video.*

class FullScreenVideoActivity : FullScreenBaseActivity() {

  var args: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_full_screen_video)

    intent.apply {
      args = getStringExtra(PARCELABLE_TWITTER_VIDEO)
    }

    args?.apply {
      vvVideo.setVideoPath(this)
      vvVideo.start()
    }

    val controller = MediaController(this)
    controller.setMediaPlayer(vvVideo)
    vvVideo.setMediaController(controller)
  }

  private fun stopPlayback() {
    vvVideo.stopPlayback()
  }

  override fun onStop() {
    super.onStop()
    stopPlayback()
  }

  override fun onPause() {
    super.onPause()
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
      vvVideo.pause()
    }
  }
}
