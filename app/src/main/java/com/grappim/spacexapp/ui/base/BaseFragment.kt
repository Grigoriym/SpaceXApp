package com.grappim.spacexapp.ui.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.grappim.spacexapp.R
import com.grappim.spacexapp.util.Failure
import timber.log.Timber

/**
 * This class is used to hide specific menu items in fragments
 */
abstract class BaseFragment : Fragment() {

  abstract fun renderFailure(failureText: String)//todo maybe make it open rather than abstract

  override fun onPrepareOptionsMenu(menu: Menu) {
    Timber.d("BaseFragment - onPrepareOptionsMenu")

    val item = menu.findItem(R.id.twitter_menu_spinner)
    if (item != null) {
      item.isVisible = false
    }
    val item2: MenuItem? = menu.findItem(R.id.twitter_menu_refresh)
    item2?.isVisible = false

    val item3: MenuItem? = menu.findItem(R.id.searchMenu)
    item3?.isVisible = false

    super.onPrepareOptionsMenu(menu)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setHasOptionsMenu(true)
    activity?.invalidateOptionsMenu()
  }

  protected fun handleFailure(failure: Failure?) {
    when (failure) {
      is Failure.NetworkConnection -> renderFailure("Network Connection Error")
      is Failure.ServerError -> renderFailure("Server Error")
    }
  }
}