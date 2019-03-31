package com.grappim.spacexapp.recyclerview.viewholders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.model.rocket.RocketModel
import kotlinx.android.synthetic.main.layout_rocket_item.view.*

class RocketsViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  var ivGetRockets: ImageView = view.ivRocketItem
  var tvRocketDescription: TextView = view.tvRocketItemDescription
  var btnRocketItem : Button = view.btnRocketItem
  var rocket: RocketModel? = null
    set(value) {
      field = value
      view.tvRocketItemRocketName.text = value?.rocketName
      tvRocketDescription.text = value?.description
    }
}