package com.grappim.spacexapp.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

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
