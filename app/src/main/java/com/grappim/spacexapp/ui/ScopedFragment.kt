package com.grappim.spacexapp.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class ScopedFragment : Fragment() {

  override fun onResume() {
    super.onResume()
    (activity as AppCompatActivity).supportActionBar?.hide()
  }

  override fun onStop() {
    super.onStop()
    (activity as AppCompatActivity).supportActionBar?.show()
  }
}