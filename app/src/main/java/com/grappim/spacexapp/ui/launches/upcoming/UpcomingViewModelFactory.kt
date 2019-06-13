package com.grappim.spacexapp.ui.launches.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.gets.GetUpcomingLaunches

class UpcomingViewModelFactory(
  private val getUpcomingLaunches: GetUpcomingLaunches
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    UpcomingViewModel(getUpcomingLaunches) as T

}