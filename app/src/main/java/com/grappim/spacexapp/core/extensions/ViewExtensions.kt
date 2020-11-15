package com.grappim.spacexapp.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.google.android.material.snackbar.Snackbar
import com.grappim.spacexapp.core.utils.SafeClickListener

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View =
  LayoutInflater.from(context).inflate(layoutId, this, false)

fun View.disable() {
  this.isClickable = false
  this.isEnabled = false
}

fun View.enable() {
  this.isClickable = true
  this.isEnabled = true
}

fun View.enableOrDisable(value: Boolean) {
  if (value) {
    this.enable()
  } else {
    this.disable()
  }
}

fun View.gone(): View {
  if (visibility != View.GONE) {
    visibility = View.GONE
  }
  return this
}

fun View.hide(): View {
  if (visibility != View.INVISIBLE) {
    visibility = View.INVISIBLE
  }
  return this
}

fun View.show(): View {
  if (visibility != View.VISIBLE) {
    visibility = View.VISIBLE
  }
  return this
}

fun View.showOrGone(show: Boolean) {
  visibility = if (show) {
    View.VISIBLE
  } else {
    View.GONE
  }
}

inline fun View.showIf(condition: () -> Boolean): View {
  visibility = if (visibility != View.VISIBLE && condition()) {
    View.VISIBLE
  } else {
    View.GONE
  }
  return this
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
  val safeClickListener = SafeClickListener {
    onSafeClick(it)
  }
  setOnClickListener(safeClickListener)
}

fun View.showSnackbar(text: String, timeLength: Int = Snackbar.LENGTH_LONG) {
  Snackbar.make(this, text, timeLength).show()
}
