package com.grappim.spacexapp.recyclerview.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.cores.CoreModel
import kotlinx.android.synthetic.main.layout_core_item.view.*

class CoreViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {
  var core: CoreModel? = null
    set(value) {
      field = value
      view.tvCoreItemBlock.text = value?.block?.toString() ?: "TBD"
      view.tvCoreItemOriginalLaunch.text = value?.originalLaunch ?: "Unknown"
      view.tvCoreItemCoreSerial.text = value?.coreSerial
      view.tvCoreItemASDS.text = "${value?.asdsAttempts}/${value?.asdsLandings}"
      view.tvCoreItemRTLS.text = "${value?.rtlsAttempts}/${value?.rtlsLandings}"
      view.ivCoreItemWaterLanding.setImageResource(
        if (value?.waterLanding!!) R.drawable.ic_check_circle_black_24dp
        else R.drawable.ic_cancel_black_24dp
      )
      value.missions?.let {
        var result:String? = ""
        for (item in it) {
          result += "${item?.name} "
        }
        view.tvCoreItemMissions.text = result
      }
    }
}