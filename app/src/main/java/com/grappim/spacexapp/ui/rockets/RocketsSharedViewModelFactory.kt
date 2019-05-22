package com.grappim.spacexapp.ui.rockets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.repository.SpaceXRepository

class RocketsSharedViewModelFactory(
  private val repository: SpaceXRepository
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return RocketsSharedViewModel(repository) as T
  }
}