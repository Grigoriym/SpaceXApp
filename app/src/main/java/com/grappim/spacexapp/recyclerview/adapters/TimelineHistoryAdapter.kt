package com.grappim.spacexapp.recyclerview.adapters

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.github.vipulasri.timelineview.TimelineView
import com.grappim.spacexapp.R
import com.grappim.spacexapp.model.history.HistoryModel
import kotlinx.android.synthetic.main.layout_history_item.view.*

class TimelineHistoryAdapter(
  val onClick: (HistoryModel) -> Unit
) : RecyclerView.Adapter<TimelineHistoryAdapter.TimeLineViewHolder>() {

  private var items: List<HistoryModel> = listOf()

  override fun getItemViewType(position: Int): Int {
    return TimelineView.getTimeLineViewType(position, itemCount)
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): TimeLineViewHolder =
    TimeLineViewHolder(
      LayoutInflater
        .from(parent.context)
        .inflate(
          R.layout.layout_history_item, parent, false
        ), viewType
    )

  override fun onBindViewHolder(
    holder: TimeLineViewHolder,
    position: Int
  ) {
    holder.apply {
      history = items[position]
      cl.setOnClickListener { onClick(items[position]) }
      setMarker(this, R.drawable.ic_marker, R.color.timelineMarker)
    }
  }

  private fun setMarker(
    holder: TimeLineViewHolder,
    drawableResId: Int, colorFilter: Int
  ) {
    holder.timeline.marker = getDrawable(
      holder.itemView.context,
      drawableResId,
      ContextCompat.getColor(holder.itemView.context, colorFilter)
    )
  }

  private fun getDrawable(context: Context, drawableResId: Int, colorFilter: Int): Drawable? {
    val drawable = VectorDrawableCompat.create(context.resources, drawableResId, context.theme)
    drawable?.setColorFilter(colorFilter, PorterDuff.Mode.SRC_IN)
    return drawable
  }

  override fun getItemCount(): Int = items.size

  fun loadItems(newItems: List<HistoryModel>) {
    items = newItems
    notifyDataSetChanged()
  }

  inner class TimeLineViewHolder(
    itemView: View,
    viewType: Int
  ) : RecyclerView.ViewHolder(itemView) {
    val timeline: TimelineView = itemView.timelineHistory
    val cl: ConstraintLayout = itemView.clHistoryItem

    var history: HistoryModel? = null
      set(value) {
        field = value
        itemView.apply {
          tvHistoryItemTitle.text = value?.title
          tvHistoryItemDate.text = value?.eventDateUtc
        }
      }

    init {
      timeline.initLine(viewType)
    }
  }
}