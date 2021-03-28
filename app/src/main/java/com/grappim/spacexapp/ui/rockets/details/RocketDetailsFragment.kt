package com.grappim.spacexapp.ui.rockets.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import coil.load
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getOffsetDateTime
import com.grappim.spacexapp.core.extensions.setMyImageResource
import com.grappim.spacexapp.core.utils.DateTimeUtils
import com.grappim.spacexapp.core.view.elv.CustomExpandableListAdapter
import com.grappim.spacexapp.core.view.elv.MetricsListAdapterItem
import com.grappim.spacexapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_rocket_details.elvRocketDetailsMetrics
import kotlinx.android.synthetic.main.fragment_rocket_details.ivRocketDetails
import kotlinx.android.synthetic.main.fragment_rocket_details.ivRocketDetailsActive
import kotlinx.android.synthetic.main.fragment_rocket_details.tvRocketDetailsBoosters
import kotlinx.android.synthetic.main.fragment_rocket_details.tvRocketDetailsCompany
import kotlinx.android.synthetic.main.fragment_rocket_details.tvRocketDetailsCostPerLaunch
import kotlinx.android.synthetic.main.fragment_rocket_details.tvRocketDetailsCountry
import kotlinx.android.synthetic.main.fragment_rocket_details.tvRocketDetailsDateFirstFlight
import kotlinx.android.synthetic.main.fragment_rocket_details.tvRocketDetailsDescription
import kotlinx.android.synthetic.main.fragment_rocket_details.tvRocketDetailsStages
import kotlinx.android.synthetic.main.fragment_rocket_details.tvRocketDetailsSuccessRate
import kotlinx.android.synthetic.main.fragment_rocket_details.tvRocketDetailsType
import timber.log.Timber

class RocketDetailsFragment : BaseFragment(R.layout.fragment_rocket_details) {

    private val args: RocketDetailsFragmentArgs by navArgs()

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
            ivRocketDetailsActive.setImageResource(
              setMyImageResource(
                it.active
              )
            )

            ivRocketDetails.load(it.flickrImages?.random())

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
