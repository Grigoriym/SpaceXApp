package com.grappim.spacexapp.recyclerview.adapters

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.history.HistoryModel
import com.grappim.spacexapp.recyclerview.viewholders.TimelineHistoryViewHolder
import com.grappim.spacexapp.util.inflateLayout

class TimelineHistoryAdapter(
  private inline val onClick: (HistoryModel) -> Unit
) : RecyclerView.Adapter<TimelineHistoryViewHolder>() {

  private var items: List<HistoryModel> = emptyList()

  override fun getItemViewType(position: Int): Int {
    return TimelineView.getTimeLineViewType(position, itemCount)
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): TimelineHistoryViewHolder =
    TimelineHistoryViewHolder(
      parent
        .context
        .inflateLayout(R.layout.layout_history_item, parent)
      , viewType
    )

  override fun onBindViewHolder(
    holder: TimelineHistoryViewHolder,
    position: Int
  ) {
    holder.apply {
      history = items[position]
      cl.setOnClickListener { onClick(items[position]) }

      timeline.marker = setMarker(holder)
    }
  }

  private fun setMarker(holder: TimelineHistoryViewHolder): Drawable? {
    val drawable = ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_marker)
    drawable?.setColorFilter(
      ContextCompat.getColor(holder.itemView.context, R.color.timelineMarker),
      PorterDuff.Mode.SRC_IN
    )
    return drawable
  }

  override fun getItemCount(): Int = items.size

  fun loadItems(newItems: List<HistoryModel>) {
    items = newItems
    notifyDataSetChanged()
  }
}