package com.grappim.spacexapp.recyclerview.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
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
      view.tvCoreItemASDS.text = view.context.getString(
        R.string.successful_attempted,
        value?.asdsAttempts ?: 0, value?.asdsLandings ?: 0
      )
      view.tvCoreItemRTLS.text = view.context.getString(
        R.string.successful_attempted,
        value?.rtlsAttempts ?: 0, value?.rtlsLandings ?: 0
      )
      view.ivCoreItemWaterLanding.setImageResource(setMyImageResource(value?.waterLanding))
      value?.missions?.let {
        var result: String? = ""
        for (item in it) {
          result += "${item?.name} "
        }
        view.tvCoreItemMissions.text = result
      }
    }
}