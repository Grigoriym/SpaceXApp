package com.grappim.spacexapp.ui.rockets.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getOffsetDateTime
import com.grappim.spacexapp.core.utils.DateTimeUtils
import com.grappim.spacexapp.elv.CustomExpandableListAdapter
import com.grappim.spacexapp.elv.MetricsListAdapterItem
import com.grappim.spacexapp.ui.base.BaseFragment
import com.grappim.spacexapp.util.GlideApp
import com.grappim.spacexapp.util.getFormattedyyyyMMdd
import com.grappim.spacexapp.util.setMyImageResource
import kotlinx.android.synthetic.main.fragment_rocket_details.*
import timber.log.Timber

class RocketDetailsFragment : BaseFragment() {

  private val args: RocketDetailsFragmentArgs by navArgs()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? =
    inflater.inflate(R.layout.fragment_rocket_details, container, false)

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.toolbar_menu_wiki, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.wiki -> {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(args.rocketModel.wikipedia)
        activity?.startActivity(i)
        return true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Timber.d("RocketDetailsFragment - onViewCreated")

    args.rocketModel.let {
      (activity as? AppCompatActivity)?.supportActionBar?.title = it.rocketName
      tvRocketDetailsBoosters.text = it.boosters.toString()
      tvRocketDetailsCompany.text = it.company
      tvRocketDetailsCostPerLaunch.text = it.costPerLaunch.toString()
      tvRocketDetailsDateFirstFlight.text =
        if (it.firstFlight != null) {
          DateTimeUtils.getDateTimeFormatter1().format(
            it.firstFlight.getOffsetDateTime(formatter = DateTimeUtils.getDateTimeFormatter2())
          )
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
