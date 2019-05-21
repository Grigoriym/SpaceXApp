package com.grappim.spacexapp.ui.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.repository.SpaceXRepository

class InfoViewModelFactory(
  private val repository: SpaceXRepository
) : ViewModelProvider.NewInstanceFactory() {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    InfoViewModel(repository) as T
}