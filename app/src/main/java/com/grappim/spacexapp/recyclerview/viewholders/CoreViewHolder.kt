package com.grappim.spacexapp.recyclerview.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.util.setMyImageResource
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
      view.ivCoreItemWaterLanding.setImageResource(setMyImageResource(value?.waterLanding))
      value?.missions?.let {
        var result:String? = ""
        for (item in it) {
          result += "${item?.name} "
        }
        view.tvCoreItemMissions.text = result
      }
    }
}