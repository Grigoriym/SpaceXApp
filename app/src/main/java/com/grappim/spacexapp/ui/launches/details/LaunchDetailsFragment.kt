package com.grappim.spacexapp.ui.launches.details

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.dp
import com.grappim.spacexapp.core.extensions.gone
import com.grappim.spacexapp.core.extensions.show
import kotlinx.android.synthetic.main.fragment_launch_details.clLaunchDetailsInfo
import kotlinx.android.synthetic.main.fragment_launch_details.ivLaunchDetailsMissionPatch
import kotlinx.android.synthetic.main.fragment_launch_details.textView56
import kotlinx.android.synthetic.main.fragment_launch_details.tvLaunchDetailsDetails
import kotlinx.android.synthetic.main.fragment_launch_details.tvLaunchDetailsFlightNumber
import kotlinx.android.synthetic.main.fragment_launch_details.tvLaunchDetailsMissionName

class LaunchDetailsFragment : Fragment(R.layout.fragment_launch_details) {

    private val args: LaunchDetailsFragmentArgs by navArgs()

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
                ivLaunchDetailsMissionPatch.load(this.links.missionPatch)
            }

            tvLaunchDetailsMissionName.text = this.missionName
            tvLaunchDetailsFlightNumber.text = this.flightNumber.toString()
            tvLaunchDetailsDetails.text = this.details
        }
    }
}
