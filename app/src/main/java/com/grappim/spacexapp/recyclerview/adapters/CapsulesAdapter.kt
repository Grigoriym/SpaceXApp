package com.grappim.spacexapp.recyclerview.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.recyclerview.viewholders.CapsuleViewHolder
import com.grappim.spacexapp.util.inflateLayout

class CapsulesAdapter(
  val onClick: (CapsuleModel) -> Unit
) : RecyclerView.Adapter<CapsuleViewHolder>() {

  private var items: List<CapsuleModel> = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): CapsuleViewHolder =
    CapsuleViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_capsule_item, parent)
    )

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: CapsuleViewHolder, position: Int) {
    holder.apply {
      capsule = items[position]
      itemView.setOnClickListener { onClick(items[position]) }
      btnCapsuleSpecs.setOnClickListener { onClick(items[position]) }
    }
  }

  fun loadItems(newItems: List<CapsuleModel>) {
    items = newItems
    notifyDataSetChanged()
  }

}