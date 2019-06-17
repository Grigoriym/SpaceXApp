package com.grappim.spacexapp.ui.launches.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.util.PARCELABLE_LAUNCH_MODEL

class LaunchDetailsActivity : AppCompatActivity() {

  var args: LaunchModel? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_launch_details)

    intent?.apply {
      args = getParcelableExtra(PARCELABLE_LAUNCH_MODEL)
    }

  }
}
