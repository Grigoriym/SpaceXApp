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
      view.apply {
        tvCapsuleSerial.text = value?.capsuleSerial
        tvCapsuleStatus.text = value?.status?.capitalize()
        tvCapsuleOriginalLaunch.text =
          if (value?.originalLaunch == null) {
            "Unknown"
          } else {
            getFormattedDate(value.originalLaunch)
          }
        tvCapsuleType.text = value?.type
        tvCapsuleNumberOfMissions.text = value?.missions?.size.toString()
      }
    }
}