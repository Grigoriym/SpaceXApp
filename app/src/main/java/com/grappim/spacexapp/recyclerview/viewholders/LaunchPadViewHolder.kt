package com.grappim.spacexapp.recyclerview.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import kotlinx.android.synthetic.main.layout_launch_pad_item.view.*

class LaunchPadViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  var launchPad: LaunchPadModel? = null
    set(value) {
      field = value
      view.apply {
        tvLaunchPadItemName.text = value?.siteNameLong
        tvLaunchPadItemLaunches.text = view.context.getString(
          R.string.successful_attempted,
          value?.successfulLaunches ?: 0,
          value?.attemptedLaunches ?: 0
        )
      }
    }
}