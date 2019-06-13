package com.grappim.spacexapp.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.launches.LaunchModel
import com.grappim.spacexapp.util.inflateLayout
import kotlinx.android.synthetic.main.layout_launches_item.view.*

class LaunchesAdapter(
  val onClick: (LaunchModel) -> Unit
) : RecyclerView.Adapter<LaunchesAdapter.LaunchesViewHolder>() {

  private var items: List<LaunchModel> = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): LaunchesViewHolder =
    LaunchesViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_launches_item, parent)
    )

  override fun getItemCount() = items.size

  override fun onBindViewHolder(
    holder: LaunchesViewHolder,
    position: Int
  ) {
    holder.apply {
      model = items[position]
      itemView.setOnClickListener { onClick(items[position]) }
    }
  }

  fun loadItems(newItems: List<LaunchModel>) {
    items = newItems
    notifyDataSetChanged()
  }

  class LaunchesViewHolder(
    private val view: View
  ) : RecyclerView.ViewHolder(view) {
    var model: LaunchModel? = null
      set(value) {
        field = value
        view.apply {
          tvLaunchesFlightNumber.text = value?.flightNumber.toString()
          tvLaunchesMissionName.text = value?.missionName
          tvLaunchesDate.text = value?.launchDateLocal
        }
      }
  }
}