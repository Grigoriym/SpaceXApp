package com.grappim.spacexapp.ui.launches

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.*
import com.grappim.spacexapp.core.utils.DateTimeUtils
import com.grappim.spacexapp.model.launches.LaunchModel
import kotlinx.android.synthetic.main.layout_launches_item.view.*
import java.util.*

class LaunchesAdapter(
  val onClick: (LaunchModel) -> Unit
) : RecyclerView.Adapter<LaunchesAdapter.LaunchesViewHolder>(), Filterable {

  private var items: List<LaunchModel> = emptyList()
  private var filteredList: List<LaunchModel> = items

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): LaunchesViewHolder = LaunchesViewHolder(parent.inflate(R.layout.layout_launches_item))

  override fun getItemCount() = filteredList.size

  override fun onBindViewHolder(
    holder: LaunchesViewHolder,
    position: Int
  ) {
    holder.apply {
      itemView.apply {
        val model = filteredList[position]

        tvLaunchesFlightNumber.text = model.flightNumber.toString()
        tvLaunchesMissionName.text = model.missionName
        tvLaunchesDate.text = model.launchDateUtc?.let { date ->
          DateTimeUtils.getDateTimeFormatter4().format(date.getOffsetDateTime())
        } ?: let {
          "Unknown"
        }
        model.launchSuccess?.let {
          groupLaunchesItem.show()
          ivLaunchesItemLaunchSuccess
            .setImageResource(
              setMyImageResource(
                model.launchSuccess
              )
            )
        }
        setSafeOnClickListener { onClick(filteredList[position]) }
      }
    }
  }

  fun loadItems(newItems: List<LaunchModel>) {
    items = newItems
    filteredList = newItems
    notifyDataSetChanged()
  }

  override fun getFilter(): Filter =
    object : Filter() {
      override fun performFiltering(constraint: CharSequence?): FilterResults {
        val filteredResults: List<LaunchModel>? = if (constraint?.length == 0) {
          items
        } else {
          getFilteredResults(constraint = constraint.toString().toLowerCase(Locale.ROOT))
        }
        val results = FilterResults()
        results.values = filteredResults
        return results
      }

      override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        filteredList = results?.values as List<LaunchModel>
        notifyDataSetChanged()
      }
    }

  private fun getFilteredResults(constraint: String): List<LaunchModel> {
    val results = mutableListOf<LaunchModel>()
    for (i in items) {
      if (i.missionName != null && i.missionName.toLowerCase(Locale.ROOT).contains(constraint)) {
        results.add(i)
      }
    }
    return results
  }

  class LaunchesViewHolder(view: View) : RecyclerView.ViewHolder(view)
}