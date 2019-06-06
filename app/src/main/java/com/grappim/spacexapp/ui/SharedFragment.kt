package com.grappim.spacexapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.Failure

/**
 * This class is used to hide specific menu items in fragments
 */
abstract class SharedFragment : Fragment() {

  abstract fun renderFailure(failureText: String)

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

  protected fun handleFailure(failure: Failure?) {
    when (failure) {
      is Failure.NetworkConnection -> renderFailure("NetworkConnection")
      is Failure.ServerError -> renderFailure("ServerError")
    }
  }
}