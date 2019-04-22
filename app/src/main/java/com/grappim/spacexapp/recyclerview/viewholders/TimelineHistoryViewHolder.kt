package com.grappim.spacexapp.recyclerview.viewholders

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.github.vipulasri.timelineview.TimelineView
import com.grappim.spacexapp.model.history.HistoryModel
import kotlinx.android.synthetic.main.layout_history_item.view.*

class TimelineHistoryViewHolder(
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