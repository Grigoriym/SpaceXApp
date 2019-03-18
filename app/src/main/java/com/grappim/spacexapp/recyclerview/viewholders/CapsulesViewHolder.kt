package com.grappim.spacexapp.recyclerview.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.model.capsule.CapsuleModel
import kotlinx.android.synthetic.main.layout_all_capsules_item.view.*

class CapsulesViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  var capsule: CapsuleModel? = null
    set(value) {
      field = value
      view.tvCapsuleSerial.text = value?.capsuleSerial
      view.tvCapsuleId.text = value?.capsuleId
      view.tvCapsuleStatus.text = value?.status
      view.tvCapsuleOriginalLaunch.text = value?.originalLaunch
      view.tvLandings.text = value?.landings.toString()
      view.tvCapsuleDetails.text = value?.details
      view.tvCapsuleReuseCount.text = value?.reuseCount.toString()
      view.tvCapsuleType.text = value?.type
    }
}