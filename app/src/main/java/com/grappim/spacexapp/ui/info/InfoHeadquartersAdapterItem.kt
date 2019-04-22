package com.grappim.spacexapp.ui.info

import android.view.View
import com.grappim.spacexapp.model.info.InfoModel
import kotlinx.android.synthetic.main.layout_elv_info_headquarters.view.*

class InfoHeadquartersAdapterItem(
  val view:View,
  private val body: InfoModel
) {
  fun fillItemWithData() {
    view.apply {
      tvElvInfoHeadquartersAddress.text = body.headquarters?.address
      tvElvInfoHeadquartersCity.text = body.headquarters?.city
      tvElvInfoHeadquartersState.text = body.headquarters?.state
    }
  }
}