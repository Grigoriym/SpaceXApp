package com.grappim.spacexapp.recyclerview.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.recyclerview.viewholders.ShipsViewHolder
import com.grappim.spacexapp.util.inflateLayout

class ShipsAdapter(
  val context: Context?,
  val onClick: (ShipModel) -> Unit
) : RecyclerView.Adapter<ShipsViewHolder>() {

  var items: List<ShipModel> = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ShipsViewHolder =
    ShipsViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_ship_item, parent)
    )

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(
    holder: ShipsViewHolder,
    position: Int
  ) {
    holder.apply {
      ship = items[position]
      itemView.setOnClickListener {
        onClick(items[position])
      }

      // items[position].roles?.let {
      //   for (item in it) {
      //     val chip = Chip(context)
      //     chip.text = item
      //     chip.isClickable = true
      //     chip.isCheckable = false
      //     chipGroup.addView(chip)
      //   }
      // }
    }
  }

  fun loadItems(newItems: List<ShipModel>) {
    items = newItems
    notifyDataSetChanged()
  }
}