package com.grappim.spacexapp.elv

import android.view.View
import com.grappim.spacexapp.model.info.InfoModel
import kotlinx.android.synthetic.main.layout_elv_info_headquarters.view.*

class InfoHeadquartersAdapterItem(
  val view: View,
  private val body: InfoModel
) : ElvAdapterItem {
  override fun fillItemsWithData() {
    view.apply {
      tvElvInfoHeadquartersAddress.text = body.headquarters?.address
      tvElvInfoHeadquartersCity.text = body.headquarters?.city
      tvElvInfoHeadquartersState.text = body.headquarters?.state
    }
  }
}