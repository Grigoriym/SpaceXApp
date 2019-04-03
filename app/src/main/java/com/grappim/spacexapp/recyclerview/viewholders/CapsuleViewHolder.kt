package com.grappim.spacexapp.recyclerview.viewholders

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.util.getFormattedDate
import kotlinx.android.synthetic.main.layout_capsule_item.view.*

class CapsuleViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  val btnCapsuleSpecs: Button = view.btnCapsuleSpecs
  var capsule: CapsuleModel? = null
    set(value) {
      field = value
      view.tvCapsuleSerial.text = value?.capsuleSerial
      view.tvCapsuleStatus.text = value?.status?.capitalize()
      view.tvCapsuleOriginalLaunch.text =
        if (value?.originalLaunch == null) {
          "Unknown"
        } else {
          getFormattedDate(value.originalLaunch)
        }

      view.tvCapsuleType.text = value?.type
      view.tvCapsuleNumberOfMissions.text = value?.missions?.size.toString()
    }
}