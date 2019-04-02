package com.grappim.spacexapp.ui.missionspayloads

import android.view.View
import com.grappim.spacexapp.model.payloads.PayloadModel
import kotlinx.android.synthetic.main.layout_elv_mission_item.view.*

class MissionsPayloadsListAdapterItem<T>(
  val view: View,
  private val body: T
) where T : PayloadModel {
  fun fillItemWithData() {
    view.tvElvMissionItemLongitude.text = body.orbitParams?.longitude.toString()
    view.tvElvMissionItemReferenceSystem.text = body.orbitParams?.referenceSystem?.capitalize()
    view.tvElvMissionItemRegime.text = body.orbitParams?.regime?.capitalize()
    view.tvElvMissionItemSemiMajorAxisKm.text = body.orbitParams?.semiMajorAxisKm.toString()
  }
}