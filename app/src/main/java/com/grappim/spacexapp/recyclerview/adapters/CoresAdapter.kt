package com.grappim.spacexapp.recyclerview.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.cores.CoreModel
import com.grappim.spacexapp.recyclerview.viewholders.CoreViewHolder
import com.grappim.spacexapp.util.inflateLayout

class CoresAdapter(
  val onClick: (CoreModel) -> Unit
) : RecyclerView.Adapter<CoreViewHolder>() {
  var items: List<CoreModel> = emptyList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoreViewHolder =
    CoreViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_core_item, parent)
    )

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: CoreViewHolder, position: Int) {
    holder.apply {
      core = items[position]
      itemView.setOnClickListener {
        onClick(items[position])
      }
    }
  }

  fun loadItems(newItems: List<CoreModel>) {
    items = newItems
    notifyDataSetChanged()
  }
}