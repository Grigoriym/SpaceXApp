package com.grappim.spacexapp.ui.launches.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.dp
import com.grappim.spacexapp.util.gone
import com.grappim.spacexapp.util.show
import kotlinx.android.synthetic.main.fragment_launch_details.*

class LaunchDetailsFragment : Fragment() {

  private val args: LaunchDetailsFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_launch_details, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    fillContent()
  }

  private fun fillContent() {
    args.launchModel.apply {
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
        Glide.with(requireActivity())
          .load(this.links.missionPatch)
          .into(ivLaunchDetailsMissionPatch)
      }

      tvLaunchDetailsMissionName.text = this.missionName
      tvLaunchDetailsFlightNumber.text = this.flightNumber.toString()
      tvLaunchDetailsDetails.text = this.details
    }
  }
}
