package com.grappim.spacexapp.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

//class MarginItemDecorator(
//  private val spaceHeight: Int = 8
//) : RecyclerView.ItemDecoration() {
//  override fun getItemOffsets(
//    outRect: Rect,
//    view: View,
//    parent: RecyclerView,
//    state: RecyclerView.State
//  ) {
//    with(outRect) {
//      if (parent.getChildAdapterPosition(view) == 0) {
//        top = spaceHeight
//      }
//      bottom = spaceHeight
//    }
//  }
//}

class MarginItemDecorator(
  private val spaceHeight: Int = 8,
  private val isHorizontal: Boolean = false,
  private val leftHeight:Int = 0
) : RecyclerView.ItemDecoration() {
  override fun getItemOffsets(
    outRect: Rect,
    view: View,
    parent: RecyclerView,
    state: RecyclerView.State
  ) {
    with(outRect) {
      if (!isHorizontal) {
        if (parent.getChildAdapterPosition(view) == 0) {
          top = spaceHeight
        }
        bottom = spaceHeight
        left = leftHeight
      } else {
        if (parent.getChildAdapterPosition(view) == 0) {
          left = 0
        }
        right = spaceHeight
      }
    }
  }
}
