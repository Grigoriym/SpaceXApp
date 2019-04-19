package com.grappim.spacexapp.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ExpandableListView

class NonScrollExpandableListView : ExpandableListView {

  constructor(context: Context) : super(context)

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

  constructor(context: Context, attr: AttributeSet, defStyle: Int) : super(context, attr, defStyle)

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    val heightMeasureSpecCustom = MeasureSpec.makeMeasureSpec(
      Integer.MAX_VALUE shr 2, MeasureSpec.AT_MOST
    )
    super.onMeasure(widthMeasureSpec, heightMeasureSpecCustom)
    val params = layoutParams
    params.height = measuredHeight
  }
}