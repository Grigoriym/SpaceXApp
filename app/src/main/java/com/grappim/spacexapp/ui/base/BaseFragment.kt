package com.grappim.spacexapp.ui.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
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

  open fun renderFailure(failureText: String) {}

  override fun onPrepareOptionsMenu(menu: Menu) {
    Timber.d("BaseFragment - onPrepareOptionsMenu")
    menu.clear()
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    val item: MenuItem? = menu.findItem(R.id.twitter_menu_spinner)
    item?.isVisible = false
    val item2: MenuItem? = menu.findItem(R.id.twitter_menu_refresh)
    item2?.isVisible = false
    val item3: MenuItem? = menu.findItem(R.id.searchMenu)
    item3?.isVisible = false
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setHasOptionsMenu(true)
  }

  protected fun handleFailure(failure: Failure?) {
    when (failure) {
      is Failure.NetworkConnection -> renderFailure("Network Connection Error")
      is Failure.ServerError -> renderFailure("Server Error")
    }
  }
}