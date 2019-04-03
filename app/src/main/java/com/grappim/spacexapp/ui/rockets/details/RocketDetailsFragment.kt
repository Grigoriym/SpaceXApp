package com.grappim.spacexapp.ui.rockets.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.rocket.RocketModel
import com.grappim.spacexapp.ui.CustomExpandableListAdapter
import com.grappim.spacexapp.ui.ScopedFragment
import com.grappim.spacexapp.util.GlideApp
import com.grappim.spacexapp.util.getFormattedyyyyMMdd
import com.grappim.spacexapp.util.setMyImageResource
import kotlinx.android.synthetic.main.fragment_rocket_details.*

class RocketDetailsFragment : ScopedFragment() {

  private var args: RocketModel? = null

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    args = arguments?.getParcelable("model")
    return inflater.inflate(R.layout.fragment_rocket_details, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    tlbrRocketDetails.setNavigationIcon(com.google.android.material.R.drawable.abc_ic_ab_back_material)
    tlbrRocketDetails.setNavigationOnClickListener {
      activity?.onBackPressed()
    }

    GlideApp.with(this)
      .load("https://upload.wikimedia.org/wikipedia/commons/f/f6/COTS-1_Dragon_After_Return_from_Orbit_%28crop%29.jpg")
      .into(ivRocketDetails)

    args?.let {
      tvRocketDetailsBoosters.text = it.boosters.toString()
      tvRocketDetailsCompany.text = it.company
      tvRocketDetailsCostPerLaunch.text = it.costPerLaunch.toString()
      tvRocketDetailsDateFirstFlight.text =
        if (it.firstFlight != null) {
          getFormattedyyyyMMdd(it.firstFlight)
        } else {
          "Unknown"
        }
      tvRocketDetailsDescription.text = it.description
      tvRocketDetailsType.text = it.rocketName
      tvRocketDetailsCountry.text = it.country
      tvRocketDetailsSuccessRate.text = it.successRatePct.toString()
      tvRocketDetailsStages.text = it.stages.toString()
      ivRocketDetailsActive.setImageResource(setMyImageResource(it.active))

      elvRocketDetailsMetrics.setAdapter(
        CustomExpandableListAdapter(
          context!!,
          elvRocketDetailsMetrics,
          "Metrics",
          it,
          R.layout.layout_elv_rocket_details_metrics
        ) { view ->
          MetricsListAdapterItem(
            view,
            it
          ).fillItemsWithData()
        }
      )
    }
  }
}
