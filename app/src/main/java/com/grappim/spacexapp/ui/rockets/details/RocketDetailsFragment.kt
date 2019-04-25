package com.grappim.spacexapp.ui.rockets.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.grappim.spacexapp.R
import com.grappim.spacexapp.elv.CustomExpandableListAdapter
import com.grappim.spacexapp.elv.MetricsListAdapterItem
import com.grappim.spacexapp.ui.ScopedFragment
import com.grappim.spacexapp.util.GlideApp
import com.grappim.spacexapp.util.getFormattedyyyyMMdd
import com.grappim.spacexapp.util.setMyImageResource
import kotlinx.android.synthetic.main.fragment_rocket_details.*
import timber.log.Timber

class RocketDetailsFragment : ScopedFragment() {

  private val args: RocketDetailsFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_rocket_details, container, false)
  }

  private fun initToolbar() {
    setHasOptionsMenu(true)
    tlbrRocketDetails.inflateMenu(R.menu.toolbar_menu_wiki)
    tlbrRocketDetails
      .setNavigationIcon(com.google.android.material.R.drawable.abc_ic_ab_back_material)
    tlbrRocketDetails
      .setNavigationOnClickListener {
        activity?.onBackPressed()
      }
    tlbrRocketDetails.setOnMenuItemClickListener { item ->
      when (item.itemId) {
        R.id.wiki -> {
          val i = Intent(Intent.ACTION_VIEW)
          i.data = Uri.parse(args.rocketModel.wikipedia)
          activity?.startActivity(i)
        }
      }
      false
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    Timber.d("RocketDetailsFragment - onViewCreated")
    super.onViewCreated(view, savedInstanceState)
    initToolbar()

    args.rocketModel.let {
      tlbrRocketDetails.title = it.rocketName
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

      GlideApp.with(this)
        .load(it.flickrImages?.random())
        .centerCrop()
        .into(ivRocketDetails)

      elvRocketDetailsMetrics.setAdapter(
        CustomExpandableListAdapter(
          context,
          elvRocketDetailsMetrics,
          "Metrics",
          it,
          R.layout.layout_elv_rocket_details_metrics,
          listAdapterItemInit = { view ->
            MetricsListAdapterItem(
              view,
              it
            ).fillItemsWithData()
          }
        )
      )
    }
  }
}
