package com.grappim.spacexapp.recyclerview.adapters

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.recyclerview.viewholders.TimelineHistoryViewHolder
import com.grappim.spacexapp.util.inflateLayout
import com.grappim.spacexapp.util.setMyColorFilter
import com.grappim.spacexapp.util.setSafeOnClickListener

class TimelineHistoryAdapter(
  private inline val onClick: (HistoryModel) -> Unit
) : RecyclerView.Adapter<TimelineHistoryViewHolder>() {

  private var items: List<HistoryModel> = emptyList()

  override fun getItemViewType(position: Int): Int =
    TimelineView.getTimeLineViewType(position, itemCount)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): TimelineHistoryViewHolder =
    TimelineHistoryViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_history_item, parent),
      viewType
    )

  override fun onBindViewHolder(
    holder: TimelineHistoryViewHolder,
    position: Int
  ) {
    holder.apply {
      history = items[position]
      cl.setSafeOnClickListener { onClick(items[position]) }
      timeline.marker = setMarker(holder)
    }
  }

  override fun getItemCount(): Int = items.size

  fun loadItems(newItems: List<HistoryModel>) {
    items = newItems
    notifyDataSetChanged()
  }

  private fun setMarker(holder: TimelineHistoryViewHolder): Drawable? {
    val drawable = ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_marker)
    val color = ContextCompat.getColor(holder.itemView.context, R.color.timelineMarker)
    drawable?.setMyColorFilter(color)
    return drawable
  }
}