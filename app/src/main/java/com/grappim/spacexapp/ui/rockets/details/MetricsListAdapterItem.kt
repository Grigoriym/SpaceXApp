package com.grappim.spacexapp.ui.rockets.details

import android.view.View
import com.grappim.spacexapp.model.rocket.RocketModel
import kotlinx.android.synthetic.main.layout_elv_rocket_details_metrics.view.*

class MetricsListAdapterItem<T>(
  val view: View,
  private val body: T
) where T : RocketModel {
  fun fillItemsWithData() {
    view
      .tvElvRocketDetailsMetricsDiameter
      .text = "${body.diameter?.meters}m/${body.diameter?.feet}ft"
    view.tvElvRocketDetailsMetricsHeight
      .text = "${body.height?.meters}m/${body.diameter?.feet}ft"
    view.tvElvRocketDetailsMetricsMass
      .text = "${body.mass?.kg}kg/${body.mass?.lb}lb"
  }
}