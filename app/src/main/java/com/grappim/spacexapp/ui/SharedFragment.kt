package com.grappim.spacexapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import com.grappim.spacexapp.R

/**
 * This class is used to hide specific menu items in fragments
 */
open class SharedFragment : Fragment() {

  override fun onPrepareOptionsMenu(menu: Menu) {
    activity?.invalidateOptionsMenu()
    val item = menu.findItem(R.id.twitter_menu_spinner)
    if (item != null) {
      item.isVisible = false
    }
    super.onPrepareOptionsMenu(menu)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setHasOptionsMenu(true)
  }
}