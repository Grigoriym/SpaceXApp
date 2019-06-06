package com.grappim.spacexapp.ui.ships

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.repository.SpaceXRepository

class ShipsViewModelFactory(
  private val repository: SpaceXRepository
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    ShipsViewModel(repository) as T
}