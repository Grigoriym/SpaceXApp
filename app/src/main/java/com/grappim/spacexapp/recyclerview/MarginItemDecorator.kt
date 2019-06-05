package com.grappim.spacexapp.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecorator(
  private val spaceHeight: Int = 8,
  private val isHorizontal: Boolean = false,
  private val leftHeight: Int = 0,
  private val isGridLayout: Boolean = false,
  private val spanCount: Int = 0,
  private val spacing: Int = 0,
  private val includeEdge: Boolean = false
) : RecyclerView.ItemDecoration() {
  override fun getItemOffsets(
    outRect: Rect,
    view: View,
    parent: RecyclerView,
    state: RecyclerView.State
  ) {
    with(outRect) {
      if (isGridLayout) {
        val position = parent.getChildAdapterPosition(view)
        if (position >= 0) {
          val column = position % spanCount // item column

          if (includeEdge) {
            outRect.left =
              spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
            outRect.right =
              (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
              outRect.top = spacing
            }
            outRect.bottom = spacing // item bottom
          } else {
            outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
            outRect.right =
              spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
              outRect.top = spacing // item top
            }
          }
        } else {
          outRect.left = 0
          outRect.right = 0
          outRect.top = 0
          outRect.bottom = 0
        }

        return
      }

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
