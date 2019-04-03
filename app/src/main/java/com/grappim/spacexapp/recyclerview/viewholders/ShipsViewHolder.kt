package com.grappim.spacexapp.recyclerview.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.util.setMyImageResource
import kotlinx.android.synthetic.main.layout_ship_item.view.*

class ShipsViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  // val chipGroup: ChipGroup = view.cgShipItem
  var ship: ShipModel? = null
    set(value) {
      field = value
      view.tvShipItemName.text = value?.shipName
      view.tvShipItemStatus.text = value?.status
      view.tvShipItemType.text = value?.shipType
      view.tvShipItemHomePort.text = value?.homePort
      view.ivShipItemActive.setImageResource(setMyImageResource(value?.active))
    }
}