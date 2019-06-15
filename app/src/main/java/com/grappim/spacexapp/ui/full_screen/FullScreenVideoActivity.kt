package com.grappim.spacexapp.ui.full_screen

import android.os.Build
import android.os.Bundle
import android.widget.MediaController
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.PARCELABLE_TWITTER_VIDEO
import com.grappim.spacexapp.util.PARCELABLE_TWITTER_VIDEO_DURATION
import kotlinx.android.synthetic.main.activity_full_screen_video.*

class FullScreenVideoActivity : FullScreenBaseActivity() {

  var videoUrl: String? = null
  var videoDuration: Int? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_full_screen_video)

    intent.apply {
      videoUrl = getStringExtra(PARCELABLE_TWITTER_VIDEO)
      videoDuration = getIntExtra(PARCELABLE_TWITTER_VIDEO_DURATION, 0)
    }

    videoUrl?.apply {
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
