package com.grappim.spacexapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.GlideApp
import kotlinx.android.synthetic.main.activity_full_screen_image.*

class FullScreenImageActivity : AppCompatActivity() {

  var args: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_full_screen_image)

    intent.apply {
      args = getStringExtra("asd")
    }
    println(args)

    args?.apply {
      GlideApp.with(baseContext)
        .load(args)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .into(ivFulScreen)
    }
  }

  override fun onWindowFocusChanged(hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)
    if (hasFocus) hideSystemUI()
  }

  private fun hideSystemUI() {
    window.decorView.systemUiVisibility = (
        View.SYSTEM_UI_FLAG_IMMERSIVE
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN
        )
  }

  private fun showSystemUI() {
    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
  }

}
