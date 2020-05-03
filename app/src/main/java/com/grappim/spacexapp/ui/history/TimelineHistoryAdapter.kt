package com.grappim.spacexapp.ui.history

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.core.extensions.getOffsetDateTime
import com.grappim.spacexapp.core.extensions.inflate
import com.grappim.spacexapp.core.extensions.setMyColorFilter
import com.grappim.spacexapp.core.extensions.setSafeOnClickListener
import com.grappim.spacexapp.core.utils.DateTimeUtils
import com.grappim.spacexapp.model.history.HistoryModel
import kotlinx.android.synthetic.main.layout_history_item.view.*

class TimelineHistoryAdapter(
  private inline val onClick: (HistoryModel) -> Unit
) : RecyclerView.Adapter<TimelineHistoryAdapter.TimelineHistoryViewHolder>() {

  private var items: List<HistoryModel> = emptyList()

  override fun getItemViewType(position: Int): Int =
    TimelineView.getTimeLineViewType(position, itemCount)

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): TimelineHistoryViewHolder =
    TimelineHistoryViewHolder(
      parent.inflate(R.layout.layout_history_item),
      viewType
    )

  override fun onBindViewHolder(
    holder: TimelineHistoryViewHolder,
    position: Int
  ) {
    holder.apply {
      itemView.apply {
        val item = items[position]
        clHistoryItem.setSafeOnClickListener { onClick(items[position]) }
        timeline.marker = setMarker(holder)

        tvHistoryItemTitle.text = item.title
        tvHistoryItemDate.text = DateTimeUtils.getDateTimeFormatter()
          .format(item.eventDateUtc?.getOffsetDateTime())
      }
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

  class TimelineHistoryViewHolder(
    itemView: View,
    viewType: Int
  ) : RecyclerView.ViewHolder(itemView) {
    val timeline: TimelineView = itemView.timelineHistory

    init {
      timeline.initLine(viewType)
    }
  }
}