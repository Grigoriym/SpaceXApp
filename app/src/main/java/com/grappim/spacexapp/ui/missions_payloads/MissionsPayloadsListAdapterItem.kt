package com.grappim.spacexapp.ui.missions_payloads

import android.view.View
import com.grappim.spacexapp.model.payloads.PayloadModel
import kotlinx.android.synthetic.main.layout_elv_mission_item.view.tvElvMissionItemLongitude
import kotlinx.android.synthetic.main.layout_elv_mission_item.view.tvElvMissionItemReferenceSystem
import kotlinx.android.synthetic.main.layout_elv_mission_item.view.tvElvMissionItemRegime
import kotlinx.android.synthetic.main.layout_elv_mission_item.view.tvElvMissionItemSemiMajorAxisKm

class MissionsPayloadsListAdapterItem(
    val view: View,
    private val body: PayloadModel
) {
    fun fillItemWithData() {
        view.tvElvMissionItemLongitude.text = body.orbitParams?.longitude?.toString() ?: "0"
        view.tvElvMissionItemReferenceSystem.text = body.orbitParams?.referenceSystem?.capitalize()
        view.tvElvMissionItemRegime.text = body.orbitParams?.regime?.capitalize()
        view.tvElvMissionItemSemiMajorAxisKm.text = body.orbitParams?.semiMajorAxisKm.toString()
    }
}