package com.grappim.spacexapp.core.view.elv

import android.view.View
import com.grappim.spacexapp.R
import com.grappim.spacexapp.api.model.rocket.RocketModel
import kotlinx.android.synthetic.main.layout_elv_rocket_details_metrics.view.*

class MetricsListAdapterItem(
  val view: View,
  private val body: RocketModel
) : ElvAdapterItem {
  override fun fillItemsWithData() {
    view
      .tvElvRocketDetailsMetricsDiameter
      .text = view.context.getString(
      R.string.rocket_details_metrics_m_ft,
      body.diameter?.meters.toString(),
      body.diameter?.feet.toString()
    )
    view.tvElvRocketDetailsMetricsHeight
      .text = view.context.getString(
      R.string.rocket_details_metrics_m_ft,
      body.height?.meters.toString(),
      body.height?.feet.toString()
    )
    view.tvElvRocketDetailsMetricsMass
      .text = view.context.getString(
      R.string.rocket_details_metrics_kg_lb,
      body.mass?.kg.toString(),
      body.mass?.lb.toString()
    )
  }
}