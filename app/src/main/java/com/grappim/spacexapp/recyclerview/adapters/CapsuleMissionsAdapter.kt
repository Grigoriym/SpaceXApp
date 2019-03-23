package com.grappim.spacexapp.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.capsule.Mission
import com.grappim.spacexapp.recyclerview.viewholders.CapsuleMissionsViewHolder

class CapsuleMissionsAdapter(
  val onClick: (Mission?) -> Unit
) : RecyclerView.Adapter<CapsuleMissionsViewHolder>() {
  var items: List<Mission?>? = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup, viewType: Int
  ): CapsuleMissionsViewHolder =
    CapsuleMissionsViewHolder(
      LayoutInflater.from(parent.context)
        .inflate(R.layout.layout_capsule_details_mission_item, parent, false)
    )

  override fun getItemCount(): Int = items?.size ?: 0

  override fun onBindViewHolder(holder: CapsuleMissionsViewHolder, position: Int) {
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