package com.grappim.spacexapp.ui.launchpads

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.inflate
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.model.launchpads.LaunchPadModel
import kotlinx.android.synthetic.main.layout_launch_pad_item.view.*

class LaunchPadsAdapter(
  private inline val onClick: (LaunchPadModel) -> Unit
) : RecyclerView.Adapter<LaunchPadsAdapter.LaunchPadViewHolder>() {

  private var items: List<LaunchPadModel> = emptyList()

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): LaunchPadViewHolder =
    LaunchPadViewHolder(parent.inflate(R.layout.layout_launch_pad_item))

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(
    holder: LaunchPadViewHolder,
    position: Int
  ) {
    holder.apply {
      itemView.apply {
        val item = items[position]
        setSafeOnClickListener {
          onClick(items[position])
        }
        tvLaunchPadItemName.text = item.siteNameLong
        tvLaunchPadItemLaunches.text = context.getString(
          R.string.successful_attempted,
          item.successfulLaunches ?: 0,
          item.attemptedLaunches ?: 0
        )
      }
    }
  }

  fun loadItems(newItems: List<LaunchPadModel>) {
    items = newItems
    notifyDataSetChanged()
  }

  class LaunchPadViewHolder(view: View) : RecyclerView.ViewHolder(view)
}