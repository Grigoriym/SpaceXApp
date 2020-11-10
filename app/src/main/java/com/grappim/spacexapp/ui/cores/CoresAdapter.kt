package com.grappim.spacexapp.ui.cores

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.inflate
import com.grappim.spacexapp.core.extensions.setMyImageResource
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.api.model.cores.CoreModel
import kotlinx.android.synthetic.main.layout_core_item.view.ivCoreItemWaterLanding
import kotlinx.android.synthetic.main.layout_core_item.view.tvCoreItemASDS
import kotlinx.android.synthetic.main.layout_core_item.view.tvCoreItemBlock
import kotlinx.android.synthetic.main.layout_core_item.view.tvCoreItemCoreSerial
import kotlinx.android.synthetic.main.layout_core_item.view.tvCoreItemMissions
import kotlinx.android.synthetic.main.layout_core_item.view.tvCoreItemOriginalLaunch
import kotlinx.android.synthetic.main.layout_core_item.view.tvCoreItemRTLS

class CoresAdapter(
  private inline val onClick: (CoreModel) -> Unit
) : RecyclerView.Adapter<CoresAdapter.CoreViewHolder>() {

    private var items: List<CoreModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoreViewHolder =
        CoreViewHolder(parent.inflate(R.layout.layout_core_item))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CoreViewHolder, position: Int) {
        holder.apply {
            itemView.apply {
                val item = items[position]
                setSafeOnClickListener {
                    onClick(items[position])
                }
                tvCoreItemBlock.text = item.block?.toString() ?: "TBD"
                tvCoreItemOriginalLaunch.text = item.originalLaunch ?: "Unknown"
                tvCoreItemCoreSerial.text = item.coreSerial
                tvCoreItemASDS.text = context.getString(
                  R.string.successful_attempted,
                  item.asdsLandings ?: 0,
                  item.asdsAttempts ?: 0
                )
                tvCoreItemRTLS.text = context.getString(
                  R.string.successful_attempted,
                  item.rtlsLandings ?: 0,
                  item.rtlsAttempts ?: 0
                )
                ivCoreItemWaterLanding.setImageResource(
                  setMyImageResource(
                    item.waterLanding
                  )
                )
                item.missions?.let {
                    var result: String? = ""
                    for (mission in it) {
                        result += "${mission?.name} "
                    }
                    tvCoreItemMissions.text = result
                }
            }
        }
    }

    fun loadItems(newItems: List<CoreModel>) {
        items = newItems
        notifyDataSetChanged()
    }

    class CoreViewHolder(view: View) : RecyclerView.ViewHolder(view)
}