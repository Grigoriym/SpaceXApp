package com.grappim.spacexapp.ui.rockets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.gets.GetRockets

class RocketsViewModelFactory(
  private val getRockets: GetRockets
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return RocketsViewModel(getRockets) as T
  }
}