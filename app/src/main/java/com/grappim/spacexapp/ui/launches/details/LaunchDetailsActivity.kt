package com.grappim.spacexapp.ui.launches.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.bumptech.glide.Glide
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.util.PARCELABLE_LAUNCH_MODEL
import com.grappim.spacexapp.util.dp
import com.grappim.spacexapp.util.gone
import com.grappim.spacexapp.util.show
import kotlinx.android.synthetic.main.activity_launch_details.*

class LaunchDetailsActivity : AppCompatActivity() {

  var args: LaunchModel? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_launch_details)

    intent?.apply {
      args = getParcelableExtra(PARCELABLE_LAUNCH_MODEL)
    }

    fillContent()
  }

  private fun fillContent() {
    args?.apply {
      if (this.links?.missionPatch == null) {
        ivLaunchDetailsMissionPatch.gone()
        val constraintSet = ConstraintSet()
        constraintSet.clone(clLaunchDetailsInfo)
        constraintSet.connect(
          textView56.id,
          ConstraintSet.START,
          ConstraintSet.PARENT_ID,
          ConstraintSet.START,
          8.dp
        )
        constraintSet.applyTo(clLaunchDetailsInfo)
      } else {
        ivLaunchDetailsMissionPatch.show()
        Glide.with(this@LaunchDetailsActivity)
          .load(this.links.missionPatch)
          .into(ivLaunchDetailsMissionPatch)
      }

      tvLaunchDetailsMissionName.text = this.missionName
      tvLaunchDetailsFlightNumber.text = this.flightNumber.toString()
      tvLaunchDetailsDetails.text = this.details
    }
  }
}
