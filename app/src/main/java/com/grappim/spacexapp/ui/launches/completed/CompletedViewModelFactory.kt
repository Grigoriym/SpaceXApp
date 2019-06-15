package com.grappim.spacexapp.ui.launches.completed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.gets.GetPastLaunches

class CompletedViewModelFactory(
  private val getPastLaunches: GetPastLaunches
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    CompletedViewModel(getPastLaunches) as T

}