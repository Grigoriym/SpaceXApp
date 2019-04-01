package com.grappim.spacexapp.recyclerview.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.model.capsule.Mission
import kotlinx.android.synthetic.main.layout_inner_rv_details_mission_item.view.*

class RvInnerMissionsViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  var mission: Mission? = null
    set(value) {
      field = value
      view.tvInnerRVDetailsMissionName.text = value?.name
    }
}