package com.grappim.spacexapp.recyclerview.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.model.ships.ShipModel
import com.grappim.spacexapp.core.extensions.setMyImageResource
import kotlinx.android.synthetic.main.layout_ship_item.view.*

class ShipsViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  var ship: ShipModel? = null
    set(value) {
      field = value
      view.tvShipItemName.text = value?.shipName
      view.tvShipItemStatus.text = value?.status
      view.tvShipItemType.text = value?.shipType
      view.tvShipItemHomePort.text = value?.homePort
      view.ivShipItemActive.setImageResource(
        setMyImageResource(
          value?.active
        )
      )
    }
}