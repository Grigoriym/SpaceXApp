package com.grappim.spacexapp.recyclerview.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import com.grappim.spacexapp.recyclerview.viewholders.LaunchPadViewHolder
import com.grappim.spacexapp.util.inflateLayout

class LaunchPadsAdapter(
  private inline val onClick: (LaunchPadModel) -> Unit
) : RecyclerView.Adapter<LaunchPadViewHolder>() {
  private var items: List<LaunchPadModel> = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): LaunchPadViewHolder =
    LaunchPadViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_launch_pad_item, parent)
    )

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(
    holder: LaunchPadViewHolder,
    position: Int
  ) {
    holder.apply {
      launchPad = items[position]
      itemView.setOnClickListener {
        onClick(items[position])
      }
    }
  }

  fun loadItems(newItems: List<LaunchPadModel>) {
    items = newItems
    notifyDataSetChanged()
  }
}