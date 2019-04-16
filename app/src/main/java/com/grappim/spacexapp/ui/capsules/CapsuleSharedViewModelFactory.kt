package com.grappim.spacexapp.ui.capsules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grappim.spacexapp.network.API
import com.grappim.spacexapp.repository.SpaceXRepository

class CapsuleSharedViewModelFactory(
  private val api: API,
  private val repository: SpaceXRepository
) : ViewModelProvider.NewInstanceFactory() {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T =
    CapsuleSharedViewModel(api, repository) as T

}