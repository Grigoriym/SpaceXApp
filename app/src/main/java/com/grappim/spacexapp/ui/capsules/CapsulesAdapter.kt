package com.grappim.spacexapp.ui.capsules

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getOffsetDateTime
import com.grappim.spacexapp.core.extensions.inflate
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.core.utils.DateTimeUtils
import com.grappim.spacexapp.model.capsule.CapsuleModel
import kotlinx.android.synthetic.main.layout_capsule_item.view.*

class CapsulesAdapter(
  private inline val onClick: (CapsuleModel) -> Unit
) : RecyclerView.Adapter<CapsulesAdapter.CapsuleViewHolder>() {

  private var items: List<CapsuleModel> = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): CapsuleViewHolder =
    CapsuleViewHolder(parent.inflate(R.layout.layout_capsule_item))

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: CapsuleViewHolder, position: Int) {
    holder.apply {
      itemView.apply {
        val item = items[position]
        itemView.setSafeOnClickListener { onClick(items[position]) }
        btnCapsuleSpecs.setSafeOnClickListener { onClick(items[position]) }

        tvCapsuleSerial.text = item.capsuleSerial
        tvCapsuleStatus.text = item.status?.capitalize()
        tvCapsuleOriginalLaunch.text =
          if (item.originalLaunch == null) {
            "Unknown"
          } else {
            DateTimeUtils.getDateTimeFormatter3().format(item.originalLaunch.getOffsetDateTime())
          }
        tvCapsuleType.text = item.type
        tvCapsuleNumberOfMissions.text = item.missions?.size.toString()
      }
    }
  }

  fun loadItems(newItems: List<CapsuleModel>) {
    items = newItems
    notifyDataSetChanged()
  }

  class CapsuleViewHolder(view: View) : RecyclerView.ViewHolder(view)

}