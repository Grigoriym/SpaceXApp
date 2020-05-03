package com.grappim.spacexapp.ui.ships

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.inflate
import com.grappim.spacexapp.core.extensions.setMyImageResource
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.model.ships.ShipModel
import kotlinx.android.synthetic.main.layout_ship_item.view.*

class ShipsAdapter(
  private inline val onClick: (ShipModel) -> Unit
) : RecyclerView.Adapter<ShipsAdapter.ShipsViewHolder>() {

  private var items: List<ShipModel> = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ShipsViewHolder =
    ShipsViewHolder(parent.inflate(R.layout.layout_ship_item))

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(
    holder: ShipsViewHolder,
    position: Int
  ) {
    holder.apply {
      itemView.apply {
        val item = items[position]
        setSafeOnClickListener {
          onClick(items[position])
        }
        tvShipItemName.text = item.shipName
        tvShipItemStatus.text = item.status
        tvShipItemType.text = item.shipType
        tvShipItemHomePort.text = item.homePort
        ivShipItemActive.setImageResource(setMyImageResource(item.active))
      }
    }
  }

  fun loadItems(newItems: List<ShipModel>?) {
    items = newItems ?: emptyList()
    notifyDataSetChanged()
  }

  class ShipsViewHolder(view: View) : RecyclerView.ViewHolder(view)
}