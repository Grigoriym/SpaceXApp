package com.grappim.spacexapp.recyclerview.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.recyclerview.viewholders.ShipsViewHolder
import com.grappim.spacexapp.util.inflateLayout

class ShipsAdapter(
  private inline val onClick: (ShipModel) -> Unit
) : RecyclerView.Adapter<ShipsViewHolder>() {

  private var items: List<ShipModel> = emptyList()

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
    }
  }

  fun loadItems(newItems: List<ShipModel>?) {
    items = newItems ?: emptyList()
    notifyDataSetChanged()
  }
}