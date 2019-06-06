package com.grappim.spacexapp.ui.launchpads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.gets.GetAllLaunchPads
import com.grappim.spacexapp.repository.SpaceXRepository

class LaunchPadViewModelFactory(
  private val getAllLaunchPads: GetAllLaunchPads
) : ViewModelProvider.NewInstanceFactory() {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    LaunchPadViewModel(getAllLaunchPads) as T
}