package com.grappim.spacexapp.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.capsule.CapsuleModel
import com.grappim.spacexapp.recyclerview.viewholders.CapsulesViewHolder

class CapsulesAdapter(
  val onClick: (CapsuleModel) -> Unit
) : RecyclerView.Adapter<CapsulesViewHolder>() {

  var items: List<CapsuleModel> = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): CapsulesViewHolder =
    CapsulesViewHolder(
      LayoutInflater.from(parent.context)
        .inflate(R.layout.layout_capsule_item, parent, false)
    )

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: CapsulesViewHolder, position: Int) {
    holder.apply {
      capsule = items[position]
      itemView.setOnClickListener { onClick(items[position]) }
    }
  }

  fun loadItems(newItems: List<CapsuleModel>) {
    items = newItems
    notifyDataSetChanged()
  }

}