package com.grappim.spacexapp.ui.cores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.repository.SpaceXRepository

class CoreSharedViewModelFactory(
  private val repository: SpaceXRepository
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    CoresSharedViewModel(repository) as T
}