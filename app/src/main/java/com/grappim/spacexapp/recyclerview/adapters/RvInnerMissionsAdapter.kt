package com.grappim.spacexapp.recyclerview.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.capsule.Mission
import com.grappim.spacexapp.recyclerview.viewholders.RvInnerMissionsViewHolder
import com.grappim.spacexapp.util.inflateLayout

class RvInnerMissionsAdapter(
  val onClick: (Mission?) -> Unit
) : RecyclerView.Adapter<RvInnerMissionsViewHolder>() {
  var items: List<Mission?>? = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup, viewType: Int
  ): RvInnerMissionsViewHolder =
    RvInnerMissionsViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_inner_rv_details_mission_item, parent)
    )

  override fun getItemCount(): Int = items?.size ?: 0

  override fun onBindViewHolder(holder: RvInnerMissionsViewHolder, position: Int) {
    holder.apply {
      mission = items?.get(position)
      itemView.setOnClickListener {
        onClick(items?.get(position))
      }
    }
  }

  fun loadItems(newItems: List<Mission?>?) {
    items = newItems
    notifyDataSetChanged()
  }
}