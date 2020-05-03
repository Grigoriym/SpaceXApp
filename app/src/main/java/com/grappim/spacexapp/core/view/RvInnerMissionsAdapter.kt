package com.grappim.spacexapp.core.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.inflate
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.model.capsule.Mission
import kotlinx.android.synthetic.main.layout_inner_rv_details_mission_item.view.*

class RvInnerMissionsAdapter(
  private inline val onClick: (Mission) -> Unit
) : RecyclerView.Adapter<RvInnerMissionsAdapter.RvInnerMissionsViewHolder>() {

  private var items: List<Mission> = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup, viewType: Int
  ): RvInnerMissionsViewHolder =
    RvInnerMissionsViewHolder(
      parent.inflate((R.layout.layout_inner_rv_details_mission_item))
    )

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: RvInnerMissionsViewHolder, position: Int) {
    holder.apply {
      itemView.apply {
        val item = items[position]
        setSafeOnClickListener {
          onClick(item)
        }
        tvInnerRVDetailsMissionName.text = item.name
      }
    }
  }

  fun loadItems(newItems: List<Mission>) {
    items = newItems
    notifyDataSetChanged()
  }

  class RvInnerMissionsViewHolder(view: View) : RecyclerView.ViewHolder(view)
}