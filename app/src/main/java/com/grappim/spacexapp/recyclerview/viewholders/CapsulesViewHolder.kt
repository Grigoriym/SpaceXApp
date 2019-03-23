package com.grappim.spacexapp.recyclerview.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.model.capsule.CapsuleModel
import kotlinx.android.synthetic.main.layout_capsule_item.view.*

class CapsulesViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  var capsule: CapsuleModel? = null
    set(value) {
      field = value
      view.tvCapsuleSerial.text = value?.capsuleSerial
      view.tvCapsuleStatus.text = value?.status?.capitalize()
      view.tvCapsuleOriginalLaunch.text = value?.originalLaunch ?: "Unknown"
      view.tvCapsuleType.text = value?.type
      view.tvCapsuleNumberOfMissions.text = value?.missions?.size.toString()
    }
}